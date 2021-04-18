package com.example.daggerexample;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;

@Singleton
@Component(modules = {ImageToTextModule.class,PatrolEngineModule.class})
public interface CarComponent {

    public void inject(MainActivity mainActivity);

    @Component.Builder
    interface  Builder
    {
        @BindsInstance
        Builder horsePower(@Named("horsePower")int horsePower);
        @BindsInstance
        Builder capacity(@Named("Capacity") int capacity);
        @BindsInstance
        Builder engineName(@Named("EngineName") String engineName);
        CarComponent make();
    }
}
