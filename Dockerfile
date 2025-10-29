FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/AI_Crypto_advisor-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD java -Dserver.port=${PORT:-8080} \
    -Dspring.datasource.url=${DATABASE_URL} \
    -Dspring.datasource.driver-class-name=org.postgresql.Driver \
    -Dspring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect \
    -Dspring.jpa.hibernate.ddl-auto=update \
    -jar app.jar