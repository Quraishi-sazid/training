package com.example.daggerexample;

import javax.inject.Inject;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class PatrolEngineModule {
   public PatrolEngineModule()
   {

   }


    @Provides
    Engine provideEngineEngine(PatrolEngine patrolEngine)
    {
       return patrolEngine;
    }

}
