package com.example.coordinatelayout.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.example.coordinatelayout.ui.ContentView;

public class PagerTitleAdapter extends PagerAdapter {

    private static final String PAGE = "Page ";

    private Context mContext;

    public PagerTitleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ContentView contentView = new ContentView(mContext);
        container.addView(contentView);
        return contentView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE + (position + 1);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}