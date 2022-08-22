# Hello World Example App

Main technologies: [Koltin](https://kotlinlang.org), [Gradle](https://gradle.org/), [Spring Boot](https://spring.io/projects/spring-boot), [Google Cloud Run](https://cloud.google.com/run)

## Develop

```bash
./gradlew bootRun
```

## Production build

```bash
./gradlew build
java -jar build/libs/<appname>-0.0.1-SNAPSHOT.jar --args="--spring.profile.active=production"
```
Replace <appname> with your application name.