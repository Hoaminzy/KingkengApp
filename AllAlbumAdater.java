package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Hoaminzf.fithou.mykengkingapp.Activity.DanhSachBaiHatActivity;
import com.Hoaminzf.fithou.mykengkingapp.Activity.DanhsachtatcaAlbumActivity;
import com.Hoaminzf.fithou.mykengkingapp.Model.AlBum;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdater extends RecyclerView.Adapter<AllAlbumAdater.ViewHolder> {
    Context context;
    ArrayList<AlBum> alBumArrayList;

    public AllAlbumAdater(Context context, ArrayList<AlBum> alBumArrayList) {
        this.context = context;
        this.alBumArrayList = alBumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_all_album,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AlBum alBum = alBumArrayList.get(i);
        Picasso.with(context).load(alBum.getSHinhAnhAB()).into(viewHolder.imgallalbum);
        viewHolder.txttenallalbum.setText(alBum.getSTenAlBum());
    }

    @Override
    public int getItemCount() {
        return alBumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgallalbum;
        TextView txttenallalbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgallalbum = itemView.findViewById(R.id.imageviewallalbum);
            txttenallalbum = itemView.findViewById(R.id.textviewtenallalbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album", alBumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
