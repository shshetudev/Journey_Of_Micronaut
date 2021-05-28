### What is Micronaut?
* Framework for microservices(but not only) in the JVM.
* Ultra-lightweight
* Reactive (Netty-based)
* Java, Groovy & Kotlin
* Familiar for developers
    * Similar to Spring framework.
* Ahead of Time compilation (AoT)
    * Micronaut runs all the meta informations are generated in compile time.
    * We don't need to cache anything into memory, since at compile time classes are already created having those meta-informations.
* No reflection, no runtime proxies, No dynamic class loading, No dynmaic byte code generation.
* Fast Startup.
* Low memory footprint.
* Natively Cloud Native & Serverless.
  * It provides service discovery
  * Provides eureka, load balancing.
  * Providing securtiy. Almost everything need to attach for cloud based application.
* Micronaut Data
  * Spring Data does all the work of query converstion in runtime but Micronaut Data does it on compile time. 
  * __For example:__ we want to fetch all the data from user table, and the query is converted as follows: `findAll() -> select * from user`. 
  * Micronaut does it in runtime. So we just need to pass the param and rest of the query is already generated in compile time. That's why it is really faster.
* Support for GraalVM out-of-the-box
  * We can compare the Java application into a binary of target platform say: Linux, Windows or Mac.
  * It doesn't have any dependency on JVM.
  * So the application starts really fast and memory consumption is very low.
* Micronaut has Apache 2 License
* +35 releases (1.0.0 Oct 2018)
* +150 contributors
* Latest stable version: 2.5.4 and latest release: 3.0.0 M1 
     