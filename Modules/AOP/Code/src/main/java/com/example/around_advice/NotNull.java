package com.example.around_advice;

import io.micronaut.aop.Around;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Around
@Retention(RetentionPolicy.RUNTIME)
@interface NotNull{

}
