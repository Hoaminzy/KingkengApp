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
import com.Hoaminzf.fithou.mykengkingapp.Activity.DanhsachcacplaylistActivity;
import com.Hoaminzf.fithou.mykengkingapp.Model.PlayList;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachcacplaylistAdapter extends RecyclerView.Adapter<DanhsachcacplaylistAdapter.ViewHoider> {
    Context context;
    ArrayList<PlayList> mangplaylist;

    public DanhsachcacplaylistAdapter(Context context, ArrayList<PlayList> mangplaylist) {
        this.context = context;
        this.mangplaylist = mangplaylist;
    }

    @NonNull
    @Override
    public ViewHoider onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist, viewGroup, false);

        return new ViewHoider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoider viewHoider, int i) {
        PlayList playList= mangplaylist.get(i);
        Picasso.with(context).load(playList.getSHinhIcon()).into(viewHoider.imghinhnen);
        viewHoider.txttenplaylist.setText(playList.getSTenPlayList());
    }

    @Override
    public int getItemCount() {
        return mangplaylist.size();
    }

    public  class ViewHoider extends RecyclerView.ViewHolder{
        ImageView imghinhnen;
        TextView txttenplaylist;
        public ViewHoider(@NonNull View itemView) {
            super(itemView);
            imghinhnen = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist = itemView.findViewById(R.id.textviewdanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist", mangplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
