# Use an official Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /create-a-cv-backend
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Use a minimal Java runtime for the application
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /create-a-cv-backend/target/your-app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
