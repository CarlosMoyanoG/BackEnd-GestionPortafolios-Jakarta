# =========================
# 1) Build stage (Maven)
# =========================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copia POM primero para cache de dependencias
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copia el código
COPY src ./src

# Compila WAR
RUN mvn -q -DskipTests clean package

# =========================
# 2) Runtime stage (WildFly)
# =========================
FROM quay.io/wildfly/wildfly:27.0.1.Final-jdk17

# --- Driver PostgreSQL como módulo ---
USER root
RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main

ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar \
    /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/postgresql-42.7.3.jar

RUN bash -c 'cat > /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/module.xml << "EOF"\n\
<?xml version="1.0" encoding="UTF-8"?>\n\
<module xmlns="urn:jboss:module:1.9" name="org.postgresql">\n\
  <resources>\n\
    <resource-root path="postgresql-42.7.3.jar"/>\n\
  </resources>\n\
  <dependencies>\n\
    <module name="javax.api"/>\n\
    <module name="javax.transaction.api"/>\n\
  </dependencies>\n\
</module>\n\
EOF'

# --- Copiar el WAR generado en el stage build ---
# OJO: esto toma el WAR real que Maven produce (sea cual sea el nombre)
COPY --from=build /app/target/*.war /opt/jboss/wildfly/standalone/deployments/app.war

# --- Scripts ---
COPY docker/cli-configure-ds.cli /opt/jboss/wildfly/cli-configure-ds.cli
COPY docker/entrypoint.sh /opt/jboss/wildfly/entrypoint.sh

RUN chmod +x /opt/jboss/wildfly/entrypoint.sh
USER jboss

EXPOSE 8080
CMD ["/opt/jboss/wildfly/entrypoint.sh"]
