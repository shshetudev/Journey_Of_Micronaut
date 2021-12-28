package greeting.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@MicronautTest
public class UserRepositoryTest {

  @Inject
  private UserRepository userRepository;

  @Test
  void getAll() {
    Flux<User> all = userRepository.findAll();
    System.out.println(all);
  }
}
