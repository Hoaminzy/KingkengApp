package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.DanhsachbaihatAdapter;
import com.Hoaminzf.fithou.mykengkingapp.Model.AlBum;
import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.Model.PlayList;
import com.Hoaminzf.fithou.mykengkingapp.Model.QuangCao;
import com.Hoaminzf.fithou.mykengkingapp.Model.TheLoai;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
QuangCao quangCao;
PlayList playList;
CoordinatorLayout coordinatorLayout;
CollapsingToolbarLayout collapsingToolbarLayout;
Toolbar toolbar;
RecyclerView recyclerViewDanhsachbaihat;
FloatingActionButton floatingActionButton;
ImageView imgdanhsachcakhuc;
    ArrayList<BaiHat> mangbaihat;
    DanhsachbaihatAdapter danhsachbaihatAdapter;
    TheLoai theLoai;
    AlBum alBum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachbaihat);
        StrictMode.ThreadPolicy policy = new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxa();
        init();
        if(quangCao != null && !quangCao.getSTenBaiHat().equals("")){
            SetValueInView(quangCao.getSTenBaiHat(), quangCao.getSHinhAnhBH());
            GetdataQuangcao(quangCao.getPKIMaQuangCao());
        }
        if(playList !=null && !playList.getSTenPlayList().equals("")){
            SetValueInView(playList.getSTenPlayList(),playList.getSHinhAnhPL());
            GetDataPlaylist(playList.getPKIMaPlayList());
        }
        if(theLoai !=null && !theLoai.getSTenTheLoai().equals("")){

            SetValueInView(theLoai.getSTenTheLoai(),theLoai.getSHinhAnhTL());
            GetDataTheLoai(theLoai.getPKIMaTheLoai());
        }

        if(alBum !=null && !alBum.getSTenCaSiAB().equals("")){
            SetValueInView(alBum.getSTenAlBum(), alBum.getSHinhAnhAB());
            GetDataAlbum(alBum.getPKIMaAlBum());
        }

    }

    private void GetDataAlbum(String pkiMaAlBum) {
        DataService dataService =APIService.getService();
        Call<List<BaiHat>> callBack = dataService.GetDanhSachBaiHatTheoAlBlum(pkiMaAlBum);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenClick();


            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void GetDataTheLoai(String idtheloai){
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.GetDanhsachbaihatthetheloai(idtheloai);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenClick();


            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
    private void GetDataPlaylist( String idplaylist) {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> callBack = dataService.GetDanhsachbaihattheoplaylist(idplaylist);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);
                evenClick();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetdataQuangcao(final String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoquangcao( idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat= (ArrayList<BaiHat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(DanhSachBaiHatActivity.this, mangbaihat);
                recyclerViewDanhsachbaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhsachbaihat.setAdapter(danhsachbaihatAdapter);

                evenClick();
//                bigViews.setTextViewText(R.id.wgSongName, songVP3.get(songIndex).getSongname());
//                bigViews.setTextViewText(R.id.wgSingerName, songVP3.get(songIndex).getArtisname());


              //  notificationBuilder.setContentText(songVP3.get(songIndex).getArtisname());





            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void SetValueInView( String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url= new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN){
                collapsingToolbarLayout.setBackground(bitmapDrawable );
           }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgdanhsachcakhuc);
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);
    }


    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
       toolbar = findViewById(R.id.toolbardanhsachbaihathot);
        recyclerViewDanhsachbaihat = findViewById((R.id.recyclerviewdanhsachbaihat));
        floatingActionButton = findViewById(R.id.floatingactiobutton);
        imgdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);


    }

    private void DataIntent() {
        Intent intent= getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
               // Toast.makeText(this, quangCao.getSTenBaiHat(), Toast.LENGTH_LONG).show();
            }
            if(intent.hasExtra("itemplaylist")){
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if(intent.hasExtra("idtheloai")){
                theLoai= (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
              alBum  = (AlBum) intent.getSerializableExtra("album");
            }
        }
    }
    //danh sach phat
    private  void   evenClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this, PlayNhacActivity.class);
                intent.putExtra("cacbaihat", mangbaihat);
                startActivity(intent);
              // NotificationGegerator.CustomBigNotification(getApplicationContext());

            }
        });
    }

}
