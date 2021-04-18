package com.example.daggerexample;

import android.util.Log;


import javax.inject.Inject;


public class Car {
    @Inject Engine engine;
    Wheel wheel;
    @Inject ImageToTextClass imageToTextClass;
    private static final String TAG = "Car";
    @Inject
    public Car(Wheel wheel) {
        this.wheel=wheel;
    }
    @Inject
    public void setRemote(Remote remote)
    {
        remote.setListener(this);
    }

    @Inject
    void setLock(CarLock lock)
    {
           lock.attachLock();
    }

    public void drive(){
        engine.start();
        Log.d(TAG, "driving...");

    }
}
