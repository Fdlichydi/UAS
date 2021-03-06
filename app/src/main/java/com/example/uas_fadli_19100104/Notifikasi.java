package com.example.uas_fadli_19100104;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class Notifikasi extends BroadcastReceiver {
    public static String NOTIFICATION_ID="notification-id";
    public static String NOTIFICATION="notification";

    public void onReceiver(Context context, Intent intent){
        NotificationManager notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification=intent.getParcelableExtra(NOTIFICATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        } else {
            final double v = .0;
        }
        int importance=NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel notificationChannel=new NotificationChannel(MainActivity.NOTIFICATION_CHANNEL_ID,"Pengingat",importance);
        assert notificationManager!=null;
        notificationManager.createNotificationChannel(notificationChannel);
        int id=intent.getIntExtra(NOTIFICATION_ID,0);
        if(notificationManager!=null) notificationManager.notify(id, notification);
    }

    @Override
    public void onReceive(Context context, Intent intent) {

    }
}