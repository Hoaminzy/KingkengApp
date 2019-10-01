package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class Music extends Service {
    MediaPlayer mediaPlayer;
    int id;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Toi trong music","Hello");

        String nhankey = intent.getExtras().getString("extra");
        Log.e("Music nhan key",nhankey);

        if(nhankey.equals("on")){
            id = 1;
        }else if(nhankey.equals("off")){
            id = 0;
        }

        if(id == 1){

            mediaPlayer.start();
//            System.exit(0);
            id=0;
        }else if (id==0){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        return START_STICKY;
    }
}
