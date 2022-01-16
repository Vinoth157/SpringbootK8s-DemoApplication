FROM openjdk:16
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ParentService-0.0.1.jar
ENTRYPOINT ["java","-jar","/ParentService-0.0.1.jar"]