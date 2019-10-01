package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.R;

import java.util.ArrayList;
import java.util.Random;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class NotificationGegerator extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    ////////////////////////////////////
    public static final String NOTIFY_PREVIOUS = "com.Hoaminzf.fithou.mykengkingapp.Activity.previous";
    public static final String NOTIFY_DELETE = "com.Hoaminzf.fithou.mykengkingapp.Activity.delete";
    public static final String NOTIFY_PAUSE = "com.Hoaminzf.fithou.mykengkingapp.Activity.pause";
    public static final String NOTIFY_PLAY = "com.Hoaminzf.fithou.mykengkingapp.Activity.play";
    public static final String NOTIFY_NEXT = "com.Hoaminzf.fithou.mykengkingapp.Activity.next";

    private static final int NOTIFICATION_ID_OPEN_ACTIVITY_DSBAIHAT = 9;
 //  private final IBinder musicBind = new MusicBinder();
    private MediaPlayer player;
    private ArrayList<BaiHat> songs;
    private int songPos;

    private String songTitle = "";
    private static final int NOTIFY_ID = 1;
    private boolean shuffle = false;
    private Random rand;
    private static int key;


//    public static NotificationGegerator instance;
//    public static int pos = 0;
//    public static List<BaiHat> list;
//    public static MediaPlayer mediaPlayer;
//    public static NotificationManager manager;
//    public static NotificationManagerCompat mNotificationManager;
//    public static NotificationCompat.Builder builder;
//    public static Notification notification;
//    public static RemoteViews contentView;
//    public static final String CHANNEL_ID = "1234";

  //  @androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (player.getCurrentPosition() > 0) {
            mediaPlayer.reset();
            next();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mp.reset();
     return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }


//    private void createNotificationChannel() {
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name, importance);
//            channel.setDescription(description);
//            // Register the channel with the system; you can't change the importance
//            // or other notification behaviors after this
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
//        }
//    }
////    public  static  void openActivityNotification(Context context){
////        NotificationCompat.Builder nc= new NotificationCompat.Builder(context);
////        NotificationManager nn= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
////        Intent notifyIntent  = new Intent(context, MainActivity.class);
////        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
////       PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
////        nc.setContentIntent(pendingIntent);
////
////        nc.setSmallIcon(R.mipmap.ic_launcher);
////        nc.setAutoCancel(true);
////        nc.setContentTitle("Notification");
////        nc.setContentText("Click please");
////
////        nn.notify(NOTIFICATION_ID_OPEN_ACTIVITY_DSBAIHAT , nc.build());
////    }
//
//
    @SuppressLint("RestrictedApi")
    public static void CustomBigNotification(Context context){

     ArrayList<BaiHat> mangbaihat = new ArrayList<>();
     RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.dong_baihat_dang_phat);
      expandedView.setImageViewResource(R.id.wgImageView, R.drawable.logo);

    // Create an explicit intent for an Activity in your app
        NotificationManager nn = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notifiIntent = new Intent(context,MainActivity.class);


       notifiIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       notifiIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

       //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifiIntent, PendingIntent.FLAG_UPDATE_CURRENT);

       PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifiIntent, PendingIntent.FLAG_CANCEL_CURRENT);

      // // PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notifiIntent,0);
        NotificationCompat.Builder nc = new NotificationCompat.Builder(context);
        nc.setContentIntent(pendingIntent);
        nc.setSmallIcon(R.drawable.iconplay);
