package com.example.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker2 extends Worker {
    private static final String TAG = "MyWorker2";
    public MyWorker2(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public ListenableWorker.Result doWork() {
        Log.d(TAG, "doWork: begin");
        Data inputData = getInputData();
      //  Log.d(TAG, "name: "+inputData.getString("name"));
        Log.d(TAG, "tag: "+getTags().toString());
        String[] names = inputData.getStringArray("name");
        for(int i=0; i<10;i++)
        {
            Log.d(TAG, getTags().toString()+"   subWork: "+i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Data output = new Data.Builder().putString("name","ridu").build();

        return ListenableWorker.Result.success(output);
    }
}
