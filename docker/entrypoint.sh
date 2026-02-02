#!/bin/bash
set -e

PORT_TO_USE=${PORT:-8080}

# Levanta WildFly escuchando en el puerto que Render asigna
/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port=${PORT_TO_USE} &

# Espera a que WildFly esté "running"
until /opt/jboss/wildfly/bin/jboss-cli.sh -c ":read-attribute(name=server-state)" | grep -q running; do
  echo "Waiting for WildFly..."
  sleep 2
done

# Configura driver + datasource
/opt/jboss/wildfly/bin/jboss-cli.sh -c --file=/opt/jboss/wildfly/cli-configure-ds.cli

# Mantiene el contenedor vivo
wait
