# Usamos una imagen base de Maven para compilar el proyecto
FROM maven:3.8.7-openjdk-17 AS build

# Configuramos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Clonamos el repositorio de Git (reemplaza con tu repositorio)
RUN git clone https://github.com/usuario/repositorio-spring-boot.git .

# Construimos el proyecto usando Maven
RUN mvn clean package -DskipTests

# Usamos una imagen ligera de OpenJDK para ejecutar la aplicación
FROM openjdk:17-jdk-alpine

# Directorio de trabajo para la aplicación
WORKDIR /app

# Copiamos el archivo JAR desde la fase de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto en el que corre la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
