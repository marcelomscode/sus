# Etapa 1: Usando uma imagem base do Java
FROM openjdk:17-jdk-slim AS build

# Diretório de trabalho dentro do container
WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=dev

# Copiar o arquivo JAR para o container
COPY target/sus-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]