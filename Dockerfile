FROM openjdk:8-jdk-alpine
EXPOSE 8282
COPY target/DevOps_Project-1.0.jar DevOps_Project.jar
ENTRYPOINT ["java", "-jar", "DevOps_Project.jar"]