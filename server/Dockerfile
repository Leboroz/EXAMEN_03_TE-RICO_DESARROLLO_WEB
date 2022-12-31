FROM openjdk:17-jdk-slim

RUN ./gradlew build -x test

COPY build/libs/examen-0.0.1-SNAPSHOT.jar examen.jar

ENTRYPOINT ["java","-jar","examen.jar"]
