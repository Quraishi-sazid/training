package com.example.daggerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject Car car,car2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CarComponent carComponent=DaggerCarComponent.builder().horsePower(150).capacity(200).engineName("Tesla").make();
        carComponent.inject(this);
        car.drive();
        car2.drive();
        car.imageToTextClass.convert();
    }
}