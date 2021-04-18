package com.example.firebaseuploadimage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ProgressDataModule {

    @Provides
    int provideInitialValue()
    {
        return 0;
    }


}
