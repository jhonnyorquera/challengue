FROM openjdk:8-jdk-alpine
ADD tar/cuenta-0.0.1-SNAPSHOT app.jar
ENTRYPOINT ["java","-jar","app.jar"]