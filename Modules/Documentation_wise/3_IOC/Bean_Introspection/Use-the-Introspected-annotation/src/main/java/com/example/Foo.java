package com.example;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Foo {
  private Integer fooField1;
  private Double fooField2;

  public Integer getFooField1() {
    return fooField1;
  }

  public void setFooField1(Integer fooField1) {
    this.fooField1 = fooField1;
  }

  public Double getFooField2() {
    return fooField2;
  }

  public void setFooField2(Double fooField2) {
    this.fooField2 = fooField2;
  }
}
