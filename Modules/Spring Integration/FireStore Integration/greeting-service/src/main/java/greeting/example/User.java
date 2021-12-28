package greeting.example;

import com.google.cloud.firestore.annotation.DocumentId;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.gcp.data.firestore.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collectionName = "users")
public class User {

  @DocumentId
  private String name;
  private int age;
  private List<Pet> pets;

  public User() {
    pets = new ArrayList<>();
  }

  public User(String name, int age, List<Pet> pets) {
    this.name = name;
    this.age = age;
    this.pets = pets;
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

  public List<Pet> getPets() {
    return pets;
  }

  public void setPets(List<Pet> pets) {
    this.pets = pets;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return age == user.age && Objects.equals(name, user.name) && Objects.equals(pets, user.pets);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, pets);
  }

  @Override
  public String toString() {
    return "User{" +
      "name='" + name + '\'' +
      ", age=" + age +
      ", pets=" + pets +
      '}';
  }
}
