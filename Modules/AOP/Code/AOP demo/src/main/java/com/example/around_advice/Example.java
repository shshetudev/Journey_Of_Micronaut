package com.example.around_advice;

import javax.inject.Singleton;

@Singleton
public class Example {
    // todo: Implement Around advice
    @NotNull
    String result() {
        // before
        return "Hello World";
        // after
    }

    @NotNull
    String result(String name){
        return "Hello " + name;
    }

    @NotNull
    String resultIsNull() {
        // before
        return null;
        // after
    }
}
