FROM tomcat:9.0.30-jdk8-openjdk-slim AS base
WORKDIR /app
EXPOSE 80

FROM openjdk:8-jdk-stretch AS build
WORKDIR /tmp
COPY . .
RUN rm -rf target/
RUN rm -rf src/main/resources/application.properties
RUN mv src/main/resources/application.prod.properties src/main/resources/application.properties
RUN chmod +x mvnw
RUN ./mvnw package

FROM base
WORKDIR /app
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /tmp/target/NguyenNKRetailSystem-1.0.war /usr/local/tomcat/webapps/ROOT.war