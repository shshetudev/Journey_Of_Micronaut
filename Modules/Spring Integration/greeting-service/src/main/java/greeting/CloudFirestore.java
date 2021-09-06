package greeting;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Singleton;
import java.util.concurrent.ExecutionException;

@Singleton
public class CloudFirestore {
  private static final Logger log = LoggerFactory.getLogger(CloudFirestore.class);
  @Autowired
  Firestore firestore;

  public void writeAndReadDocument() throws ExecutionException, InterruptedException {
    // Add document with id "tom" using a custom User class to users collection
    User tom = new User("Tom", 18);
    // set asynchronously create/update document reference users/tom
    // users is the collection and tom is the document id
    ApiFuture<WriteResult> apiFuture = this.firestore.document("users/tom").set(tom);
    // .get() blocks on response
    WriteResult writeResult = apiFuture.get();
    log.info("Update time: {}", writeResult.getUpdateTime());
  }
}
