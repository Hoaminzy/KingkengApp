package com.Hoaminzf.fithou.mykengkingapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.Hoaminzf.fithou.mykengkingapp.Activity.DanhSachBaiHatActivity;
import com.Hoaminzf.fithou.mykengkingapp.Activity.DanhsachtatcaAlbumActivity;
import com.Hoaminzf.fithou.mykengkingapp.Adapter.Album_Adapter;
import com.Hoaminzf.fithou.mykengkingapp.Model.AlBum;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Album_Hot extends Fragment {
    View view;
    RecyclerView recyclerViewAlbum;
    TextView txtxemthemalbum;
    Album_Adapter album_adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_album_hot, container,false);
       recyclerViewAlbum = view.findViewById(R.id.recycleviewalbum);
       txtxemthemalbum = view.findViewById(R.id.textviewxemthemalbum);
       txtxemthemalbum.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), DanhsachtatcaAlbumActivity.class);
               startActivity(intent);
           }
       });
       GetDate();
        return view;
    }

    private void GetDate() {
        DataService dataService = APIService.getService();
        Call<List<AlBum>> callBack = dataService.GetAlbumHot();
        callBack.enqueue(new Callback<List<AlBum>>() {
            @Override
            public void onResponse(Call<List<AlBum>> call, Response<List<AlBum>> response) {
                final ArrayList<AlBum> alBumArrayList = (ArrayList<AlBum>) response.body();
                album_adapter=new Album_Adapter(getActivity(),alBumArrayList);

                LinearLayoutManager linearLayoutManager= new LinearLayoutManager((getActivity()));
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewAlbum.setLayoutManager(linearLayoutManager);
                recyclerViewAlbum.setAdapter(album_adapter);

            }

            @Override
            public void onFailure(Call<List<AlBum>> call, Throwable t) {

            }
        });
    }
}
