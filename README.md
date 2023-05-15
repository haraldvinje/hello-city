# Hello City Example App

Simple sample CRUD API for getting, adding and deleting cities. Supports REST and graphQL. Main technologies: [Kotlin](https://kotlinlang.org), [Gradle](https://gradle.org/), [Spring Boot](https://spring.io/projects/spring-boot), [Google Cloud Run](https://cloud.google.com/run)

## Develop

```bash
./gradlew bootRun
```

### Example query

#### Get All cities

**Rest:** ```GET localhost:8080/api/city```

**GraphQL:** ```POST localhost:8080/graphql```
```
query GetCities {
    cities {
        id
        name
        description
    }
}
```

## Production build

```bash
./gradlew build
java -jar build/libs/<appname>-<version>.jar --args="--spring.profiles.active=production"
```
Replace ```<appname>```  with your application name (usually the value of ```rootProject.Name``` in [settings.gradle.kts](settings.gradle.kts)) and ```<version>``` with version (usually the value of ```version``` in [build.gradle.kts](build.gradle.kts)).