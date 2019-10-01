package com.Hoaminzf.fithou.mykengkingapp.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPaperAdapter extends FragmentPagerAdapter {

private ArrayList<Fragment> arrayFragment=new ArrayList<>();
    private ArrayList<String> arrayLitle=new ArrayList<>();
    public MainViewPaperAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int posision) {
        return arrayFragment.get(posision);
    }

    @Override
    public int getCount() {
        return arrayFragment.size();
    }
    public void addFragment(Fragment fragment, String title){
       arrayFragment.add(fragment);
       arrayLitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayLitle.get(position);
    }
}
