# Eager Initialization of Singletons

- **Eager initialization of `@Singleton` beans maybe desirable in certain scenarios, such as AWS Lambda where more CPU resources are assigned to Lambda construction than execution.**
- We can specify whether to eagerly initialize `@Singleton`-scoped beans using `ApplicationContextBuilder` interface.
- please see the illustration: [Application](src/main/java/com/shshetudev/Application.java)
- When we use Micronaut in environments such as **Serverless Functions**, we will not have an Application class and instead we extend a Micronaut-provided class.
- In those cases, Micronaut provides methods which we can override to enhance the **ApplicationContextBuilder**. **(Not implemented)**
> `public class MyFunctionHandler extends MicronautRequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {` <br/>
> `@Nonnull`<br/>
> `@Override`<br/>
> `protected ApplicationContextBuilder newApplicationContextBuilder() {` <br/>
> `ApplicationContextBuilder builder = super.newApplicationContextBuilder();` <br/>
> `builder.eagerInitSingletons(true);` <br/>
> `return builder;` <br/>
> `}` <br/>
> `...` <br/>
> `}`

- `@ConfigurationReader` beans such as `@EachProperty` or `@ConfigurationProperties` are singleton beans.
- To eagerly init configuration but we keep other `@Singleton`-scoped bean creation lazy, we use `eagerInitConfiguration`. **(Not implemented)**
> `public class Application {` <br/>
> `public static void main(String[] args) {` <br/>
> `Micronaut.build(args)` <br/>
> `.eagerInitConfiguration(true)` <br/>
> `.mainClass(Application.class)` <br/>
> `.start();` <br/>
> `}` <br/>
> `}`

