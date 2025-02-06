FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar app.jar
RUN mkdir -p /etc/ssl/certsRUN mkdir -p /etc/ssl/certs
COPY keystore/keystore.p12 /etc/ssl/certs/keystore.p12
ENTRYPOINT ["java", "-jar", "app.jar"]