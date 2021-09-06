package com.shshetudev.greeting;

import io.micronaut.context.annotation.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties("greeting")
public class GreetingConfiguration {
  private String template = "Hello %s!";

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }
}
