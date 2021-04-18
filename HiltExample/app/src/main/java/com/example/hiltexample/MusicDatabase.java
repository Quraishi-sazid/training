package com.example.hiltexample;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;
@Database(entities = {Music.class}, version = 1)
public abstract class MusicDatabase extends RoomDatabase {
}
