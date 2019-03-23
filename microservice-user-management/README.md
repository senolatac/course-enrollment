# User-Service

This project is a Spring Boot, MySQL, Hibernate and Liquabase project.

## Development server

Run `User-Service Main class` for a dev server. Navigate to `http://localhost:8000/`. The app will automatically reload if you change any of the source files.

## Build

Run `gradlew clean build`

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar app.jar
