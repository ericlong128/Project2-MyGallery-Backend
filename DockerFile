FROM openjdk:8-jdk-alpine

# Copy the JAR from the target folder into the container
COPY /target/project2-backend.jar project2-backend.jar 

EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/project2-backend.jar"]
