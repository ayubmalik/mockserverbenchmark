#!/usr/bin/env bash
basedir=$(dirname "${BASH_SOURCE[0]}" )

$JAVA_HOME/bin/java -Xmx512m \
  -Dmockserver.logLevel=DEBUG \
  -Dmockserver.disableSystemOut=false \
  -jar ${basedir}/mockserver-netty-5.11.1-jar-with-dependencies.jar \
  -serverPort 1080
