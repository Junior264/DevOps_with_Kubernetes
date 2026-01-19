FROM openjdk:27-ea-jdk-slim
COPY target/log_output-0.0.1-SNAPSHOT.jar app.jar
RUN mkdir -p /files
CMD ["java","-jar","/app.jar"]