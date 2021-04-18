package com.example.daggerexample;

import javax.inject.Inject;

public class Test {
    private static final String TAG = "Car Test";
    @Inject
    public Test()
    {

    }
    void test()
    {
        TestComponent testComponent=DaggerTestComponent.create();
        testComponent.Inject(this);
    }
}
