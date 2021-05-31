package com.shetu;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;


@Controller("/")
public class HelloController {
//    @Inject
//    private HelloService helloService1;
    private final HelloService helloService;
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @Get(value = "/hello/{name}", produces = MediaType.TEXT_PLAIN)
    public String sayHi(String name) {
        return helloService.sayHi(name);
    }
}
