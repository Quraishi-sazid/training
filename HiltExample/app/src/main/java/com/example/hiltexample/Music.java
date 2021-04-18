package com.example.hiltexample;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Music {
    @PrimaryKey
    private int id;
    private String musicName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }
}
