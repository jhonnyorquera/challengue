FROM openjdk:17-jdk-slim
ADD tar/cuenta-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]