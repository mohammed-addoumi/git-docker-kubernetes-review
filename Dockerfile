FROM openjdk:17.0.1-jdk-slim

WORKDIR /app

COPY target/app.jar app.jar

CMD ["java","-jar","app.jar"]