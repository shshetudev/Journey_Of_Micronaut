package greeting.example;

import java.util.Objects;

public class Pet {
  private String name;
  private String type;

  public Pet() {
  }

  public Pet(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pet)) return false;
    Pet pet = (Pet) o;
    return Objects.equals(name, pet.name) && Objects.equals(type, pet.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type);
  }

  @Override
  public String toString() {
    return "Pet{" +
      "name='" + name + '\'' +
      ", type='" + type + '\'' +
      '}';
  }
}
