package com.example.simple_demo;

import io.micronaut.context.annotation.Factory;

import javax.inject.Singleton;

@Factory
class EngineFactory {
  @Singleton
  Engine v8Engine(CrankShaft crankShaft){
    return new V8Engine(crankShaft);
  }

}
