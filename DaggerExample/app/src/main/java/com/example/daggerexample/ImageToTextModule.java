package com.example.daggerexample;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageToTextModule {
    @Provides
    MyImage provideImage()
    {
        return new MyImage();
    }

    @Provides
    MyTextView provideMyTextView()
    {
        return new MyTextView();
    }

    @Provides
    ImageToTextClass provideImageToTextClass(MyTextView myTextView,MyImage myImage)
    {
        return new ImageToTextClass(myImage,myTextView);
    }
}
