FROM openjdk:8-jdk-alpine
EXPOSE 8282
COPY target/5SIM4-DevOps-1.0.jar 5SIM4-DevOps.jar
ENTRYPOINT ["java", "-jar", "5SIM4-DevOps.jar"]