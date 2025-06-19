# Etapa 1: Usando uma imagem base do Java
FROM openjdk:17-jdk-slim AS build

# Diretório de trabalho dentro do container
WORKDIR /app

# Definindo argumentos que serão recebidos na build
ARG SPRING_PROFILES_ACTIVE
ARG CLASS_NAME
ARG DATA_BASE_URL
ARG HIBERNATE_DIALECT
ARG PASSWORD
ARG PORT_SERVER
ARG USER_NAME

# Exportando os argumentos como variáveis de ambiente
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
ENV CLASS_NAME=${CLASS_NAME}
ENV DATA_BASE_URL=${DATA_BASE_URL}
ENV HIBERNATE_DIALECT=${HIBERNATE_DIALECT}
ENV PASSWORD=${PASSWORD}
ENV PORT_SERVER=${PORT_SERVER}
ENV USER_NAME=${USER_NAME}

# Copiar o arquivo JAR para o container
COPY target/sus-0.0.1-SNAPSHOT.jar app.jar

# Comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
