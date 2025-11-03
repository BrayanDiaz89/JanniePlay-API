#Etapa 1: Compilación, versión dentro de carpeta Gradle wrapper
FROM gradle:8.14.3-jdk21 AS build
#Copio toda la aplicación a /app
COPY --chown=gradle:gradle . /app
#Contexto de ejecución que va a tener
WORKDIR /app
#Correr gradle bootJar Task - (build) - bootJar
RUN gradle bootJar --no-daemon

#Etapa 2: Runtime con JDK 21 Ejecución
FROM eclipse-temurin:21-jdk
WORKDIR /app
#Copiar todo lo que este dentro de build/libs y se renombra a jannie_play.jar
COPY --from=build /app/build/libs/*.jar jannie_play.jar
#Exponer la app en el puerto 8080
EXPOSE 8080
#Ejecutamos la app
ENTRYPOINT ["java", "-jar", "jannie_play.jar"]