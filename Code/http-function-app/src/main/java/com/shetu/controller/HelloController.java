package com.shetu.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {
    @Get(uri="/",produces = "text/plain")
    public String index(){
        return "Micronaut on Cloud Functions";
    }
}
