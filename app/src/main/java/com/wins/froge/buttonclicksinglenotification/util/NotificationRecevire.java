package com.wins.froge.buttonclicksinglenotification.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class NotificationRecevire extends BroadcastReceiver {


    public static  final String NOTIFICATION_ID = "1";
    public static final String NOTIFICATION_NAME = "Example";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION_NAME);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_ID, NOTIFICATION_NAME, importance);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);

        }
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        assert notificationManager != null;
        notificationManager.notify(id, notification);


    }
}
