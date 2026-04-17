FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/*.jar fields.jar

CMD ["java", "-jar", "fields.jar"]




