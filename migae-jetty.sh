#!/bin/sh

CLJ="${JARDIR}/clojure.jar"
JETTYRUNNER="${JARDIR}/jetty-runner.jar"

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
