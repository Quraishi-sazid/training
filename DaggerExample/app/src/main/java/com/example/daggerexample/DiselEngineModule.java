package com.example.daggerexample;

import dagger.Module;
import dagger.Provides;

@Module
public class DiselEngineModule {
    @Provides
    Engine provideDiselEngine()
    {
        return new DiselEngine(8);
    }
}
