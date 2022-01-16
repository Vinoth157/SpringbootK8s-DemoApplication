FROM openjdk:16
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} CloudConfig-0.0.1.jar
ENTRYPOINT ["java","-jar","/CloudConfig-0.0.1.jar"]