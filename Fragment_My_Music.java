package com.Hoaminzf.fithou.mykengkingapp.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.MyMusicAdapter;
import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_My_Music extends Fragment {
     View view;
    RecyclerView recyclerViewmymusic;
    MyMusicAdapter myMusicAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my_music, container,false);
        recyclerViewmymusic=view.findViewById(R.id.recycleviewMyMusic);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService= APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetAllBaiHat();

        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> allBaiHatArrayList = (ArrayList<BaiHat>) response.body();
                myMusicAdapter = new MyMusicAdapter(getActivity(), allBaiHatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewmymusic.setLayoutManager(linearLayoutManager);
                recyclerViewmymusic.setAdapter(myMusicAdapter);

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
