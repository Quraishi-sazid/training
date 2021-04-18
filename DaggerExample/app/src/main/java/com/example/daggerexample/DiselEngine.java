package com.example.daggerexample;

import android.util.Log;

import javax.inject.Inject;

public class DiselEngine implements Engine {
    private static final String TAG = "Car DiselEngine";
    int horsepower;
    @Inject
    public DiselEngine(int horsepower)
    {
        this.horsepower=horsepower;
    }

    @Override
    public void start() {
        Log.d(TAG, "driving: DiselEngine");
    }
}
