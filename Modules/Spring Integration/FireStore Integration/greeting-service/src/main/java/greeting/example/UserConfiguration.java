package greeting.example;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("userinfo")
public class UserConfiguration {

    private FirestoreReactiveRepository<User> firestoreReactiveRepository;

    public FirestoreReactiveRepository<User> getFirestoreReactiveRepository() {
        return firestoreReactiveRepository;
    }

    public void setFirestoreReactiveRepository(FirestoreReactiveRepository<User> firestoreReactiveRepository) {
        this.firestoreReactiveRepository = firestoreReactiveRepository;
    }
    //    private String template = "Hello, %s!";
//
//    public String getTemplate() {
//        return template;
//    }
//
//    public void setTemplate(String template) {
//        this.template = template;
//    }
}