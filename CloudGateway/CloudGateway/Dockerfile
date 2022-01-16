FROM openjdk:16
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} CloudGateway-0.0.1.jar
ENTRYPOINT ["java","-jar","/CloudGateway-0.0.1.jar"]