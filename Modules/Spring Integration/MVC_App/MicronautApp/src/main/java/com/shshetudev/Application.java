package com.shshetudev;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
  info = @Info(
    title = "Greeting Service",
    version = "0.0",
    description = "Implements a Greeting API API",
    license = @License(name = "Apache 2.0", url = "http://foo.bar")
  )
)
public class Application {
  public static void main(String... args) {
    Micronaut.run(Application.class);
//        org.springframework.boot.SpringApplication.run(Application.class);
  }
}
