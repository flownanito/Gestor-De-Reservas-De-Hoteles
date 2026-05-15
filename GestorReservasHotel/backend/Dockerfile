# Etapa 1: Construcción con Maven
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución (Cambiamos alpine por slim para compatibilidad con Mac ARM64)
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Define el punto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
