package com.Hoaminzf.fithou.mykengkingapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.BannerAdapter;
import com.Hoaminzf.fithou.mykengkingapp.Model.QuangCao;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.Service.APIService;
import com.Hoaminzf.fithou.mykengkingapp.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_banner,container, false);
        anhxa();
        GetData();
        return view;
    }
    public void anhxa(){
        viewPager=view.findViewById(R.id.viewpager);
        circleIndicator= view.findViewById(R.id.indicatordefult);
    }
    private void GetData(){
        DataService dataService= APIService.getService();
        Call<List<QuangCao>> callBack=dataService.GetDataBanner();
        callBack.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banners=(ArrayList<QuangCao>) response.body();
               // Log.d("BBA", banners.get(0).getSTenBaiHat());
              bannerAdapter=new BannerAdapter(getActivity(), banners);
              viewPager.setAdapter(bannerAdapter);
              circleIndicator.setViewPager(viewPager);
              //hanler và runnable: xét thời gian chuyển của từng pager
            handler=new Handler();
            runnable= new Runnable() {
                @Override
                public void run() {
                    currentItem=viewPager.getCurrentItem();
                    currentItem++;
                    //nếu gọi pager lớn hơn số pager có thì quay về pager 1
                    if(currentItem>=viewPager.getAdapter().getCount()){
                        currentItem=0;
                    }
                    viewPager.setCurrentItem(currentItem,true);
                    handler.postDelayed(runnable, 4500);


                }
            };
            handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });
    }
}
