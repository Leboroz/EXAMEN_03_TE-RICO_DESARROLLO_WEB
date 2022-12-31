FROM gradle:7.2.0-jdk17 AS build

RUN gradle build --no-daemon 

FROM openjdk:17-jdk-slim

EXPOSE 8080

RUN mkdir /app

COPY --from=build /build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
