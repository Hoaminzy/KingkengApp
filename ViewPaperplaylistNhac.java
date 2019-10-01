package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPaperplaylistNhac extends FragmentPagerAdapter {
    public  final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    public ViewPaperplaylistNhac(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentArrayList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();

    }
    public void AddFragment( Fragment fragment){
        fragmentArrayList.add(fragment);
    }
}
