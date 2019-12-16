FROM openjdk:8-jre-slim AS base
WORKDIR /app
EXPOSE 80

FROM openjdk:8-jdk-stretch AS build
WORKDIR /tmp
COPY . .
RUN rm -rf src/main/resources/application.properties
RUN mv src/main/resources/application.prod.properties src/main/resources/application.properties
RUN chmod +x mvnw
RUN ./mvnw package

FROM base
WORKDIR /app
COPY --from=build /tmp/target/NguyenNKRetailSystem-1.0.jar .
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/NguyenNKRetailSystem-1.0.jar"]