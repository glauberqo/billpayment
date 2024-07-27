# Usando uma imagem base do OpenJDK 17
FROM eclipse-temurin:17-jdk-alpine

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiando o arquivo JAR da aplicação para o contêiner
COPY target/billpayment-0.0.1-SNAPSHOT.jar /app/billpayment-0.0.1-SNAPSHOT.jar

# Expondo a porta que a aplicação vai usar
EXPOSE 8080

# Definindo o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "billpayment-0.0.1-SNAPSHOT.jar"]