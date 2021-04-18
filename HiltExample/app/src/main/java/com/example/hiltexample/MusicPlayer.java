package com.example.hiltexample;

import android.util.Log;

import javax.inject.Inject;

public class MusicPlayer {

    private static final String TAG = "MusicPlayer";
    @Inject
    public MusicPlayer() {

    }

    public void play(String musicName)
    {
        Log.d(TAG, "play: "+musicName);
    }
}
