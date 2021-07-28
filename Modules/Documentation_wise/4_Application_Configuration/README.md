## Applcation Configuration
* Configuration in Micronaut takes inspiration from both __Spring and Grails,__ __integrating configuration properties from multiple sources directly into the core IoC container__.
* Configuration can by default be provided in Java properties: __YAML, JSON or Groovy files.__
* The convention is to search for a file named __application.yml, application.properties, application.json or application.groovy.__
* In addition, like Spring and Grails, __Micronaut allows overriding any property via system properties or environment variables.__
* Each source of configuration is modeled with __PropertySource__ interface and the mechanism is extensible, allowing the implementation of addtional __PropertySourceLoader__ implementations.

### The Environment
* The environment is modelled by the __Environment__ interface, which allows specifying one or many unique environment names when creating an __ApplicationContext.__
    >`ApplicationContext applicationContext = ApplicationContext.run("test","android");`
    `Environment environment = applicationContext.getEnvironment();`
    `assertTrue(environment.getActiveNames().contains("test"));`
    `assertTrue(environment.getActivenNames().contains("android"));`

* __The active environment names allow loading different configuration files depending on the environment__, and also using the `@Requires` annotation to conditionally load beans or bean `@Configuration` packages.
* __In addition, Micronaut attempts to detect the current environments.__
* For example, within a Spock or JUnit test the `TEST` environment is automatically active.
* Additional active environments can be specified using the `micronaut.environments` system property or the `MICRONAUT_ENVIRONMENTS` environment varaible.
* These are specified as a comma-seperated list. For example:
  > `java -Dmicronaut.environments=foo,bar -jar myapp.jar`
* Those above environments are called `foo` and `bar`.
* __Finally Cloud environments names are also detected.__

### Environment Priority
* Micronaut loads property sources based on the environments specified.
* __If the same property key exists in multiple property sources specific to an environment, the environemnt order determines which value to use.__
* Micronaut uses the following hierarchiy for __environment processing (lowest to highest priority):__
  * Deduced environments.
  * Environments from the `micronaut.environments` system property.
  * Environments from the `MICRONAUT_ENVIRONMENTS` environment variable.
  * __Environments specified explicitly through the applciation context builder.__
  * This also applies: `@MicronautTest(environments=...)`


### Disabling Environment Detection
* __Automatic detection of environments can be disabled by setting the `micronaut.env.deduction` system property or the `MICRONAUT_ENV_DEDUCTION` environment variable to false.__
* This prevents Micronaut from detecting current environments, while still using any environments that are specifically provided as show above:
  `java -Dmicronaut.env.deduction=false -jar myApp.jar`
  * Alternatively, we can disable environment deduction using the `ApplicationContextBuilder deduceEnvironment` method when setting up our application.
    > `​@Test`
    `public void testDisableEnvironmentDeductionViaBuilder() {`
    `ApplicationContext ctx = ApplicationContext.builder().deduceEnvironment(false).start();`
    `assertFalse(ctx.getEnvironment().getActiveNames().contains(Environment.TEST));`
    `ctx.close();`
    `}`

    ### Default Environment
    * __Micronaut supports the concept of one or may default environments.__
    * A default environemnt is one that is only applied __if no other environemnts are explicitly specified or deduced.__
    * __Environments can be explictily specified either through the application context builder `Micronaut.build().environments(...)`, through the `micronaut.environments` system property, or the `MICRONAUT_ENVIRONMENTS` environment variable.__
    * Environments can be deduced to automatically apply the environment appropriate for cloud deployments.
    * If an environment is found through any of the above means, the default enviromnet will not be applied.
    * To set the default environments, we have to modify our application main method:
    > `public static void main(String[] args) {`
    `Micronaut.build(args)`
    `.mainClass(Application.class)`
    `.defaultEnvironments("dev")`
    `.defaultEnvironments("dev")`
    `}`

### Micronaut Banner
* __Since Micronaut 2.3 a banner is shown when the application starts.__
* It is enabled by default and it also shows the Micronaut version.
* We can get it by simply running: `./gradlew run`.
* __To customize the banner with our own ASCII art (just plain ASCII at this moment), we have to create the file `src/main/resources/micronaut-banner.txt` and it will be used instead.__
* To disable it, we have to modify our `Application` class:
  > `public class Application {` 
  `public static void main(String[] args) {`
  `Micronaut.build(args)`
  `.banner(false) `
  `.start();`
  `}`
  `}`

### Externalized Configuration with PropertySources
* Additional `PropertySource` instances can be applied to the environment prior to intializing the `ApplicationContext`.
    > `​ApplicationContext applicationContext = ApplicationContext.run(`
    > `PropertySource.of(`
    > `"test",`
    > `CollectionUtils.mapOf(`
    > `​"micronaut.server.host", "foo",`
    > `​"micronaut.server.port", 8080`
    > `​)`
    > `),`
    > `​"test", "android");`
    > `nvironment environment = applicationContext.getEnvironment();`
    > `assertEquals(`
    > `foo",`
    > `environment.getProperty("micronaut.server.host",`
    > `String.class).orElse("localhost")`
    > `​);`
* The `PropertySources.of` method can be used to create a `PropertySource` form a map of values.
* Alternatively one can register a `PropertySouceLoader` by creating a `META-INF/services/io.micronaut.context.env.PropertySourceLoader` file containing a reference to the class name of the `PropertySourceLoader`.

### Included PropertySource Loaders
* Micronaut by default contains `PropertySourceLoader` implmentations that load properties from the given locations and priority.
    * Command line arguments.
    * Properties from `SPRING_APPLICATION_JSON`__(for Spring compatibility)__
    * Properties from `MICRONAUT_APPLICATION_JSON`
    * Java System Properties
    * OS environment variables.          