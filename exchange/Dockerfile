FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/exchange-0.0.1-SNAPSHOT.jar

# WORKDIR /app

COPY ${JAR_FILE} exchangeApi.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "exchangeApi.jar"]