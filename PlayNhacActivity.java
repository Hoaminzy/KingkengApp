package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.ViewPaperplaylistNhac;
import com.Hoaminzf.fithou.mykengkingapp.Fragment.Fragment_DS_Cac_Bai_Hat;
import com.Hoaminzf.fithou.mykengkingapp.Fragment.Fragment_DiaNhac;
import com.Hoaminzf.fithou.mykengkingapp.Fragment.Fragment_Play_Danh_Sach_Cac_Bai_Hat;
import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.db.Database;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import okhttp3.internal.Util;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class PlayNhacActivity extends AppCompatActivity {

    RemoteViews bigViews;

    Toolbar toolbarplaynhac;
    TextView txttimesong, txttotaltimesong,tvTimeSleep ;
    SeekBar seekBartime;
        ImageView imvClock;
    CountDownTimer timerSleep;
   public static ImageButton imgplay, imgrepeat, imgnext, imgpre, imgradom,imgclock ;
    ViewPager viewPagerplaynhac;
    public  static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public  static ViewPaperplaylistNhac adapternhac;
    Fragment_DiaNhac fragment_diaNhac;
    Fragment_Play_Danh_Sach_Cac_Bai_Hat fragment_play_danh_sach_cac_bai_hat;
    Fragment_DS_Cac_Bai_Hat fragment_ds_cac_bai_hat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    private int key;
    private ImageView btnDownload;
    private Handler mHandler = new Handler();
    Util utils;
    private int currentSongIndex = 0;
    final Handler handler = new Handler();
    static final int pr_type=0;
    ProgressDialog progressDialog;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
      //  setContentView(R.layout.timer);
        //tạo  file sqlite và tạo bảng trong sqlite đó
        database=new Database(PlayNhacActivity.this,"mymusicDB.sqlite",null,2);
        database.Querydata("Create table if not exists tblmymusic(id Integer primary key autoincrement, tenbai nvarchar(100),linlanh nvarchar(300),linkbh nvarchar(300))");
//StricMode kiểm tra tín hiệu mạng
        StrictMode.ThreadPolicy policy = new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();




    }

    private void stop_sleepp(int time, int interval) {
        tvTimeSleep.setVisibility(View.VISIBLE);
        imvClock.setVisibility(View.VISIBLE);
        timerSleep = new CountDownTimer(time, interval) {

            public void onTick(long millisUntilFinished) {
//                int h = (int) (time - millisUntilFinished);

                // đếm ngược
                String countDown = getStringTimeFromDuration((int) millisUntilFinished);
                tvTimeSleep.setText(countDown);
            }



            public void onFinish() {
                // tắt app

              doStop();
                moveTaskToBack(true);

                // tắt máy
//                try {
//                    Process proc = Runtime.getRuntime()
//                            .exec(new String[]{ "su", "-c", "reboot -p" });
//                    proc.waitFor();
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
            }
        };
        timerSleep.start();
        Toast.makeText(this, "Đã bật hẹn giờ tắt sau " + getStringTimeFromDuration(time), Toast.LENGTH_LONG).show();
    }
    public static String getStringTimeFromDuration(int millis) {
        if (millis >= 3600000) {
            return String.format("%01d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                    TimeUnit.MILLISECONDS.toSeconds(millis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        }
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), // The change is in this line
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
    //gán hình ảnh cho đĩa nhạc quay
    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1)!=null){
                    if(mangbaihat.size()>0){
                        fragment_diaNhac.Playnhac(mangbaihat.get(0).getSHinhAnhBH());
                        //removeCallbacks: xóa đi hình ảnh cũ
                        handler.removeCallbacks(this);


                    }else {
                        handler.postDelayed(this, 300);
                    }

                }
            }
        },500);
