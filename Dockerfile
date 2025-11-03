# ---------- Etapa de build (Maven + JDK21) ----------
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos POM primero para aprovechar cache de dependencias
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copiamos el código y construimos el WAR
COPY src ./src
RUN mvn -q -e -DskipTests clean package

# ---------- Etapa de runtime (Tomcat 10 + JDK21) ----------
FROM tomcat:10.1-jdk21-temurin
# Limpia apps por defecto
RUN rm -rf /usr/local/tomcat/webapps/*

# Copia el WAR construido como ROOT.war (app en /)
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
