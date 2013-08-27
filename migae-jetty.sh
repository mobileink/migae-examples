#!/bin/sh

CLJ="${HOME}/.m2/repository/org/clojure/clojure/1.5.1/clojure-1.5.1.jar"
JETTYRUNNER="${HOME}/.m2/repository/org/eclipse/jetty/jetty-runner/9.0.5.v20130815/jetty-runner-9.0.5.v20130815.jar"
JETTY_HOME=/usr/local/java/jetty

case $1 in
    start)
	java -jar ${JETTYRUNNER} \
	    --out jetty.err.log \
	    --log jetty.rqst.log \
	    --classes src/ \
	    --jar ${CLJ} \
	    --stop-port 8123 \
	    --stop-key migae \
	    war \
	    1>jetty.err.log 2>&1 &
	;;
    stop)
	(echo "migae"; echo "stop"; sleep 1;)| telnet localhost 8123
	;;
    help)
	java -jar ${JETTYRUNNER} --help
	;;
esac
