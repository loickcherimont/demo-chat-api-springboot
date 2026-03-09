FROM amazoncorretto:21-alpine

WORKDIR /app

ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT [ "java", "-jar", "app.jar" ]