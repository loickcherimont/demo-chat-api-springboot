# Dockerfile adapted to host
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy the project into the container /app 
COPY . .

RUN mvn clean package -DskipTests

# Image JDK
FROM amazoncorretto:21-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]