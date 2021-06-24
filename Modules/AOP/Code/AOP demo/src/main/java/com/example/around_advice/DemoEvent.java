package com.example.around_advice;

import io.micronaut.core.annotation.Introspected;

import javax.inject.Singleton;

@Consumed
@Singleton
@Introspected
public class DemoEvent extends Event{
    private String demoName;
    private Integer demoAge;
    private TooDeep tooDeep;
}
