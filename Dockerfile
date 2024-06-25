#Сборка первого задания
#FROM maven:3.8-jdk-8 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn package -DskipTests
#FROM openjdk:17-slim
#WORKDIR /app
#COPY --from=builder /app/startApp/target/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

#Сборка второго задания
FROM maven:3.6.3-jdk-11-slim AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

FROM openjdk:11-jre-slim
WORKDIR /app
RUN apt-get update && apt-get install -y \
    xvfb \
    libgtk-3-0 \
    libgl1-mesa-glx \
    libx11-6 \
    libavcodec-extra \
    libavformat58 \
    openjfx \
    postgresql-client \
    libglu1-mesa \
 && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/javafx/target/javafx-1.0-SNAPSHOT.jar javafx.jar

ENV PATH_TO_FX=/usr/share/openjfx/lib \
    DISPLAY=:99 \
    DB_HOST=localhost \
    DB_PORT=5432 \
    DB_NAME=searadar \
    DB_USER=postgres \
    DB_PASSWORD=12345

ENTRYPOINT ["xvfb-run", "--auto-servernum", "--server-num=1", "java", "--module-path", "$PATH_TO_FX", "--add-modules", "javafx.controls,javafx.fxml,javafx.graphics,javafx.web", "-jar", "javafx.jar"]

##Тест докер
#FROM maven:3.8.4-openjdk-17-slim AS build
#WORKDIR /app
#COPY . /app
#RUN mvn clean package
#FROM eclipse-temurin:17-jdk
#RUN apt-get update && apt-get install -y \
#    xvfb \
#    libxrender1 \
#    libxtst6 \
#    libxi6 \
# && rm -rf /var/lib/apt/lists/*
#WORKDIR /project
#COPY --from=build /app/javafx/target/javafx-1.0-SNAPSHOT.jar javafx.jar
#COPY --from=build /app/javafx/javafx-sdk-22.0.1 /project/sdk
#ENTRYPOINT Xvfb :99 -ac -screen 0 1280x1024x16 & export DISPLAY=:99 && java -jar --module-path /project/sdk/lib --add-modules javafx.controls,javafx.fxml javafx.jar

