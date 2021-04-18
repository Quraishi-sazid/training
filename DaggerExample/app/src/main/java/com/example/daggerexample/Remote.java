package com.example.daggerexample;

import android.util.Log;

import javax.inject.Inject;

public class Remote
{
    private static final String TAG = "Car Remote";
    @Inject
    public Remote()
    {

    }
    public void setListener(Car car)
    {
        Log.d(TAG, "setListener: ");
    }
}
