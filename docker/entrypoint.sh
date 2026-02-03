#!/bin/bash
set -euo pipefail

PORT_TO_USE="${PORT:-8080}"
echo "Using PORT=${PORT_TO_USE}"

# MATAR cualquier jar residual que bloquee el scanner
rm -f /opt/jboss/wildfly/standalone/deployments/postgresql*.jar \
      /opt/jboss/wildfly/standalone/deployments/postgresql*.jar.* || true

# Arrancar WildFly (en background)
 /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port="${PORT_TO_USE}" &
WF_PID=$!

# Esperar a que el server esté listo
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

echo "WildFly is running. Creating datasource..."
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-datasource.cli

echo "Datasource OK."
wait "${WF_PID}"
