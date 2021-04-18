package com.example.daggerexample;

import android.util.Log;

import javax.inject.Inject;

public class CarLock {
    private static final String TAG = "Car CarLock";
    @Inject
    public CarLock() {
    }
    public void attachLock()
    {
        Log.d(TAG, "attachLock: log attached");
    }
}
