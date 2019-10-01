package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.DanhsachtatcaChuDeHotAdapter;
import com.Hoaminzf.fithou.mykengkingapp.Model.ChuDe;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaChuDeActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllChude;
    Toolbar toolbarallChude;
    DanhsachtatcaChuDeHotAdapter danhsachtatcaChuDeHotAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_chu_de);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callBack = dataService.GetAllChuDe();
        callBack.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude =(ArrayList<ChuDe>) response.body();
              //  Log.d("BBB", mangchude.get(0).getSTenChuDe());
                danhsachtatcaChuDeHotAdapter = new DanhsachtatcaChuDeHotAdapter(DanhsachtatcaChuDeActivity.this, mangchude);
                recyclerViewAllChude.setLayoutManager((new GridLayoutManager(DanhsachtatcaChuDeActivity.this, 1)));
                recyclerViewAllChude.setAdapter(danhsachtatcaChuDeHotAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });

    }

    private void init() {
        recyclerViewAllChude = findViewById(R.id.recyclerviewAllchude);
        toolbarallChude = findViewById(R.id.toolbarallchude);
        setSupportActionBar(toolbarallChude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Các Chủ Đề");
        toolbarallChude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
