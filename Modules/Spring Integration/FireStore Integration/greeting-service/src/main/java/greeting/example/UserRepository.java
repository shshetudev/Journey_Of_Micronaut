package greeting.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

//@Configuration
/*
 * @Component

 * */
//@Component
//@Repository
@ConfigurationProperties("users")
public interface UserRepository extends JpaRepository<User,String> {
  Flux<User> findByAge(int age);
}
