# Usa la imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# El argumento para tu archivo JAR (ejecutable de Spring Boot)
ARG JAR_FILE=target/*.jar

# Copia el JAR generado al contenedor
COPY ${JAR_FILE} app.jar

# Define el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
