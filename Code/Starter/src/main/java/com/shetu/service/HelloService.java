package com.shetu.service;


import javax.inject.Singleton;

@Singleton
public class HelloService {
    public String sayHi(String name) {
        return "Hello " + name;
    }
}
