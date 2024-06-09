FROM maven:3.8-jdk-8 AS builder

WORKDIR /app

COPY . .

RUN mvn package -DskipTests

FROM openjdk:17-slim

WORKDIR /app

COPY --from=builder /app/startApp/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]