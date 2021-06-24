package com.example.around_advice;

import io.micronaut.core.annotation.Introspected;

import javax.inject.Singleton;

@Consumed
@Singleton
@Introspected
public class SimpleEvent extends Event{
    private String simpleName;
    private Integer simpleAge;
    private TooDeep tooDeep;
}
