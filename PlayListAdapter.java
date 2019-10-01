package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Hoaminzf.fithou.mykengkingapp.Model.PlayList;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<PlayList> {

    public PlayListAdapter(@NonNull  Context context, int resource, @NonNull List<PlayList> objects) {
        super(context, resource, objects);
    }
    class ViewHolder{
        TextView txttenplaylist;
        ImageView imgbackground, imgplaylist;
    }

   // @androidx.annotation.NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null)
        {
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.dong_playlist, null);
            viewHolder= new ViewHolder();
            viewHolder.txttenplaylist=convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgplaylist=convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgbackground=convertView.findViewById(R.id.imageviewbackgroundplaylist);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        PlayList playList=getItem(position);
        Picasso.with(getContext()).load(playList.getSHinhAnhPL()).into(viewHolder.imgbackground);
        Picasso.with(getContext()).load(playList.getSHinhIcon()).into(viewHolder.imgplaylist);


        viewHolder.txttenplaylist.setText(playList.getSTenPlayList());

        return convertView;
    }
}
