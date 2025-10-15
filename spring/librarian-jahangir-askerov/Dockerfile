FROM openjdk:21-jdk-slim

COPY target/myApplication.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8888
