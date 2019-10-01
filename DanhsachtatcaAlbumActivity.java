package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.AllAlbumAdater;
import com.Hoaminzf.fithou.mykengkingapp.Model.AlBum;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaAlbumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllalbum;
    Toolbar toolbaralbum;
    AllAlbumAdater allAlbumAdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_album);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<AlBum>> callBack = dataService.GetAllAlbum();
        callBack.enqueue(new Callback<List<AlBum>>() {
            @Override
            public void onResponse(Call<List<AlBum>> call, Response<List<AlBum>> response) {
                ArrayList<AlBum> mangalbum = (ArrayList<AlBum>) response.body();
               // Log.d("BBB", mangalbum.get(0).getSTenAlBum());
                allAlbumAdater = new AllAlbumAdater(DanhsachtatcaAlbumActivity.this, mangalbum);
                recyclerViewAllalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaAlbumActivity.this, 2));
                recyclerViewAllalbum.setAdapter(allAlbumAdater);
            }

            @Override
            public void onFailure(Call<List<AlBum>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllalbum = findViewById(R.id.recyclerviewAllalbum);
        toolbaralbum = findViewById(R.id.toolbaralbum);
        setSupportActionBar(toolbaralbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả AlBum");
        toolbaralbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
