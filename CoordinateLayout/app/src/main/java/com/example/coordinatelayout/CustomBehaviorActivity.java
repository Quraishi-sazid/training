package com.example.coordinatelayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by romain on 20/10/15.
 */
public class CustomBehaviorActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {

    private View mHeader;

    public static void start(Context context) {
        Intent intent = new Intent(context, CustomBehaviorActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_behavior);

        mHeader = findViewById(R.id.activity_custom_behavior_header);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.addOnOffsetChangedListener(this);
        ImageView teamImage = (ImageView) findViewById(R.id.custom_behavior_image_teamA);
        teamImage.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) (maxScroll - mHeader.getHeight());
        mHeader.setAlpha(percentage);
    }
}
