package com.Hoaminzf.fithou.mykengkingapp.Activity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.RemoteViews;

import com.Hoaminzf.fithou.mykengkingapp.R;

public class AppWidgetsProvinder extends AppWidgetProvider {

    private MediaPlayer mediaPlayer;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        init();
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i = 0; i < appWidgetIds.length; i++) {
            int currentWidgetId = appWidgetIds[i];
            Intent intent = new Intent(Intent.ACTION_VIEW);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_music);

            views.setOnClickPendingIntent(R.id.btnNext, pending);

            views.setOnClickPendingIntent(R.id.btnPause, pending);
            views.setOnClickPendingIntent(R.id.btnPlay, pending);
        //    views.setOnClickPendingIntent(R.id.btnDelete, pending);

            appWidgetManager.updateAppWidget(currentWidgetId, views);

        }
    }

    private void init() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {


        super.onReceive(context, intent);
    }
}



