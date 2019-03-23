# Log-Service

This project is a Spring Boot, Cassandra and Spring Cloud project.

## Development server

Run `Log-Service Main class` for a dev server. Navigate to `http://localhost:8002/`. The app will automatically reload if you change any of the source files.

## Build

Run `gradlew clean build`

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar app.jar
