package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.MainViewPaperAdapter;
import com.Hoaminzf.fithou.mykengkingapp.Fragment.Fragment_TimKiem;
import com.Hoaminzf.fithou.mykengkingapp.Fragment.Fragment_Trangchu;
import com.Hoaminzf.fithou.mykengkingapp.R;

public class MainActivity  extends AppCompatActivity  {
   TabLayout tabLayout;
   ViewPager viewPager;
    public RelativeLayout control_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        init();
    }

    private void init() {
        MainViewPaperAdapter mainViewPaperAdapter=new MainViewPaperAdapter(getSupportFragmentManager());
        mainViewPaperAdapter.addFragment(new Fragment_Trangchu(),"Trang chu");
        mainViewPaperAdapter.addFragment(new Fragment_TimKiem(), "Tim Kiem");
      //  mainViewPaperAdapter.addFragment(new Fragment_Ca_Nhan(), "Cá Nhân");
        viewPager.setAdapter(mainViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
        //tabLayout.getTabAt(2).setIcon(R.drawable.user);


    }

    private  void anhxa(){
    tabLayout=(TabLayout) findViewById(R.id.myTabLayout);
    viewPager=(ViewPager) findViewById(R.id.myViewPaper);
    }


}
