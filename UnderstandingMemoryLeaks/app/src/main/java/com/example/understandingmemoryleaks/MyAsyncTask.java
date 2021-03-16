package com.example.understandingmemoryleaks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class MyAsyncTask extends AsyncTask<Void,Void,Void>
{
    WeakReference<Context> context;
    //Context context;

    public MyAsyncTask(Context context) {
        this.context = new WeakReference<>(context);
        //this.context=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        Bitmap bitmap= BitmapFactory.decodeResource(context.get().getResources(),R.drawable.ic_launcher_background);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}