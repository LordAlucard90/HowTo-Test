# Lombok

[Project Lombok](https://projectlombok.org/) is a powerful way to auto generate code using simple annotations.

All the available annotations can be found [here](https://projectlombok.org/features/all) or in the [javadoc](https://projectlombok.org/api/lombok/package-summary.html).

Some annotations can be placed both on the class, on the filed or on the method.

The available annotations are:
- Variable Declaration (Val And Var)

    In java types definitions can be very long, especially in for loops.
    To avoid long declaration can be used `val` or `var`.
    
    Their main difference is the mutability, `val` is not mutable as opposed to `var` that is mutable.

- NotNull Method Parameter 

    It is possible to annotate a method parameter as `@NotNull` to generate an automatic null check that throws an exception if it is null.
    
    When the annotation is placed on a constructor then null check is placed after the `super()` or `this()` calls.

- CleanUp

    TBD

- Accessors

    When a field is defined in a class usually are also defined getters and setters resulting in many not so useful lines of code.
    
    '@Getter' and '@Setter' annotation produce the same code in only one line.
    
    The annotations can be placed either on the class and on the field. 

- ToString

    This annotation creates automatically an implementation for the `toString` method. 

- EqualsAndHashCode

    This annotation creates automatically an implementation for the `equals` and `hasCode` methods. 

    By default will be included all the non static fields with name and value.

- Constructors

    These annotations creates automatically an implementation for the chosen constructor. 

    For RequiredArgsConstructor by default will be included all the non null and final not initialized fields.

    For AllArgsConstructor by default will be included all the fields except for final already initialized.

- Data And Value

    These are used to wrap other annotations.
    
    Data: `@ToString`, `@EqualsAndHashCode`, `@Getter` on all fields, `@Setter` on all non-final fields, `@RequiredArgsConstructor`.
        
    Value: `final` on class, `private` and `final` on all fields, `@ToString`, `@EqualsAndHashCode`, `@Getter` on all fields, `@AllArgsConstructor`.

- Builder

    TBD

- SneakyThrows

    TBD

- Synchronized

    TBD

- With

    TBD

- Log

    TBD



