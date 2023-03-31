FROM amazoncorretto:17.0.6-alpine3.17
MAINTAINER cezaodabahia
WORKDIR /app
COPY ./target/crud_devops-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]