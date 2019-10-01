package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Hoaminzf.fithou.mykengkingapp.R;

public class MyMusicActivity  extends AppCompatActivity {
    ListView lvmymusic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_music);
        lvmymusic = findViewById(R.id.lvmymusic);
        Intent intent = getIntent();
        ArrayAdapter arrayAdapter = new ArrayAdapter(MyMusicActivity.this, android.R.layout.simple_list_item_1);
        lvmymusic.setAdapter(arrayAdapter);
    }
}
