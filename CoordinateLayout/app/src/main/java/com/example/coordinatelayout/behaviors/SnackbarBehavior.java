package com.example.coordinatelayout.behaviors;

import android.content.Context;
import android.opengl.Visibility;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class SnackbarBehavior extends FloatingActionButton.Behavior {

    public SnackbarBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        float translationY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
        //child.setTranslationY(translationY);
        if(child.getVisibility()== View.GONE)
            child.setVisibility(View.VISIBLE);
        else
            child.setVisibility(View.GONE);
        return true;
    }
}

