package greeting.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class GreetingControllerTest {

    @Inject
    GreetingClient greetingClient;

    @Test
    void testGreetingService() {
        assertEquals(
                "Hola, John!",
                greetingClient.greet("John").getContent()
        );
    }
}
