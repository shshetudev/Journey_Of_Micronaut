package com.shshetudev.greeting;

import io.micronaut.scheduling.annotation.Scheduled;

import java.util.Optional;

public class GreetingJob {
  private final GreetingService greetingService;

  public GreetingJob(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  @Scheduled(fixedDelay = "30s")
  void printLastGreeting(){
    final Optional<Greeting> lastGreeting = greetingService.getLastGreeting();
    lastGreeting.ifPresent(greeting -> System.out.println("Last Greeting was = " + greeting.getContent()));
  }
}