//        imgclock.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mediaPlayer.stop();
//                Intent intent = new Intent(PlayNhacActivity.this,TimerActivity.class);
//                startActivity(intent);
//
//            }
//        });
//        btnDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                File file = new File(Environment.getExternalStorageDirectory().getPath()+"/Download"+"/"+mangbaihat.get(currentSongIndex).getSTenBaiHat()+".mp3");
//                if(file.exists()){
//                    Toast.makeText(getApplicationContext(), "File đã tồn tại", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(getApplicationContext(), "Downloading...", Toast.LENGTH_SHORT).show();
//                    new DownloadMusicFromInternet().execute(mangbaihat.get(currentSongIndex).getSTenBaiHat());
//                    //thêm vào mảng
//
//                    database.Querydata("Insert into tblmymusic values(null,'"+mangbaihat.get(currentSongIndex).getSTenBaiHat()+"','"+mangbaihat.get(currentSongIndex).getSHinhAnhBH()+"','"+mangbaihat.get(currentSongIndex).getSLinkBH()+"')");
//
//                }
//            }
//        });
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);

                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);

                }

            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat==false){
                    if(checkrandom==true){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgradom.setImageResource(R.drawable.iconrepeat);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat=true;
                }else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat=false;
                }
            }
        });
        imgradom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom==false){
                    if(repeat==true){
                        repeat = false;
                        imgradom.setImageResource(R.drawable.iconsuffle);
                        imgrepeat.setImageResource(R.drawable.iconshuffled);
                    }
                    imgradom.setImageResource(R.drawable.iconshuffled);
                    checkrandom=true;
                }else{
                    imgradom.setImageResource(R.drawable.iconsuffle);
                    checkrandom=false;
                }
            }
        });
        //keo den dau thif nhac chay den do
        seekBartime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying()||mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(position<(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat==true){
                            if(position==0){
                                position = mangbaihat.size();
                            }
                            position-=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index==position){
                                position=index-1;
                            }
                            position=index;
                        }
                        if(position>(mangbaihat.size()-1)){
                            position=0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getSLinkBH());
                        fragment_diaNhac.Playnhac(mangbaihat.get(position).getSHinhAnhBH());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getSTenBaiHat());
                        UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);

            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mangbaihat.size()>0){
                    if(mediaPlayer.isPlaying()||mediaPlayer!=null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer=null;
                    }
                    if(position<(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;
                        if(position<0){
                            position=mangbaihat.size()-1;
                        }
                        if(repeat==true){
                            if(position==0){
                                position = mangbaihat.size();
                            }
                            position+=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index==position){
                                position=index-1;
                            }
                            position=index;
                        }

                        new PlayMp3().execute(mangbaihat.get(position).getSLinkBH());
                        fragment_diaNhac.Playnhac(mangbaihat.get(position).getSHinhAnhBH());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getSTenBaiHat());
                            UpdateTime();
                    }
                }
                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },500);

            }
        });



    }

    private void GetDataFromIntent() {
        Intent intent=getIntent();
      mangbaihat.clear();
        if (intent != null) {
            if(intent.hasExtra("cakhuc"));
            {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                //   Toast.makeText(this, baiHat.getSTenBaiHat(), Toast.LENGTH_SHORT).show();
                mangbaihat.add(baiHat);

            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<BaiHat> mangbaihat1 = intent.getParcelableArrayListExtra("cacbaihat");
//            for (int i = 0; i<mangbaihat.size();i++){
//                Log.d("BBB", mangbaihat.get(i).getSTenBaiHat());
//            }
                mangbaihat = mangbaihat1;
            }
        }

    }

    private void init() {
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        txttimesong = findViewById(R.id.textviewtimesong);
        txttotaltimesong= findViewById(R.id.textviewtotaltimesong);
        seekBartime = findViewById(R.id.seekbarsong);
        imgplay = findViewById(R.id.imagebuttomplay);
        imgpre = findViewById(R.id.imagebuttompre);
        imgnext= findViewById(R.id.imagebuttomnext);
        imgradom= findViewById(R.id.imagebuttomsuffle);
        imgrepeat = findViewById(R.id.imagebuttomrepeat);
      //  imgclock = findViewById(R.id.imagebuttomoclock);
        viewPagerplaynhac = findViewById(R.id.viewpaperplaynhac);
      //btnDownload = findViewById(R.id.imagedownload);

        tvTimeSleep = (TextView) findViewById(R.id.tvTimeSleep);

        // imgCD = (CircleImageView) findViewById(R.id.imageViewCD);
        imvClock = (ImageView) findViewById(R.id.imvClock);
       setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarplaynhac.setNavigationOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    mangbaihat.clear();
                    finish();

                }
            });




        toolbarplaynhac.setTitleTextColor(Color.WHITE);
//gán hai frgment đĩa nhạc và pager danh sách bài hát vào playnhac
       fragment_diaNhac =new Fragment_DiaNhac();
        fragment_play_danh_sach_cac_bai_hat = new Fragment_Play_Danh_Sach_Cac_Bai_Hat();
        fragment_ds_cac_bai_hat = new Fragment_DS_Cac_Bai_Hat();
        adapternhac = new ViewPaperplaylistNhac(getSupportFragmentManager());
        adapternhac.AddFragment(fragment_play_danh_sach_cac_bai_hat);
       // adapternhac.AddFragment(fragment_ds_cac_bai_hat);
        adapternhac.AddFragment(fragment_diaNhac);
        viewPagerplaynhac.setAdapter(adapternhac);
        fragment_diaNhac = (Fragment_DiaNhac) adapternhac.getItem(1);
      //tự động pphát khi kích vào bài hát
        if(mangbaihat.size()>0){
            getSupportActionBar().setTitle(mangbaihat.get(0).getSTenBaiHat());
            new PlayMp3().execute(mangbaihat.get(0).getSLinkBH());

            imgplay.setImageResource(R.drawable.iconpause);


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_activity_timer, menu);
        getMenuInflater().inflate(R.menu.add_button_share, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int time;
        switch (item.getItemId()) {
            case R.id.itemshare:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mangbaihat.get(0).getSLinkBH());
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
                break;
            case R.id.action_10:
                time = 600000;
                stop_sleepp(time, 1000);
                break;
            case R.id.action_30:
                time = 1800000;
                stop_sleepp(time, 1000);
                break;
            case R.id.action_45:
                time = 2700000;
                stop_sleepp(time, 1000);
                break;
            case R.id.action_60:
                time = 3600000;
                stop_sleepp(time, 1000);
                break;
            case R.id.action_90:
                time = 5400000;
                stop_sleepp(time, 1000);
                break;
            case R.id.action_clear:
                timerSleep.cancel();
                tvTimeSleep.setVisibility(View.INVISIBLE);
                imvClock.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "Đã tắt hẹn giờ ", Toast.LENGTH_LONG).show();
                break;
