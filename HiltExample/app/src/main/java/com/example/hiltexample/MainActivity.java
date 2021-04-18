package com.example.hiltexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject MusicDatabase musicDatabase1,musicDatabase2;
    @Inject MainActivityViewModel mainActivityViewModel;
    @Inject MusicPlayer player;
    private static final String TAG = "MyApplication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player.play("Hilt Music");
        Log.d(TAG, "onCreate: "+musicDatabase1);
        Log.d(TAG, "onCreate: "+musicDatabase2);
    }
}