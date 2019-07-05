#pull latest gradle image as builder
FROM gradle:latest as builder
#Copy all codes to /app/gradle/src and chown: chnage ownership of files and directories
ADD --chown=gradle:gradle . /app/gradle/src
#Chnage working directory: cd /app/gradle/src
WORKDIR /app/gradle/src
#Create jar file for user-service
RUN gradle clean assemble

#pull java8 image
FROM java:8
#permanent storage folder
VOLUME /tmp
COPY ./wait-for-it.sh ./wait-for-it.sh
#wait-for-it.sh will be executable script.
RUN chmod +x /wait-for-it.sh
#Copy and paste as app.jar
COPY --from=builder /app/gradle/src/build/libs/microservice-user-management-0.0.1.jar app.jar
#Update when it is changed.
RUN bash -c 'touch /app.jar'
#RunTime Arguments=>SecureRandom class will be urandom. It is required for linux. Profile: container
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]
