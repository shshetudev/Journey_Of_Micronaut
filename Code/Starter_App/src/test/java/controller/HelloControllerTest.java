package controller;

import com.shetu.HelloControllerClient;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@MicronautTest
public class HelloControllerTest {
    private EmbeddedServer server;
    private HelloControllerClient client;

    @Before
    public void setup(){
        this.server = ApplicationContext.run(EmbeddedServer.class);
        this.client = server.getApplicationContext().getBean(HelloControllerClient.class);
    }

    @Test
    public void testHello(){
        String hello = "Hello World";
        System.out.println(hello);
    }
    @Test
    public void test_return_hello(){
        String response = client.hello("Shetu").blockingGet();
        Assert.assertEquals("Hello Shetu!",response);
    }

    @After
    public void cleanup(){
        this.server.stop();
    }
}
