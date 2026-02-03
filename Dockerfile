FROM quay.io/wildfly/wildfly:27.0.1.Final-jdk17

USER root

# ----------------------------
# 1) PostgreSQL JDBC como MÓDULO (org.postgresql)
# ----------------------------
RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main

# Descarga del driver
ADD https://jdbc.postgresql.org/download/postgresql-42.7.3.jar \
    /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/postgresql-42.7.3.jar

# module.xml del módulo
COPY docker/module.xml \
    /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main/module.xml

# Permisos para que WildFly (jboss) pueda leerlo en Render
RUN chown -R jboss:jboss /opt/jboss/wildfly/modules/system/layers/base/org/postgresql/main

# ----------------------------
# 2) Copiar scripts y WAR (NO desplegar aún)
# ----------------------------
COPY docker/cli-datasource.cli /opt/jboss/wildfly/cli-datasource.cli
COPY docker/entrypoint.sh /opt/jboss/wildfly/entrypoint.sh
RUN chmod +x /opt/jboss/wildfly/entrypoint.sh

# WAR lo guardamos fuera del deployments para desplegarlo al final
COPY artifacts/GestorProyectos.war /opt/jboss/wildfly/app.war
RUN chown jboss:jboss /opt/jboss/wildfly/app.war /opt/jboss/wildfly/cli-datasource.cli /opt/jboss/wildfly/entrypoint.sh

USER jboss

EXPOSE 8080
CMD ["/opt/jboss/wildfly/entrypoint.sh"]
