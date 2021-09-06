package com.shshetudev.greeting;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting {
  private final long id;
  private final String content;

  // We can use the @JsonCreator annotation to tune the constructor/factory used in deserialization.
  // todo: see the link to know more-> https://www.baeldung.com/jackson-annotations
  @JsonCreator
  public Greeting(@JsonProperty("id") long id, @JsonProperty("content") String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }
}
