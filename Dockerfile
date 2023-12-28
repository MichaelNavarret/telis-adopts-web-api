# Usar una imagen base con JDK 21 y Maven
FROM maven:3.9.6-amazoncorretto-21 AS build

# Establecer un directorio de trabajo
WORKDIR /app

# Copiar archivos de tu proyecto al directorio de trabajo
COPY . /app

# Ejecutar Maven para construir el proyecto
RUN mvn clean package

# Crear una nueva imagen basada en OpenJDK 21
FROM openjdk:21-slim-buster

# Exponer el puerto que utilizará la aplicación
EXPOSE 4000

# Copiar el archivo JAR construido desde la etapa anterior
COPY --from=build /app/target/telis-adopt-proyect-0.0.1-SNAPSHOT.jar /app/telis-adopt-proyect-0.0.1-SNAPSHOT.jar

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/telis-adopt-proyect-0.0.1-SNAPSHOT.jar"]