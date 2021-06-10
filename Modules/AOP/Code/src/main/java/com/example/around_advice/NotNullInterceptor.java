package com.example.around_advice;

import io.micronaut.aop.InterceptorBean;
import io.micronaut.aop.MethodInterceptor;
import io.micronaut.aop.MethodInvocationContext;
import io.micronaut.core.type.MutableArgumentValue;

import java.util.Map;
import java.util.Objects;

@InterceptorBean(NotNull.class)
public class NotNullInterceptor implements MethodInterceptor<Object, Object> {
    @Override
    public Object intercept(MethodInvocationContext<Object, Object> context) {
        final Map<String, Object> parameters = context.getParameterValueMap();
        parameters.forEach(
                (name, value) -> Objects.requireNonNull(value, "Method passed null value illegally for parameter: " + name)
        );

        // We can call context.proceed() multiple times, it will give method output as a result: "Hello shetuHello shetu"
        // Object result = "" + context.proceed() + context.proceed();

        Object result = context.proceed();
        Objects.requireNonNull(result, "Method illegally returned a null value: " + context.getDescription(true)
        );
        return result;
    }
}
