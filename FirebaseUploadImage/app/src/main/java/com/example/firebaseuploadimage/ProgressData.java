package com.example.firebaseuploadimage;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import javax.inject.Inject;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;


public class ProgressData extends BaseObservable {
    private int percent;
    @AssistedInject
    public ProgressData(@Assisted int percent) {
        this.percent = percent;
    }

    @Bindable
    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
        notifyPropertyChanged(BR.percent);
    }
}


