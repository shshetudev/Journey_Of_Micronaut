# Aspect Oriented Programming

* Aspect Oriiented Programming (AOP) has historically had many incarnations and some very complicated implementations.
* Generally AOP can be thought of as a way to define cross-cutting concerns (logging, transactions, tracing etc) seperate from application code in the form of aspects that define advice.
* There are typically two forms of advice:
  * Around Advice - decorates a method or class
  * Introduction Advice - introduces new behaviour to a class
* In modern Java applications, declaring advice typically takes the form of an annotation.
* The most well-known annotation advice in the Java world is probably `@Transactional`, which demarcates transaction boundaries in Spring and Grails applications.
* The disadvantages of traditional approaches to AOP is the heavy reliance on runtime proxy creation and reflection, which shows application performance, makes debugging harder and increases memory consumption.
* Micronaut tries to address these concerns by providing a simple compile-time AOP API that does not use reflection.
