FROM gradle:7.2.0-jdk17 AS build

COPY . .

RUN ./gradlew build  -x test 

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build ./build/libs/examen-0.0.1-SNAPSHOT.jar examen.jar

ENTRYPOINT ["java","-jar","examen.jar"]
