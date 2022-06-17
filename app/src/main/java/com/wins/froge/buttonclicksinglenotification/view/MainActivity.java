package com.wins.froge.buttonclicksinglenotification.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.wins.froge.buttonclicksinglenotification.R;
import com.wins.froge.buttonclicksinglenotification.databinding.ActivityMainBinding;
import com.wins.froge.buttonclicksinglenotification.util.NotificationRecevire;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;
    NotificationManager nm;
    private NotificationManagerCompat notificationManager;

    public static final String NOTIFICATION_ID = "1";
    public final String NOTIFICATION_NAME = "Example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = getApplicationContext();
        notificationManager = NotificationManagerCompat.from(this);

        iniUi();
        initClickListener();
    }

    private void iniUi() {
        Intent resultIntent = new Intent(getApplicationContext(), SecondActivity.class);
        resultIntent.putExtra("object_id", "this is notification");
        PendingIntent intent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

        NotificationCompat.Builder n = new NotificationCompat.Builder(this, NOTIFICATION_ID);
        n.setContentTitle("IOS");
        n.setContentTitle("IOS Update!!");
        n.setContentIntent(intent);
        n.setSmallIcon(R.drawable.ic_baseline_apple_24);

        Intent notificationIntent = new Intent(context, NotificationRecevire.class);
        notificationIntent.putExtra(NotificationRecevire.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationRecevire.NOTIFICATION_NAME,n.build());
        @SuppressLint("WrongConstant") PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 52);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);

    }

    private void initClickListener() {
        binding.buttonLocalNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSingleNotification();
            }
        });
    }

    //* Notification Show *//
    public void showSingleNotification() {
        Intent resultIntent = new Intent(getApplicationContext(), SecondActivity.class);
        resultIntent.putExtra("object_id", "this is notification");

        PendingIntent intent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);

        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChanel();
        }
        NotificationCompat.Builder n = new NotificationCompat.Builder(this, NOTIFICATION_ID);
        n.setContentTitle("IOS");
        n.setContentTitle("IOS Update!!");
        n.setContentIntent(intent);
        n.setSmallIcon(R.drawable.ic_baseline_apple_24);

        nm.notify(1, n.build());

    }

    //* Create Notification *//
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanel() {

        NotificationChannel nc = new NotificationChannel(NOTIFICATION_ID, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        nc.enableVibration(true);
        nm.createNotificationChannel(nc);

    }
}