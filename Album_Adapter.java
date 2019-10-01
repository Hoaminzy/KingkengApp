package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Hoaminzf.fithou.mykengkingapp.Activity.DanhSachBaiHatActivity;
import com.Hoaminzf.fithou.mykengkingapp.Model.AlBum;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Album_Adapter extends RecyclerView.Adapter<Album_Adapter.ViewHolder> {
    Context context;
    ArrayList<AlBum> mangalbum;

    public Album_Adapter(Context context, ArrayList<AlBum> mangalbum) {
        this.context = context;
        this.mangalbum = mangalbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      AlBum alBum = mangalbum.get(i);
      viewHolder.txttencasialbum.setText(alBum.getSTenCaSiAB());
      viewHolder.txttenalbum.setText(alBum.getSTenAlBum());
        Picasso.with(context).load(alBum.getSHinhAnhAB()).into(viewHolder.imghinhalbum);
    }

    @Override
    public int getItemCount() {
        return mangalbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
 ImageView imghinhalbum;
 TextView txttenalbum, txttencasialbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhalbum = itemView.findViewById(R.id.imageviewalbum);
            txttenalbum = itemView.findViewById(R.id.textviewtenalbum);
            txttencasialbum= itemView.findViewById(R.id.textviewtencasi);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album", mangalbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
