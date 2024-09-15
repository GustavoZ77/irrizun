# Usamos una imagen base de Maven para compilar el proyecto
FROM gradle:8.10.1-jdk17-alpine AS build

# Configuramos el directorio de trabajo dentro del contenedor
WORKDIR /app

RUN apk update && apk add git

# Clonamos el repositorio de Git (reemplaza con tu repositorio)
RUN git clone https://github.com/GustavoZ77/irrizun.git .

# Construimos el proyecto usando Maven
RUN gradle --version
RUN gradle clean
RUN gradle build
RUN gradle bootJar

# Usamos una imagen ligera de OpenJDK para ejecutar la aplicación
FROM alpine/java:17-jdk

# Directorio de trabajo para la aplicación
WORKDIR /app

# Copiamos el archivo JAR desde la fase de compilación
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto en el que corre la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
