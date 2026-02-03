#!/bin/bash
set -euo pipefail

PORT_TO_USE="${PORT:-8080}"
echo "Using PORT=${PORT_TO_USE}"

# 1) Arrancar WildFly
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port="${PORT_TO_USE}" &
WF_PID=$!

# 2) Esperar WildFly
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

echo "WildFly is running. Configuring datasource..."

# 3) Configurar datasource
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-configure-ds.cli

echo "Datasource configured. Deploying WAR as ROOT..."

# 4) Deploy como ROOT.war para que quede en /
DEPLOY_DIR="/opt/jboss/wildfly/standalone/deployments"

rm -f "${DEPLOY_DIR}/ROOT.war"* 2>/dev/null || true

cp /opt/jboss/wildfly/app.war "${DEPLOY_DIR}/ROOT.war"
touch "${DEPLOY_DIR}/ROOT.war.dodeploy"

echo "ROOT.war deploy trigger created."

# 5) Mantener proceso principal
wait "${WF_PID}"
