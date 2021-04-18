package com.example.firebaseuploadimage;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface ProgressDataFactory{
    ProgressData create(int percent);
}
