package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Hoaminzf.fithou.mykengkingapp.Activity.NotificationGegerator;
import com.Hoaminzf.fithou.mykengkingapp.Activity.PlayNhacActivity;
import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihathotAdapter extends RecyclerView.Adapter<BaihathotAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaihathotAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.dong_bai_hat_hot, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.txttencasi.setText(baiHat.getSCaSi());
        viewHolder.txtten.setText(baiHat.getSTenBaiHat());
        Picasso.with(context).load(baiHat.getSHinhAnhBH()).into(viewHolder.imghinh);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten, txttencasi;
        ImageView imghinh, imgluotthich;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten=itemView.findViewById(R.id.textviewtenbaihathot);
            txttencasi=itemView.findViewById(R.id.textviewtencasihot);
            imghinh=itemView.findViewById(R.id.imageviewbaihathot);
            imgluotthich=itemView.findViewById(R.id.imageviewluotthich);
            //chuyển sang phaynhac khí khích vào bài hát
            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);

                   NotificationGegerator.CustomBigNotification(context.getApplicationContext());
                }
            });
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(context, baiHatArrayList.get(getPosition()).getSTenBaiHat(), Toast.LENGTH_SHORT).show();
                 imgluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService= APIService.getService();
                    Call<String> callBack = dataService.UpdateLuotThich("1", baiHatArrayList.get(getPosition()).getPKIMaBaiHat());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("yes")){
                                Toast.makeText(context, "Da Thich!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Bo Thich!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgluotthich.setEnabled(false);

                }
            });


        }
    }
}
