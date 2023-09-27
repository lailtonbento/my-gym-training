FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
WORKDIR /My-Gym-Training
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

ENTRYPOINT ["java","-jar","/app.jar"]
