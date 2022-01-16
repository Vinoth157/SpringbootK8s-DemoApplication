FROM openjdk:16
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} AddressService-0.0.1.jar
ENTRYPOINT ["java","-jar","/AddressService-0.0.1.jar"]