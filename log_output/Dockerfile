FROM openjdk:27-ea-jdk-slim
COPY target/log_output-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]