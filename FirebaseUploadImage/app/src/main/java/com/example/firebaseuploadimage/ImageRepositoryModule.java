package com.example.firebaseuploadimage;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ImageRepositoryModule {
    ImageRepository imageRepository;

    @Provides
    ImageRepository provideImageRepository(){
        imageRepository=new ImageRepository(FirebaseStorage.getInstance().getReference(),
                FirebaseDatabase.getInstance().getReference(),
                new MutableLiveData<Boolean>(),
                new MutableLiveData<Long>());
        return imageRepository;
    }
    @Provides
    MutableLiveData<Boolean> provideImageUploadedLiveData() {
        return imageRepository.getImageUploadSucceded();
    }
    @Provides
    MutableLiveData<Long> providePercentLiveData() {
        return imageRepository.getPercentLiveData();
    }
}
