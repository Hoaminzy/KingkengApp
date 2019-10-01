package com.Hoaminzf.fithou.mykengkingapp.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Hoaminzf.fithou.mykengkingapp.Activity.PlayNhacActivity;
import com.Hoaminzf.fithou.mykengkingapp.Model.dowloadBH;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CanhanAdapter extends BaseAdapter {
    Activity myContext;
    int myLayout;
    List<dowloadBH> lstdowload;

    public CanhanAdapter(Activity myContext, int myLayout, List<dowloadBH> lstdowload) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.lstdowload = lstdowload;
    }

    @Override
    public int getCount() {
        return lstdowload.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolde{
        ImageView imageView;
        TextView txt1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolde holder;
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(myLayout,null);
            holder=new ViewHolde();
            holder.txt1=(TextView)convertView.findViewById(R.id.itemtenbaihat);
            holder.imageView=(ImageView)convertView.findViewById(R.id.itemanh1);
            convertView.setTag(holder);
        }else {
            holder=(ViewHolde)convertView.getTag();
        }
        holder.txt1.setText(lstdowload.get(position).getTenbaihat());
    //    holder.txt2.setText(lstdowload.get(position).getCasi());
        Picasso.with(myContext).load(lstdowload.get(position).getAnh()).into(holder.imageView);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(myContext, PlayNhacActivity.class);
                myContext.startActivity(intent);
            }
        });


        return convertView;
    }
}

