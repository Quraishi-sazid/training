package com.example.hiltexample;

import android.app.Application;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }
}
