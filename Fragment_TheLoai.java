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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.TheLoaiAdapter;
import com.Hoaminzf.fithou.mykengkingapp.Model.TheLoai;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TheLoai extends Fragment {
    View view;
    RecyclerView recyclerViewtheloai;
    TextView txtxemthemtheloai;
    TheLoaiAdapter theLoaiAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_theloai, container, false);
        recyclerViewtheloai=view.findViewById(R.id.recycleviewTheloai);
        txtxemthemtheloai= view.findViewById(R.id.textviewxemthemtheloai);
        GetData();
        return view;
    }
    private void GetData(){
        DataService dataService= APIService.getService();
        Call<List<TheLoai>> callBack =dataService.GetTheLoai();
        callBack.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
          ArrayList<TheLoai> theLoaiArrayList=(ArrayList<TheLoai>) response.body();
          theLoaiAdapter=new TheLoaiAdapter(getActivity(),theLoaiArrayList);
                LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerViewtheloai.setLayoutManager(linearLayoutManager);
                recyclerViewtheloai.setAdapter(theLoaiAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }
}
