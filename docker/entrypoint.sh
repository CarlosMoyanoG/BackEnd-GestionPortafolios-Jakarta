#!/bin/bash
set -euo pipefail

PORT_TO_USE="${PORT:-8080}"
echo "Using PORT=${PORT_TO_USE}"

# Arrancar WildFly en el puerto que Render asigna
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port="${PORT_TO_USE}" &
WF_PID=$!

# Esperar hasta que WildFly esté RUNNING
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

echo "WildFly is running. Creating datasource..."

# Crear datasource (driver ya está en deployments)
 /opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-datasource.cli

echo "Datasource OK. Deploying WAR..."

DEPLOY_DIR="/opt/jboss/wildfly/standalone/deployments"

# Limpia flags de deploy previos
rm -f "${DEPLOY_DIR}/GestorProyectos.war."* 2>/dev/null || true

cp /opt/jboss/wildfly/GestorProyectos.war "${DEPLOY_DIR}/GestorProyectos.war"
touch "${DEPLOY_DIR}/GestorProyectos.war.dodeploy"

echo "WAR deploy triggered."

wait "${WF_PID}"
