FROM maven:3.8.3-openjdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package spring-boot:repackage

FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=/home/app/target/*.jar
COPY --from=build ${JAR_FILE} app.jar
ARG SQL_FILE=/home/app/target/classes/*.db
COPY --from=build ${SQL_FILE} sample.db
ENTRYPOINT ["java","-jar","/app.jar"]
