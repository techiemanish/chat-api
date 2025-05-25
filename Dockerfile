#Author: techiemanish
#Github: https://github.com/techiemanish

#Maven Build
FROM maven:latest AS build

ARG MONGO_URI
ENV MONGO_URI=$MONGO_URI

COPY src /usr/src/app/src

COPY pom.xml /usr/src/app

RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:17

COPY --from=build /usr/src/app/target/chat-api-0.0.1-SNAPSHOT.jar /usr/app/chat-api-0.0.1-SNAPSHOT.jar

EXPOSE 8181

CMD ["java", "-jar", "/usr/app/chat-api-0.0.1-SNAPSHOT.jar"]