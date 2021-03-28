package com.example.coordinatelayout.behaviors;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.coordinatelayout.R;
import com.google.android.material.appbar.AppBarLayout;

public class MatchScoreBehavior extends CoordinatorLayout.Behavior<TextView> {

    private static final float TOOLBAR_SCALE = 0.7f;

    private final Context mContext;
    private int mToolbarHeight;
    private int mMaxScrollAppBar;
    private float mTextSizeMax;

    /**
     * Constructor
     *
     * @param context Context
     * @param attrs   AttributeSet
     */
    public MatchScoreBehavior(Context context, AttributeSet attrs) {
        mContext = context;
        init(context);
    }

    private void init(Context context) {
        Resources resources = context.getResources();
        mTextSizeMax = resources.getDimension(R.dimen.custom_behavior_text_size);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        AppBarLayout appBarLayout = (AppBarLayout) dependency;
        shouldInitProperties(child, appBarLayout);
        int currentScroll = dependency.getBottom();
        float percentage = (float) currentScroll / (float) mMaxScrollAppBar;
        /*if (percentage < TOOLBAR_SCALE) {
            percentage = TOOLBAR_SCALE;
        }*/
        child.setScaleY(percentage);
        child.setScaleX(percentage);
        return true;
    }

    private void shouldInitProperties(TextView child, AppBarLayout appBarLayout) {
        if (mMaxScrollAppBar == 0) {
            mMaxScrollAppBar = appBarLayout.getBottom();
        }
    }
}