package com.example.daggerexample;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;

@Singleton
public class PatrolEngine implements Engine {
    private static final String TAG = "Car PatrolEngine";
    private final Driver driver;
    int horsePower;
    int capacity;
    @Inject @Named("EngineName") String EngineName;
    //with constructor value we can't inject
    @Inject
    public PatrolEngine(@Named("horsePower") int horsePower,@Named("Capacity") int capacity,Driver driver) {
        this.horsePower=horsePower;
        this.capacity=capacity;
        this.driver=driver;
    }

    @Override
    public void start() {
        Log.d(TAG, "driving: driver is "+driver);
        Log.d(TAG, "driving: Patrol Engine Name "+EngineName);
        Log.d(TAG, "driving: Patrol Engine"+horsePower+" "+capacity);
    }
}
