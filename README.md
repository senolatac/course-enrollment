# Course Enrollment Microservices, Spring Cloud, Spring Boot, Angular 7, MySQL, Cassandra Docker, Hibernate, Liquibase

The application structure is as follows.
- **microservice-user-management** - Microservice implemented using Spring boot. [More info](microservice-user-management/README.md)
- **microservice-course-management** - Microservice implemented using Spring boot. [More info](microservice-course-management/README.md)
- **microservice-log-management** - Microservice implemented using Spring boot. [More info](microservice-log-management/README.md)
- **eureka-discovery-service** - Microservice implemented using Spring eureka. [More info](eureka-discovery-service/README.md)
- **zuul-gateway-service** - Microservice implemented using Spring zuul. [More info](zuul-gateway-service/README.md)
- **course-enrollment-client** - A NodeJs application implemented using Angular 7. This consumes services hosted by server side.  [More info](course-enrollment-client/README.md)
- **docker-compose.yml** - Docker compose file to run server services and course-enrollment-client in container.

### Build

#### 1) Build Docker images and run it in containers using docker-compose from parent directory.
   This also create container for Mysql and run it.
   
```
$ docker-compose up
```

NOTE: To run without docker container follow [steps](faster-code-server/README.md) in faster-code-server project.

#### 2) Build and run course-enrollment-client application (Important: docker-compose up will handle everything so this is not necessary part.)

```
$ cd course-enrollment-client
$ docker build -t course-enrollment-client .
$ docker run -d -p 4200:80 course-enrollment-client
```

### Access application using following URL

```
http://localhost:4200
```

