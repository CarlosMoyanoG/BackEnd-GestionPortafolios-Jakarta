#!/bin/bash
set -euo pipefail

PORT_TO_USE="${PORT:-8080}"
echo "Using PORT=${PORT_TO_USE}"

# 1) Arrancar WildFly en background usando el puerto que Render te da
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port="${PORT_TO_USE}" &
WF_PID=$!

# 2) Esperar a que el server esté listo
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

echo "WildFly is running. Creating datasource..."

# 3) Configurar driver + datasource
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-datasource.cli

echo "Datasource OK. Deploying WAR..."

# 4) Desplegar WAR DESPUÉS del datasource
DEPLOY_DIR="/opt/jboss/wildfly/standalone/deployments"

# Limpieza marcadores previos
rm -f "${DEPLOY_DIR}/app.war.failed" \
      "${DEPLOY_DIR}/app.war.isdeploying" \
      "${DEPLOY_DIR}/app.war.deployed" \
      "${DEPLOY_DIR}/app.war.dodeploy" 2>/dev/null || true

cp /opt/jboss/wildfly/app.war "${DEPLOY_DIR}/app.war"
touch "${DEPLOY_DIR}/app.war.dodeploy"

echo "WAR deployed."

# 5) Mantener contenedor vivo
wait "${WF_PID}"
