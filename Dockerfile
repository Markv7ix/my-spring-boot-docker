FROM openjdk:17
MAINTAINER baeldung.com
COPY target/my-spring-boot-docker-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]