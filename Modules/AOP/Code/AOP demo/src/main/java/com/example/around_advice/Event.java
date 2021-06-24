package com.example.around_advice;

import io.micronaut.core.annotation.Introspected;

import javax.inject.Singleton;

@Introspected
public abstract class Event {
    private String mobileNumber;
    private String fathersName;
}
