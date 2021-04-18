package com.example.daggerexample;

import android.util.Log;

public class ImageToTextClass
{
    MyImage myImage;
    MyTextView myTextView;
    private static final String TAG = "Car ImageToTextClass";
    public  ImageToTextClass(MyImage myImage,MyTextView myTextView)
    {
        this.myImage=myImage;
        this.myTextView=myTextView;
        Log.d(TAG, "ImageToTextClass: I can convert Image to text");
    }

    public void convert()
    {
        Log.d(TAG, "convert: I am converting");
    }
}
