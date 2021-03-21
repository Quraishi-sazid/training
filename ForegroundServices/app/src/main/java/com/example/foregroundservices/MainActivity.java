package com.example.foregroundservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
/*
* This app is an example of service.
* Why we need service:-
* we need service to run a piece of code in background. without acitivity of fragment. NO need of UI.
* It doesn't mean that it'll run in a background thread. By default a service runs in UI thread.
* If we want to do a long running task in service then we have to create a separate thread in onStartCommand.
* Is JobScheduler and service replaceable?
* No.Purpose of jobScheduler and purpose of a service is not same. Alarm maneger and jobScheduler is used for to
* do something at a particular time and for other events like charging connected,wifi connected etc respectively.
* But Service is created for running on the background only. A service may be started from a jobscheduler or a service may start
* a job.
* There are many kinds of services. here started service is discussed.
* Before android oreo service could run indefinitely in background. we used startService to start a service.
* But from oreo we have to use foreground service to run in background.
* onCreate() will be called only once.when the service is created.
* onStartCommand() will be called whenever startService is called.
* onStartCommand() can return three value.
*  1->START_NOT_STICKY->if the service is not needed to be restarted
*  2->START_STICKY->needed to be restarted.with empty intent.
*  3->START_REDELIVER_INTENT->needed to be restarted.with same intent.
* All three are only relevant when the phone runs out of memory and kills the service before it finishes executing.(https://stackoverflow.com/questions/9093271/start-sticky-and-start-not-sticky)
* links:
* https://www.youtube.com/watch?v=FbpD5RZtbCc&t=515s
 * */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    NotificationChannel notificationChannel;
    public static String CHANNEL_ID="123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel= new NotificationChannel(CHANNEL_ID,"My channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
        }
        ((Button)findViewById(R.id.start_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
        ((Button)findViewById(R.id.stop_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
    }

    public void start()
    {
        Intent intent=new Intent(this,MyForgroundService.class);
        ContextCompat.startForegroundService(this,intent);
    }
    public void stop()
    {
        Intent intent=new Intent(this,MyForgroundService.class);
        stopService(intent);
        Log.d(TAG, "stop: ");
    }
}