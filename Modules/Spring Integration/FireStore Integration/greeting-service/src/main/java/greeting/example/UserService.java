package greeting.example;

import io.micronaut.validation.Validated;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Validated
public class UserService {

//  private final UserRepository userRepository;
//
//  public UserService(UserRepository userRepository) {
//    this.userRepository = userRepository;
//  }

  public Flux<User> getAll(){
    return Flux.empty();
  }
}
