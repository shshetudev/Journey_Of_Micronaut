package com.shetu;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

/*
* To create an executable jar: `./gradlew assemble`
* To run the jar: `java -jar build/libs/starter_app-0.1-all.jar`
* */
@Controller("/hello")
public class HelloController {
//    @Inject
//    private HelloService helloService1;
    private final HelloService helloService;
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Get("/{name}")
    public Single<String> hello(String name){
        return Single.just("Hello " + name + "!");
    }
//    @Get(value = "/hello/{name}", produces = MediaType.TEXT_PLAIN)
//    public String sayHi(String name) {
//        return helloService.sayHi(name);
//    }
}
