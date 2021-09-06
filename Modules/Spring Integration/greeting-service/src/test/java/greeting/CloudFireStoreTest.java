package greeting;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@MicronautTest
class CloudFireStoreTest {
//  @Inject
//  private CloudFirestore firestore;
  private Firestore db = getFirestore();

  @Test
  void testWriteAndReadDocument() throws ExecutionException, InterruptedException, IOException {
    Map<String, Object> docData = new HashMap<>();
    docData.put("name", "Chandpur");
    docData.put("state", "Bangladesh");
    docData.put("country", "Bangladesh");
    docData.put("regions", Arrays.asList("Hazigonj", "Motlob"));
    save(docData);
  }

  private Firestore getFirestore() {
    Firestore firestore = null;
    try {
      firestore = FirestoreOptions.getDefaultInstance().toBuilder()
        .setCredentials(GoogleCredentials.getApplicationDefault())
        .build().getService();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return firestore;
  }

  private void save(Map<String, Object> docData) throws ExecutionException, InterruptedException {
    // Add a new document asynchronously in collection cities with id: "Dhaka"
    ApiFuture<WriteResult> future = db.collection("cities").document("Dhaka").set(docData);
    // future.get() blocks on response
    System.out.println("Update time : " + future.get().getUpdateTime());
  }


  private GoogleCredentials getCredentials() throws IOException {
    String jsonPath = "/home/shetu/RK-JSON-file/ud-account-file.json";
    return GoogleCredentials.fromStream(new FileInputStream(jsonPath))
      .createScoped(Collections.singletonList("https://www.googleapis.com/auth/cloud-platform"));
  }
}
