# Databases Integration

It is possible to choose the database to run during 
the tests using the `spring.profiles.active` property.

By default it is used `h2`, however it can be a good idea run the tests also in a real db.  

The different profile environment variables can be defined in the `application-<PROFILE_NAME>.properties` located in the resources test folder.
With IntelliJ Idea be sure that all the folders are [correctly identified](https://github.com/LordAlucard90/HowTo-Dev/blob/master/jetbrains/intellij_idea.md#set-folders-type).

There are multiple methods to [set the current spring profile](), two of this are:
- set the property in `application.properties` files
  ```yaml
  spring.profiles.active=h2
  ```
- set the annotation on the test class (this method overrides the previous).
  ```java
  @ActiveProfiles("mysql")
  public class SimpleEntityTest {
      // tests
  }
  ```




