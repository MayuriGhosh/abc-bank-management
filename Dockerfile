FROM eclipse-temurin:17

WORKDIR /app

COPY target/abc-bank-management-0.0.1-SNAPSHOT.jar /app/abc-bank-management.jar

LABEL app='abc-bank-management'

ENTRYPOINT ["java", "-jar", "abc-bank-management.jar"]