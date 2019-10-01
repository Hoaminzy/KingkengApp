package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.Hoaminzf.fithou.mykengkingapp.Model.TheLoai;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    Context context;
    ArrayList<TheLoai> mangtheloai;

    public TheLoaiAdapter(Context context, ArrayList<TheLoai> mangtheloai) {
        this.context = context;
        this.mangtheloai = mangtheloai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    TheLoai theLoai=mangtheloai.get(i);
        Picasso.with(context).load(theLoai.getSHinhAnhTL()).into(viewHolder.imghinhtheloai);
    }

    @Override
    public int getItemCount() {
        return mangtheloai.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhtheloai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhtheloai=itemView.findViewById(R.id.imageviewtheloai);

        }
    }
}
