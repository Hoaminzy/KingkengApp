package com.Hoaminzf.fithou.mykengkingapp.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.Hoaminzf.fithou.mykengkingapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_DiaNhac extends Fragment {
    CircleImageView circleImageView;
    View view;
    ObjectAnimator objectAnimator;
    Animation animation;
    Context context;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragnemt_dia_nhac, container, false);
        circleImageView = view.findViewById(R.id.imageviewcirle);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f);
        animation= AnimationUtils.loadAnimation(getActivity(), R.anim.disk_ratate);
        //       //set thoi gian chay trong 10s
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //chay xong tiep tuc chay
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        //chay trong trach tinh trang ngung 1 luc mowi chay tiep(tuyen tinh k doi)
        objectAnimator.setInterpolator(new LinearInterpolator());

        return view;
    }
    public void Playnhac(String hinhanh) {
        Picasso.with(context).load(hinhanh).into(circleImageView);
        circleImageView.startAnimation(animation);
   }

}
