package com.example;

import com.example.annotation.Consumed;
import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Consumed
@Introspected
public class Person {
  private String name;
  private int age = 20;
  private Demo demo;
  private List<String> list;
  private String[] array;
  public Person() {
  }

  public Person(String name) {
    this.name = name;
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    this.list = list;
  }

  public String[] getArray() {
    return array;
  }

  public void setArray(String[] array) {
    this.array = array;
  }

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

  public Demo getDemo() {
    return demo;
  }

  public void setDemo(Demo demo) {
    this.demo = demo;
  }
}
