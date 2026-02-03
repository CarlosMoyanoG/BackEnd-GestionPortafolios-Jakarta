#!/bin/bash
set -euo pipefail

PORT_TO_USE="${PORT:-8080}"
echo "Using PORT=${PORT_TO_USE}"

# 1) Arrancar WildFly (en background para poder configurar DS antes del deploy)
 /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port="${PORT_TO_USE}" &
WF_PID=$!

# 2) Esperar a que el server esté listo
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

echo "WildFly is running. Configuring datasource..."

# 3) Crear driver + datasource
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-configure-ds.cli

echo "Datasource configured. Deploying WAR..."

# 4) Desplegar el WAR (solo después del datasource)
DEPLOY_DIR="/opt/jboss/wildfly/standalone/deployments"

# limpieza por si quedó un estado anterior
rm -f "${DEPLOY_DIR}/app.war.failed" "${DEPLOY_DIR}/app.war.isdeploying" "${DEPLOY_DIR}/app.war.deployed" "${DEPLOY_DIR}/app.war.dodeploy" 2>/dev/null || true

cp /opt/jboss/wildfly/app.war "${DEPLOY_DIR}/app.war"
touch "${DEPLOY_DIR}/app.war.dodeploy"

echo "WAR deployed trigger created."

# 5) Mantener el contenedor vivo: WildFly como proceso principal
wait "${WF_PID}"