//            case R.id.action_renew:
//                reNewLayout();
////                reloadContentView();
//                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void doStop() {
        mediaPlayer.stop();
        mediaPlayer.release();
        imgplay.setImageResource(R.drawable.iconplay);
        GetDataFromIntent();
    }
//chức năng thực hiện thứ tự để phát được 1 bài hát
    class PlayMp3 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
            mediaPlayer = new MediaPlayer();
            //stream_music là play nhạc dưới dạng online
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txttotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBartime.setMax(mediaPlayer.getDuration());
    }
    private  void UpdateTime(){
        final Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer!=null) {
                  seekBartime.setProgress(mediaPlayer.getCurrentPosition());
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                  txttimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                  handler.postDelayed(this, 300);
                  mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                      @Override
                      public void onCompletion(MediaPlayer mp) {
                          next = true;
                          try {
                              Thread.sleep(1000);
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }
                      }
                  });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next==true){
                    if(position<(mangbaihat.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat==true){
                            if(position==0){
                                position = mangbaihat.size();
                            }
                            position-=1;
                        }
                        if(checkrandom==true){
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if(index==position){
                                position=index-1;
                            }
                            position=index;
                        }
                        if(position>(mangbaihat.size()-1)){
                            position=0;
                        }
                        new PlayMp3().execute(mangbaihat.get(position).getSLinkBH());
                        fragment_diaNhac.Playnhac(mangbaihat.get(position).getSHinhAnhBH());
                        getSupportActionBar().setTitle(mangbaihat.get(position).getSTenBaiHat());
                    }

                imgpre.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgpre.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
                next = false;
                handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);

    }



    private class DownloadMusicFromInternet extends AsyncTask<String, String, String> {

        //hien thi progressbar truoc khi download

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //hien thi progressbar va sau do goi doInBackground
            showDialog(pr_type);
        }

        @Override
        protected String doInBackground(String... strings) {
            int count;
            try {
                URL url =new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                int lenghtFile = connection.getContentLength();
                // input stream to read file - with 8k buffer
                InputStream inputStream = new BufferedInputStream(url.openStream(),10*1024);
                // Output stream to write file in SD card
                if (!checkPermission()) {
                    openActivity();
                } else {
                    if (checkPermission()) {
                        requestPermissionAndContinue();
                    } else {
                        openActivity();
                    }
                }


                OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()
                        +"/Download"
                        +"/"+mangbaihat.get(currentSongIndex).getSTenBaiHat()+".mp3");
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    // Publish the progress which triggers onProgressUpdate method
                    publishProgress("" + (int) ((total * 100) / lenghtFile));

                    // Write data to file
                    output.write(data, 0, count);
                }
                // Flush output
                output.flush();
                // Close streams
                output.close();
                inputStream.close();


            }catch (Exception e){
                Log.e("error: ",e.getMessage());
            }
            return null;
        }
        // While Downloading Music File
        protected void onProgressUpdate(String... progress) {
            // Set progress percentage
            progressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        // Once Music File is downloaded
        @Override
        protected void onPostExecute(String file_url) {
            //Dismiss the dialog after the Music file was downloaded
            Toast.makeText(getApplicationContext(), "Download complete!",
                    Toast.LENGTH_SHORT).show();
       //   btnDownload.setImageResource(R.drawable.icondownload);
//            Intent intent = new Intent(PlayNhacActivity.this, MyMusicActivity.class);
//            intent.putExtra("danhsachbaihat",mangbaihat.get(position).getSLinkBH());
//            startActivity(intent);
        }
    }


    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle(getString(R.string.permission_necessary));
                alertBuilder.setMessage(R.string.storage_permission_is_encessary_to_wrote_event);
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(PlayNhacActivity.this, new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(PlayNhacActivity.this, new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            openActivity();
        }
    }
    private void openActivity() {
        //add your further process after giving permission or to download images from remote server.
    }
    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }
    private static final int PERMISSION_REQUEST_CODE = 200;

}
