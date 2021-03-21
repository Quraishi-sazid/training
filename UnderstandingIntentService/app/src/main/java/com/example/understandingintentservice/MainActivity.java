package com.example.understandingintentservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
* I was getting "unable to instantiate service : java.lang.nullpointerexception: class name is null".
while I was setting up foreground service in onCreate(). I moved it to onHandleIntent() then it worked fine.
Android version: 10.
* Intent service:
* 1. runs in the background thread.(normal service runs in main thread)
* 2. just like normal service,from android oreo we have to use forground service to use intent service.
* 3.onHandleintent() would be called everytime whenever we use startService()
* 4.task will be executed sequentially in the background thread.
* 5.It will be finished whenever it has ended task.
* 6.If we don't wakelock then after screen is of the intent service pause it's execution.
* 7.wake lock is used to avoid sleeping of device.
*  Many types of wake lock can be. In this example partial wake lock is used which is used for keep the cpu running.
* we can screen on,screen dim and do other things with wake lock.
* we need Power_Maneger from getSystemService() to get power maneger. powerManager.newWakeLock is used for getting wake lock
* */
public class MainActivity extends AppCompatActivity {

    public static String CHANNEL_ID="123";
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;
        NotificationChannel notificationChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel= new NotificationChannel(CHANNEL_ID,"My channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
        }
        ((Button)findViewById(R.id.btn_start_service)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContextCompat.startForegroundService(MainActivity.this,new Intent(MainActivity.this,MyIntentService.class));
            }
        });
    }
}