//        nc.setContentTitle("vẫn yêu đấy thôi");
//        nc.setContentText("Chi Dân");
        nc.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // Create an explicit intent for an Activity in your app
        nc.setCustomBigContentView(expandedView);
        nc.setOngoing(true);
       // nc.setAutoCancel(true);

        //expandedView.setTextViewText(R.id.textSongName,"");
       // bigViews.setTextViewText(R.id.wgSongName, songHome.get(songIndex).getSongname());

      // nc.getBigContentView().setTextViewText(R.id.textSingerName, "cai");


        setListener(expandedView, context);
       // nn.notify(NOTIFICATION_ID_OPEN_ACTIVITY_DSBAIHAT , nc.build());
        nc.setContentIntent(pendingIntent);
        nn.notify(0, nc.build());

    }

    private static  void setListener(RemoteViews views, Context context){
        Intent previous = new Intent(NOTIFY_PREVIOUS);
        Intent delete = new Intent(NOTIFY_DELETE);
        Intent pause = new Intent(NOTIFY_PAUSE);
        Intent play = new Intent(NOTIFY_PLAY);
        Intent next = new Intent( NOTIFY_NEXT);

        PendingIntent pPrevious = PendingIntent.getBroadcast(context, 0, previous, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.btnPrevious, pPrevious);

//        PendingIntent pDelete = PendingIntent.getBroadcast(context, 0, delete, PendingIntent.FLAG_CANCEL_CURRENT);
//        views.setOnClickPendingIntent(R.id.btnDelete, pDelete);

        PendingIntent pPause = PendingIntent.getBroadcast(context, 0,pause, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.btnPause, pPause);

        PendingIntent pPlay = PendingIntent.getBroadcast(context, 0, play, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.btnPlay, pPlay);

        PendingIntent pNext = PendingIntent.getBroadcast(context, 0, next, PendingIntent.FLAG_CANCEL_CURRENT);
        views.setOnClickPendingIntent(R.id.btnNext, pNext);



    }


//        @Override
//        public IBinder onBind (Intent intent){
//
//
//            return musicBind;
//
//
//        }
//
//



    /////////////////




    /////////////
        @Override
        public void onCreate () {

            super.onCreate();
            songPos = 0;
            player = new MediaPlayer();

        }

        public void initMusicPlayer(Context context) {

//            try {
//                if (mediaPlayer == null)
//                    mediaPlayer = MediaPlayer.create(context, Uri.parse(NotificationGegerator.list.get(pos).getPKIMaBaiHat()));
//            } catch (IndexOutOfBoundsException e){
//
//            }
            player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
          //  player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(this);
            player.setOnErrorListener(this);
            player.setOnCompletionListener(this);
        }
        public void setList (ArrayList < BaiHat > theSongs) {
            songs = theSongs;
        }
        public class MusicBinder extends Binder {
            NotificationGegerator getService() {
                return NotificationGegerator.this;
            }
        }

        @Override
        public int onStartCommand (Intent intent,int flags, int startId){
            String action = intent.getAction();
            switch (action) {
                case NOTIFY_PREVIOUS:
                    previous();
                    break;
                case NOTIFY_PLAY:
                    play();
                    break;
                case NOTIFY_DELETE:
                    delete();
                    break;
                case NOTIFY_PAUSE:
                    pause();
                    break;
                case NOTIFY_NEXT:
                    next();
                    break;
            }
            return START_STICKY;
        }



