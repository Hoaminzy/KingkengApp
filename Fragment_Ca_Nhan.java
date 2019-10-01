package com.Hoaminzf.fithou.mykengkingapp.Fragment;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.Hoaminzf.fithou.mykengkingapp.Adapter.CanhanAdapter;
import com.Hoaminzf.fithou.mykengkingapp.Model.dowloadBH;
import com.Hoaminzf.fithou.mykengkingapp.R;
import com.Hoaminzf.fithou.mykengkingapp.db.Database;

import java.util.ArrayList;

public class Fragment_Ca_Nhan extends Fragment {
    ListView recycleviewcanhan;
    TextView txtcanhan;
    ArrayList<dowloadBH> mang;
    CanhanAdapter adapter;
    Database database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ca_nhan, container, false);
        txtcanhan=view.findViewById(R.id.tvcanhan);
        anhxa(view);
        GetData();

        return view;
    }

    private void GetData() {
        //gọi lại database từ playnhacActivity và cursor để lấy tất cả dữ liệu ở bảng
        database=new Database(getActivity(),"mymusicDB.sqlite",null,2);
        Cursor cursor=database.getdata("SELECT * FROM tblmymusic");
        //Cursor cursor=database.getdata("Select * from tblmymusic");
        mang.clear();
        //xét mảng cursor nếu nó tổng danh sách  nhỏ hơn  thì trả về dữ liệu
        for (int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String tenbh=cursor.getString(1);
            String anh=cursor.getString(2);
            String link=cursor.getString(3);

            mang.add(new dowloadBH(id,tenbh,link,anh));
        }
        adapter.notifyDataSetChanged();
    }

    private void anhxa(View view) {
        recycleviewcanhan=(ListView)view.findViewById(R.id.lstcanhandl);

        mang=new ArrayList<>();
        adapter=new CanhanAdapter(getActivity(),R.layout.dong_ca_nhan,mang);
        recycleviewcanhan.setAdapter(adapter);

    }


}



