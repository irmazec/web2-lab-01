
FROM openjdk:19-alpine AS builder

COPY ../../.mvn .mvn
COPY ../../mvnw .
COPY ../../pom.xml .
COPY ../../src src
RUN chmod +x mvnw

RUN mvn clean package

FROM openjdk:19-alpine

COPY --from=builder target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
