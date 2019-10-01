package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Hoaminzf.fithou.mykengkingapp.Activity.DanhSachBaiHatActivity;
import com.Hoaminzf.fithou.mykengkingapp.Model.QuangCao;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_banner, null);

        ImageView imgbackgroundbanner = view.findViewById(R.id.imagebackgroudbanner);
        ImageView imgsongbanner = view.findViewById(R.id.imagebanner);
        TextView txttitlesongbanner = view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtnoidung = view.findViewById(R.id.textviewnoidung);

        Picasso.with(context).load(arrayListBanner.get(position).getSHinhAnhQC()).into(imgbackgroundbanner);
        Picasso.with(context).load(arrayListBanner.get(position).getSHinhAnhBH()).into(imgsongbanner);

        txttitlesongbanner.setText(arrayListBanner.get(position).getSTenBaiHat());
        txtnoidung.setText(arrayListBanner.get(position).getSNoiDung());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner", arrayListBanner.get(position));
         //       intent.putExtra("banner", arrayListBanner.get(position));
                context.startActivity(intent);

            }
        });
       container.addView(view);
        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }


}
