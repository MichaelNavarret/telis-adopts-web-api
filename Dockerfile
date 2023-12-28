
Aquí tienes la adaptación del Dockerfile para un proyecto Spring con Maven:

Dockerfile
Copy code
# Usar una imagen base con JDK 8 y Maven
FROM maven:3.8.4-openjdk-8-slim AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean install

# Crear una nueva imagen basada en OpenJDK 8
FROM openjdk:21-jre-slim

# Exponer el puerto que utilizará la aplicación
EXPOSE 8080

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/demo-0.0.1-SNAPSHOT.jar"]