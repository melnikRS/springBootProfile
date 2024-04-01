FROM amazoncorretto:17-alpine-jdk
EXPOSE 8080
ADD target/springBootProfile-0.0.1-SNAPSHOT.jar appDev.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]