### Micronaut AOP

### Intoduction to AOP

* AOP has a long history in the Java community, with a variety of different implementations, including  a custom Java language extension called AspectJ.
* Most developers are exposed to AOP via annotations, where we explicitly apply AOP "advice" to a method.
* The most famous example of this in the Java community is probably Spring's **@Transactional** annotation, which allows us to demarcate a method as running within the context of a declared transaction.
* This is what is known as "Around Advice", where we decorate a method invocation with new behavior that implements a cross-cutting concern.

### Advice types

* __Arround Advice__
We decorate an existing method with a new behvior.

* __Introduction Advice__
i. Introduction Advice differs, in that it allows  us to introduce new behavior to an existing class.
ii. A great example of this is, in fact, Micronaut Data (and Spring Data), which allows us to declare an interface that the compiler implements for us by introducing new behavior.

* __Adapter Advice__
i. Adapter advice is unique to Micronaut.
ii. It allows us to introduce a new bean that implements SAM type, an interface with a single abstract method, and delegates to any method defination.

### Why Micronaut implements its own AOP mechanism, rather than rely on something already out there?

* Existing implementations for both Java/Jakarta EE and Spring rely heavily on a mixture of runtime reflection, JDK proxies, and byte code generation with something like CGLib or Bytebuddy.

* __Even Quraks currently only implements reflection-free DI and not AOP.__

* Micronaut depedendecny injection is completly reflection free, so it made sense for Micronaut's AOP mechanism to be reflection free as well.

### Micronaut AOP Setup

* Micronaut AOP is simple to use when compared to other implementations out there.
* It is literally just a compiler feature.
* There is no need to set up complex ProxyFactoryBean implemenations or rely on a runtime container.
* The minimum set of requirements to get going with Micronaut AOP is to add the Micronaut annotation processors and declare a dependency on micronaut-aop in your build.