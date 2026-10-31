# =========================================================================
# DOCKERFILE DA API ESTACIONA+
# =========================================================================
# Usamos dois estágios de build:
#   1) builder >> utiliza JDK e Maven para compilar o projeto para um .jar
#   2) runner  >> utiliza JRE e o .jar compilado para executar a API
# =========================================================================


# -------------------------------------------------------------------------
# ESTÁGIO 1 – BUILD
# -------------------------------------------------------------------------

# Utiliza a implementação JDK Temurin, com Alpine Linux como sistema base.
FROM eclipse-temurin:21-jdk-alpine AS builder

# Define o diretório /build como workdir
WORKDIR /build

# Copia arquivos do Maven
COPY pom.xml mvnw ./
COPY wrapper .mvn/wrapper

# Executa um comando Shell, onde:
# - sed: remove o caractere de retorno de linha utilizado pelo Windows ('\r');
# - chmod: atribui permissões de execução para o wrapper do Maven;
# - ./mvnw: executa o wrapper do Maven, onde:
#   - -B: exibe logs sem colorização;
#   - --no-transfer-progress: oculta indicativos de progresso;
#   - dependency:go-offline: coleta as dependências do Maven utilizadas no projeto,
#     armazenando na pasta ~/.m2 como cache para a agilização de builds futuros.
RUN sed -i 's/\r$//' mvnw \
    && chmod +x mvnw \
    && ./mvnw -B --no-transfer-progress dependency:go-offline

# Copia o código fonte da máquina hospedeira para a imagem.
COPY src ./src

# Finalmente, o projeto é compilado em um arquivo .jar, ignorando testes com -DskipTests.
RUN ./mvnw -B --no-transfer-progress -DskipTests package


# -------------------------------------------------------------------------
# ESTÁGIO 2 – RUNTIME
# -------------------------------------------------------------------------

# Utiliza a implementação do JRE Temurin, assim mantendo apenas o necessário para o runtime.
FROM eclipse-temurin:21-jre-alpine AS runner

# Adiciona um usuário Linux para evitar exploits de segurança, onde:
# - addgroup: cria um grupo chamado "spring";
# - adduser: cria um usuário chamado "spring", que é adicionado ao grupo "spring" e então é realizado o login.
RUN addgroup -S spring && adduser -S spring -G spring

# Define o diretório /app como workdir
WORKDIR /app

# Copia o resultado .jar e renomeia para app.jar, onde:
# - --from=builder: define a origem dos arquivos como o estágio builder,
#   onde foi realizado o build do projeto;
# - --chown=spring:spring: define o usuário "spring" do grupo "spring" como dono do arquivo.
COPY --from=builder --chown=spring:spring /build/target/*.jar app.jar

# Utiliza o usuário "spring" para as próximas execuções.
USER spring

# Abre a porta 8080 para permitir a conexão com o Tomcat utilizado pela API.
EXPOSE 8080

# Define o primeiro comando a ser executado pelo contêiner gerado a partir das imagens utilizadas acima, onde:
# - java: executa um programa Java;
# - -XX:MaxRAMPercentage=75.0: limita o uso máximo de RAM disponibilizado ao contêiner para 3/4 do seu total;
# - -jar app.jar: indica que o programa já contém um .jar, informando-o.
ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
