FROM openjdk:21-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "-Dspring.profiles.active=prod", "/app.jar"]