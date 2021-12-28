package greeting.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

//  private final UserConfiguration userConfiguration;
  private final UserRepository userRepository;

//  public UserController(UserConfiguration userConfiguration) {
//    this.userConfiguration = userConfiguration;
//    this.userRepository = userRepository;
//    userConfiguration.setFirestoreReactiveRepository(userRepository);
//  }
//  private final UserRepository userRepository;
//  private final FirestoreTemplate firestoreTemplate;
//

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

//  public UserController(UserRepository userRepository, FirestoreTemplate firestoreTemplate) {
//    this.userRepository = userRepository;
//    this.firestoreTemplate = firestoreTemplate;
//  }

//
//  @GetMapping
//  public Flux getAllUsers() {
//    Optional<Greeting> lastGreeting = greetingService.getLastGreeting();
//    return  Flux.empty();
//  }


//  public UserController(UserService userService, FirestoreTemplate firestoreTemplate) {
//    this.userService = userService;
//    this.firestoreTemplate = firestoreTemplate;
//  }

//  public UserController(UserService userService) {
//    this.userService = userService;
//  }

//  @GetMapping
//  public Flux<User> getAllUsers() {
//    return userService.getAll();
//  }

//  @GetMapping
//  public Flux<User> getAllUsers() {
//    return userConfiguration.getFirestoreReactiveRepository().findAll();
//  }

  @GetMapping
  public Flux<User> getAllUsers() {
    return userRepository.findAll();
  }
//
//  @GetMapping("/age")
//  public Flux<User> getUsersByAge(@RequestParam int age) {
//    return userRepository.findByAge(age);
//  }

//  @GetMapping("/phones")
//  public Flux<PhoneNumber> getPhonesByName(@RequestParam String name) {
//    return this.firestoreTemplate.withParent(new User(name, 0, null)).findAll(PhoneNumber.class);
//  }
//
//  @GetMapping("/removeUser")
//  public Mono<String> removeUserByName(@RequestParam String name) {
//    return this.userRepository.delete(new User(name, 0, null))
//      .map(aVoid -> name + "was successfully removed");
//  }
//
//  @PostMapping("/saveUser")
//  public Mono<User> saveUser(ServerWebExchange serverWebExchange) {
//    return serverWebExchange.getFormData()
//      .flatMap(formData -> {
//        User user = new User(
//          formData.getFirst("name"),
//          Integer.parseInt(Objects.requireNonNull(formData.getFirst("age"))),
//          createPets(formData.getFirst("phones")));
//        FirestoreReactiveOperations userTemplate = this.firestoreTemplate.withParent(user);
//        List<PhoneNumber> phones = getPhones(formData.getFirst("phones"));
//        return userTemplate.saveAll(Flux.fromIterable(phones)).then(userRepository.save(user));
//      });
//  }

  private List<Pet> createPets(String pets) {
    return pets == null || pets.isEmpty()
      ? Collections.emptyList()
      : Arrays.stream(pets.split(","))
      .map(
        s -> {
          String[] parts = s.split(",");
          return new Pet(parts[0], parts[1]);
        }).collect(Collectors.toList());
  }

  private List<PhoneNumber> getPhones(String phones) {
    return phones == null || phones.isEmpty()
      ? Collections.emptyList()
      : Arrays.stream(phones.split(","))
      .map(PhoneNumber::new).collect(Collectors.toList());
  }
}
