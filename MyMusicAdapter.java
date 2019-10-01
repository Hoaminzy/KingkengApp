package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;

import java.util.ArrayList;

public class MyMusicAdapter extends RecyclerView.Adapter<MyMusicAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> allbaiHatArrayList;

    public MyMusicAdapter(Context context, ArrayList<BaiHat> allbaiHatArrayList) {
        this.context = context;
        this.allbaiHatArrayList = allbaiHatArrayList;
    }



    @NonNull
    @Override
    public MyMusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyMusicAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
