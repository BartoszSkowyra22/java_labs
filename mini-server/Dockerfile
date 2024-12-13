FROM maven:3.8.8-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etap 2: Tworzenie obrazu uruchomieniowego
FROM openjdk:21

LABEL org.opencontainers.image.authors="Bartosz Skowyra"
EXPOSE 5672

COPY --from=builder /app/target/mini-server-0.0.1-SNAPSHOT.jar mini-server.jar
ENTRYPOINT ["java", "-jar", "/mini-server.jar"]