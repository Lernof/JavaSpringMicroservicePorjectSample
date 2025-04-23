FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8755

ENTRYPOINT ["java", "-jar", "app.jar"]
