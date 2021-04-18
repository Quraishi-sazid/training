package com.example.hiltexample;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class MainActivityViewModel extends ViewModel {
    private static final String TAG = "MyApplication ";

    public MainActivityViewModel() {

        Log.d(TAG, "MainActivityViewModel: Hey I am from viewModel");
    }


}