//
//    public static void newNotification(Context context) {
//        builder = new NotificationCompat.Builder(context, CHANNEL_ID);
//        builder.setContentTitle("My Music");
//        builder.setContentText("thông báo abcd");
//        builder.setSmallIcon(R.drawable.iconnhac);
//        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
//                R.drawable.iconnhac));
//        contentView = new RemoteViews(context.getPackageName(), R.layout.notification);
//        builder.setCustomContentView(contentView);
//        Intent iPrevious = new Intent(NOTIFY_PREVIOUS);
//        Intent iNext = new Intent(NOTIFY_NEXT);
//        Intent iPlay = new Intent(NOTIFY_PLAY);
//        Intent iExit = new Intent(NOTIFY_DELETE);
//        iPrevious.setClass(context, NotificationGegerator.class);
//        iNext.setClass(context, NotificationGegerator.class);
//        iPlay.setClass(context, NotificationGegerator.class);
//        iExit.setClass(context,NotificationGegerator.class);
//        PendingIntent pPrevious = PendingIntent.getBroadcast(context, 0, iPrevious, 0);
//        PendingIntent pNext = PendingIntent.getBroadcast(context, 0, iNext, 0);
//        PendingIntent pPlay = PendingIntent.getBroadcast(context, 0, iPlay, 0);
//        PendingIntent pExit = PendingIntent.getBroadcast(context, 0, iExit, 0);
//        contentView.setOnClickPendingIntent(R.id.bt_Previous, pPrevious);
//        contentView.setOnClickPendingIntent(R.id.bt_Next, pNext);
//        contentView.setOnClickPendingIntent(R.id.bt_Play, pPlay);
//        contentView.setOnClickPendingIntent(R.id.bt_Exit, pExit);
//        notification = builder.build();
//      //NotificationGegerator.instance.startForeground(1, notification);
//    }
//    /////////////
//    private void createNotificationChannel() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Music";
//            int importance = NotificationManager.IMPORTANCE_LOW;
//            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//            manager.createNotificationChannel(mChannel);
//        }
//    }
//    public static void updateNotification() {
//        contentView.setTextViewText(R.id.tv_Song, list.get(pos).getSTenBaiHat());
//        contentView.setTextViewText(R.id.tv_Artist, list.get(pos).getSHinhAnhBH());
//        Uri uri = null;
//        try {
//            uri = Uri.parse(list.get(pos).getPKIMaBaiHat());
//        } catch (NullPointerException e) {
//        }
//        if (uri != null)
//            contentView.setImageViewUri(R.id.image_Song, uri);
//        else contentView.setImageViewResource(R.id.image_Song, R.drawable.iconnhac);
//
//        mNotificationManager.notify(1, notification);
//    }
    ////////////////////////////
    @Override
        public void onDestroy () {
            stopForeground(true);
        }
        private void next () {

            if (shuffle) {
                int newSongPos = songPos;
                while (newSongPos == songPos) {
                    newSongPos = rand.nextInt(songs.size());
                }
                songPos = newSongPos;
            } else {
                songPos++;
                if (songPos >= songs.size()) songPos = 0;
            }

        }
    private void previous() {
        songPos--;
        if (songPos < 0) songPos = songs.size() - 1;
        play();

    }
        private void pause () {
            player.pause();
        }

        private void delete () {
            player.release();
        }

        private  void play() {
        ///////////////////
          //  initMusicPlayer(context);
//            if (mediaPlayer.isPlaying()) {
//                mediaPlayer.pause();
//                contentView.setImageViewResource(R.id.bt_Play, R.drawable.iconplay);
//                updateNotification();
//                PlayNhacActivity.imgplay.setImageResource(R.drawable.iconpause);
//                try {
//                    PlayNhacActivity.imgplay.setImageResource(R.drawable.iconplay);
//                } catch (NullPointerException e) {
//                }
//            } else {
//                PlayNhacActivity.imgplay.setImageResource(R.drawable.iconpause);
//                if (notification == null) {
//                    //newNotification(context);
//                }
//                contentView.setImageViewResource(R.id.bt_Play, R.drawable.iconplay);
//                updateNotification();
//                try {
//                    PlayNhacActivity.imgplay.setImageResource(R.drawable.iconpause);
//                } catch (NullPointerException e) {
//                }
//                mediaPlayer.start();
//            }
//           setMediaPlayer(mediaPlayer);
///////////////////////////////

////            player.reset();
////            BaiHat playSong = songs.get(songPos);
////            songTitle = playSong.getSTenBaiHat();
////            String currentSong = playSong.getPKIMaBaiHat();
////            Uri trackUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.parseLong(currentSong));
////            try {
////                player.setDataSource(getApplicationContext(), trackUri);
////            } catch (Exception e) {
////                Log.e("MUSIC SERVICE", "Error starting data source", e);
////            }
////            player.prepareAsync();
//        }




//        @Override
//        public void onCompletion (MediaPlayer mp){
//            if (player.getCurrentPosition() > 0) {
//                mp.reset();
//                next();
//            }
//        }
//        @Override
//        public boolean onError (MediaPlayer mp,int what, int extra){
//            mp.reset();
//            return false;
//        }

//        @Override
//        public void onPrepared (MediaPlayer mp){
//            mp.start();
//            Intent notificationIntent = new Intent(this, MainActivity.class);
//            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(
//                    this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
//            );
//    //   RemoteViews expandedView = new RemoteViews(context.getpackgetName R.layout.dong_baihat_dang_phat);
////       expandedView.setImageViewResource(R.id.wgImageView, R.drawable.logo);
//            Notification.Builder nBuilder = new Notification.Builder(this);
//            nBuilder.setContentIntent(pendingIntent)
//                    .setTicker(songTitle)
//                    .setSmallIcon(R.drawable.iconplay)
//                    .setOngoing(true)
//                    .setContentTitle("Playing")
//                    .setContentText(songTitle);
//            Notification notif = nBuilder.getNotification();
//            startForeground(NOTIFY_ID, notif);
//        }
//        @Override
//        public boolean onUnbind (Intent intent){
//            player.stop();
//            player.release();
//            return false;
//        }

//    public static void setMediaPlayer(MediaPlayer mediaPlayer) {
//       NotificationGegerator.mediaPlayer = mediaPlayer;
//    }

}
    }
