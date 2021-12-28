package com.example;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Demo {
  private String name;
  private int age;
  private Foo foo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public Foo getFoo() {
    return foo;
  }

  public void setFoo(Foo foo) {
    this.foo = foo;
  }
}
