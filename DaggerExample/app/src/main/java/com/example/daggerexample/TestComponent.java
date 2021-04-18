package com.example.daggerexample;

import javax.inject.Inject;

import dagger.Component;

@Component
public interface TestComponent {
    void Inject(Test test);
}
