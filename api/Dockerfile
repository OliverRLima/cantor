FROM openjdk:11-slim
MAINTAINER Oliver Lima

COPY ./target/api-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app/

ENTRYPOINT ["java","-jar","api-0.0.1-SNAPSHOT.jar"]
