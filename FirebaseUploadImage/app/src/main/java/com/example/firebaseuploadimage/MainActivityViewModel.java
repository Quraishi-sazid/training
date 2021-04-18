package com.example.firebaseuploadimage;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {
    @Inject public ImageRepository imageRepository;
    @Inject public MutableLiveData<Boolean> imageUploadSucceded;
    @Inject
    public MainActivityViewModel() {
    }

    public void uploadImage(Uri imageUri, String fileExtension) {
        imageRepository.uploadImage(imageUri,fileExtension);
    }
}
