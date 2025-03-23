FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/chothuedonu-0.0.1-SNAPSHOT.jar /app/chothuedonu-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "chothuedonu-0.0.1-SNAPSHOT.jar"]