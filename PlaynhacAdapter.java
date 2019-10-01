package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.R;

import java.util.ArrayList;

public class PlaynhacAdapter extends RecyclerView.Adapter<PlaynhacAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;
    ArrayList<BaiHat> chonBaiHats;


    MediaPlayer mediaPlayer;
    public PlaynhacAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
        this.chonBaiHats = chonBaiHats;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat= mangbaihat.get(i);
        viewHolder.txtCasi.setText(baiHat.getSCaSi());
        viewHolder.txtindex.setText(i+1+"");
        viewHolder.txttenbaihat.setText(baiHat.getSTenBaiHat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
            TextView txtindex, txttenbaihat, txtCasi;
        public ViewHolder(@NonNull final View itemView) {

            super(itemView);
            txtCasi= itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex = itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat= itemView.findViewById(R.id.textviewplaynhactenbaihat);
            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {

//                    Intent intent = new Intent(context, PlayNhacActivity.class);
//                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
//                    context.startActivity(intent);
                  //  NotificationGegerator.CustomBigNotification(context);

                    //   NotificationBroadcast.PendingResult(context.getApplicationContext());
                }

            });

        }
    }
}
