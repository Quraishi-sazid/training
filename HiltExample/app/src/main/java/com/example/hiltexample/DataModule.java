package com.example.hiltexample;


import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

    @Provides
    @Singleton
    MusicDatabase provideMusicDB(@ApplicationContext Context context)
    {
        MusicDatabase musicDatabase = Room.databaseBuilder(context, MusicDatabase.class, "music_db").build();
        return musicDatabase;
    }

}
