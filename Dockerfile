FROM openjdk:11.0.2-jdk-slim-stretch
LABEL maintainer="alessandro.travi@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/sales-taxes.jar
COPY ${JAR_FILE} sales-taxes.jar
ENTRYPOINT ["java","-jar","/sales-taxes.jar"]