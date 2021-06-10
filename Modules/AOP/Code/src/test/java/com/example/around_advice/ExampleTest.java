package com.example.around_advice;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@MicronautTest
public class ExampleTest {
    @Inject
    Example example;

    @Test
    void testResult() {
        String result = example.result();
        Assertions.assertEquals("Hello World", result);
    }

    @Test
    void testResultIsNull() {
        String result = example.resultIsNull();
        Assertions.assertNull(result);
    }

    @Test
    void testResultParameterIsNull() {
        String result = example.result(null);
        Assertions.assertNull(result);
    }

    @Test
    void testResultParameterIsNotNull() {
        String name = "shetu";
        String result = example.result(name);
        Assertions.assertEquals("Hello " + name, result);
    }
}
