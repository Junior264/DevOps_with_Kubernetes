FROM openjdk:27-ea-jdk-slim
COPY target/log_output-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]