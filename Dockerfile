# Usamos una imagen base de gradle
FROM gradle:8.10.1-jdk17-alpine AS build

# Configuramos el directorio de trabajo dentro del contenedor
WORKDIR /app

RUN apk update && apk add git


# Limpiamos cualquier archivo viejo antes de clonar
RUN rm -rf /app/*

# Clonamos el repositorio de Git
RUN git clone https://github.com/GustavoZ77/irrizun.git .
RUN git checkout master
RUN git pull origin master


RUN pwd && ls -l

RUN gradle clean --no-daemon
RUN gradle build -x test --no-daemon

# Usamos una imagen ligera de OpenJDK para ejecutar la aplicación
FROM alpine/java:17-jdk

# Directorio de trabajo para la aplicación
WORKDIR /app

# Copiamos el archivo JAR desde la fase de compilación
COPY --from=build /app/build/libs/*.jar app.jar

# Exponemos el puerto en el que corre la aplicación Spring Boot
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
