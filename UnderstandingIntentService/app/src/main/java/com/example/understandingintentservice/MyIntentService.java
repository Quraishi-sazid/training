package com.example.understandingintentservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyIntentService extends IntentService {
    private static final String TAG = "MyIntentService";
    private PowerManager.WakeLock wakelock;
    int task=0;
    public MyIntentService() {
        super("MyIntentService");
        Log.d(TAG, "MyIntentService: onCreate()");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if(task==0)
            {
                PowerManager powerManager=(PowerManager) getSystemService(POWER_SERVICE);
                wakelock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyIntentService:wakelock");
                wakelock.acquire();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Title")
                        .setContentText("Text")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                Notification notification = builder.build();
                startForeground(1,notification);
            }

        }
        Log.d(TAG, "task id: "+ ++task);
        for(int i=0; i<10; i++)
        {
            try {
                Log.d(TAG, "task id: "+ task + " sub task id:"+ i);
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wakelock.release();
    }
}
