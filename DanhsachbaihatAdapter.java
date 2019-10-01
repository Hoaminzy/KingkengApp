package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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

import com.Hoaminzf.fithou.mykengkingapp.Activity.PlayNhacActivity;
import com.Hoaminzf.fithou.mykengkingapp.Model.BaiHat;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> mangbaihat;
    MediaPlayer mediaPlayer;

    public DanhsachbaihatAdapter(Context context, ArrayList<BaiHat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }


    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, viewGroup,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
      BaiHat baiHat= mangbaihat.get(i);
      viewHolder.txtcasi.setText(baiHat.getSCaSi());
      viewHolder.txttenbaihat.setText(baiHat.getSTenBaiHat());
      viewHolder.txtindex.setText(i + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
      TextView txtindex, txttenbaihat, txtcasi;
      ImageView imaluotthich;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtcasi=itemView.findViewById(R.id.textviewtencasi);
            txttenbaihat=itemView.findViewById(R.id.textviewtenbaihat);
            txtindex= itemView.findViewById(R.id.textviewdanhsachindex);
            imaluotthich= itemView.findViewById(R.id.imageviewdanhsachbaihatyeuthich);
            imaluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Toast.makeText(context, baiHatArrayList.get(getPosition()).getSTenBaiHat(), Toast.LENGTH_SHORT).show();
                    imaluotthich.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callBack = dataService.UpdateLuotThich("1", mangbaihat.get(getPosition()).getPKIMaBaiHat());
                    callBack.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if (ketqua.equals("yes")) {
                                Toast.makeText(context, "Da Thich!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Bo Thich!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imaluotthich.setEnabled(false);


                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayNhacActivity.class);
                    intent.putExtra("cakhuc", mangbaihat.get(getPosition()));
                    context.startActivity(intent);

                //  NotificationGegerator.CustomBigNotification(context);

                 //   NotificationBroadcast.PendingResult(context.getApplicationContext());



                }
            });
        }
    }
}
