package greeting.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;

@MicronautTest
public class UserControllerTest {

  private UserController userController;

  public UserControllerTest(UserController userController) {
    this.userController = userController;
  }

//  @Test
//  void getAll(){
//    Flux<User> allUsers = userController.getAllUsers();
//    System.out.println(allUsers);
//  }
}
