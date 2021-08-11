# Built-In Scopes

There are number of built-in scopes in micronaut:

- `@Singleton`: Indicates only one instance of the bean will exist.
- `@context`: Indicates that the bean will be created at the same time as the `ApplicationContext` (eager initializatio).
- `@Prototype`: Indicates that a new instance of the bean is created each time it is injected.
- `@Infrastructure`: Represents a bean that can not be overriden or replaced using `@Replaces` because it is critical to the functioning of the system.
- `@ThreadLocal`: Represents a custom scope that associates a bean per thread via a `ThreadLocal`.
- `@Refreshable`: A custom scope that allows a bean's state to be refreshed via the `/refresh` endpoint. 
- `@RequestScope`: A custom scope that indicates a new instance of the bean is created and associated with each `HTTP` request.

## Custom scope declaration

- **The `@Prototype` annotation is a synonym for `@Bean` because the default scope is prototype.**
- Additional scopes can be added by defining `@Singleton` bean that implements the `CustomScope` interface.
- When starting an `ApplicationContext` by default `@Singleton`-scoped beans are created lazily and on-demand. This is by design to optimaize startup time.
- If this presents a problem for our use case we have the option for using the `@Context` annotation which binds the lifecycle our our object to the life cycle of `ApplicationContext`.
- In other words whe the `ApplicationContext` is started our bean will be created.
- **Alternatively we annotate any `@Singleton`-scoped bean with `@Parallel` which allows parallel intialization of our bean without impacting overall startup time.**
