FROM java:8-jdk-alpine 
MAINTAINER Oliver Lima

WORKDIR /usr/app

COPY ./target/site-0.0.1-SNAPSHOT.jar /usr/app

ENTRYPOINT ["java","-jar","site-0.0.1-SNAPSHOT.jar"]
