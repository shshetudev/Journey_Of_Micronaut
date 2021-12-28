package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/hello")
public class HelloController {

  @Get(uri="/", processes = "text/plain")
  public String index(){
    return "Example Response";
  }

  @Post(uri="/", processes=)
}
