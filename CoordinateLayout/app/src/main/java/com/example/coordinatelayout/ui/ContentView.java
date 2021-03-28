package com.example.coordinatelayout.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.example.coordinatelayout.R;

public class ContentView extends FrameLayout {

    public ContentView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_content, this);
    }
}
