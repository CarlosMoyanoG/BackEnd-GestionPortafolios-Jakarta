FROM quay.io/wildfly/wildfly:27.0.1.Final-jdk17

USER root

# 1) Driver PostgreSQL como DEPLOYMENT (no módulo)
ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar \
    /opt/jboss/wildfly/standalone/deployments/postgresql-42.7.3.jar

# 2) Copia tu WAR ya generado (tú lo pones en /artifacts)
COPY artifacts/GestorProyectos.war /opt/jboss/wildfly/GestorProyectos.war

# 3) Scripts
COPY docker/cli-datasource.cli /opt/jboss/wildfly/cli-datasource.cli
COPY docker/entrypoint.sh /opt/jboss/wildfly/entrypoint.sh
RUN chmod +x /opt/jboss/wildfly/entrypoint.sh

USER jboss

EXPOSE 8080
CMD ["/opt/jboss/wildfly/entrypoint.sh"]
