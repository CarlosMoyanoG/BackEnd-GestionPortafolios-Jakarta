#!/bin/bash
set -e

PORT_TO_USE=${PORT:-8080}

# Arranca WildFly
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port=${PORT_TO_USE} &

# Espera a que el server esté listo
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

# Crea driver + datasource
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-configure-ds.cli

# AHORA sí: desplegar el WAR
DEPLOY_DIR=/opt/jboss/wildfly/standalone/deployments
rm -f "$DEPLOY_DIR/app.war.skipdeploy"
touch "$DEPLOY_DIR/app.war.dodeploy"

# Mantiene el contenedor vivo
wait
