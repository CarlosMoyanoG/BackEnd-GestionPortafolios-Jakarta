#!/bin/bash
set -e

PORT_TO_USE=${PORT:-8080}

# 1. Arrancar WildFly
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port=${PORT_TO_USE} &

# 2. Esperar a que WildFly esté listo
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

# 3. Crear driver + datasource
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-configure-ds.cli

# 4. AHORA desplegar el WAR
DEPLOY_DIR=/opt/jboss/wildfly/standalone/deployments
cp /opt/jboss/wildfly/app.war $DEPLOY_DIR/app.war
touch $DEPLOY_DIR/app.war.dodeploy

# 5. Mantener contenedor vivo
wait
