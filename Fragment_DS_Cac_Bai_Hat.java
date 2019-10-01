package com.Hoaminzf.fithou.mykengkingapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.Hoaminzf.fithou.mykengkingapp.Activity.PlayNhacActivity;
import com.Hoaminzf.fithou.mykengkingapp.Adapter.PlaynhacAdapter;
import com.Hoaminzf.fithou.mykengkingapp.R;

public class Fragment_DS_Cac_Bai_Hat extends Fragment {
    View view;
     ListView lvdsbaihat;
    PlaynhacAdapter playnhacAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_ds_cac_bai_hat, container,false);
        lvdsbaihat = view.findViewById(R.id.listviewdscacbaihat);
        if(PlayNhacActivity.mangbaihat.size()>0){
            playnhacAdapter = new PlaynhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);

          // lvdsbaihat.setLayoutManager(new LinearLayoutManager(getActivity()));
           // lvdsbaihat.setAdapter((ListAdapter) playnhacAdapter);
            lvdsbaihat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), PlayNhacActivity.class);
                    //intent.putExtra("itemplaylist", mangplaylist.get(position));
                    startActivity(intent);
                }
            });
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
