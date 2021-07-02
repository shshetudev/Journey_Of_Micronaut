Link: https://medium.com/@mesirii/ad-astra-the-micronaut-framework-52ff2d684877
### Introduction
* It's not often that a new application framework is introduced in the Java world.
* Especially for microservices, Serverless (FaaS), mobile applications (Android), and stream processing, fast start-up and efficient operations are important because latency requirements are high. 
* So for, most Java frameworks have not been famous for that.
* The developers of OCI around Graeme Rocher have developed Micronaut, a complete (full stack and cloud-native) framework that combines the convenience of Spring with the speed of handwritten code.
* Micronaut is licensed Apache 2.0 and supports application development in Java, Kotlin and Groovy.
* Since it's not based on reflection, the framework can also be used on Android.

### Features of Micronaut
* Dependency injection
* Simple configuration with reasonable standards
* Asynchronous APIs
* Little boilerplate code
* Service discovery
* Scalable HTTP clients
* Asynchronous HTTP servers with routing, middlewire, security etc.

### Speciality
* __Compile-time code generation__ and weaving

* __Dependency injection:__
  * DI is supported like in Spring by an IOC (Inversion of control) container.
  * All injection and bean provisioning, ASM generated code implements the provisioning and configuration of instances (prototypes, singletons and other scopes).
  * This removes the classpath and reflection scan during the launch of the application, as well as the caches for all reflection information (annotations, fields, methods, constructors).
  * On the other hand, the JVM can optimize and inline the generated code as usual in the JIT process.
  * Since in complex setups chains of beans must often be instantiated, the performance increase is cumulative.

* __Code generation:__
  * The code generation takes place in annotation processors (Java), AST transformations (Groovy) or compiler plugins (Kotlin, kapt).
  * The collected annotation information will be made available at runtime using `BeanDefination`.
  * Constructors, fields or setters can be annotated as usual with `@Inject`.
  * Beans can optionally be marked as `@Singleton` or created from `Factory @Bean` annotated methods.
  * There is also an optional `BeanContext` like in Spring, which we rarely need to use.
    
* __Bean Qualification:__
  * There are qualifications for beans with various annnotations and also scopoes like `@Context`, `@Infrastruture` or `@ThreadLocal`
    or own variants.
  * `@Refreshable` is a scope of the beans newly generated by external trigger (eg vai API or `RefreshEvent`).
  * With `@Requires` very flexible conditions based on configuration, environment, etc. can be linked to the availability of beans.
  * Beans in Micronaut, unlike Spring, have no names, just their types and possibly qualifiers, causing fewer ambiguity issues.
  * For this purpose, with `@Replaces` a replacement bean can be defined in a certain context, if the conditions of the first bean ar not met or in a test scenario.
  * Whole groups of beans within a package can be configured with `@Configuration` annotation in `package-info.java`.
  * All annotated beans in the classpath are discovered and linked during the build process.
  * Dependencies on libraries for persistence, orchestration, etc are resolved at the same time and their services are made available as beans.
    
* __Configuration:__
  * As in Spring Boot or Grails, configuration information from property- JSON, YAML or Groovy-files is used directly but can be overridden by environment variables or system properties.
    