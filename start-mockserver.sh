#!/usr/bin/env bash
basedir=$(dirname "${BASH_SOURCE[0]}" )

$JAVA_HOME/bin/java -Xms1G -Xmx1G \
  -Dmockserver.logLevel=INFO \
  -Dmockserver.disableSystemOut=true \
  -jar ${basedir}/mockserver-netty-5.11.1-jar-with-dependencies.jar \
  -serverPort 1080
