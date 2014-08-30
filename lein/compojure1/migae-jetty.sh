#!/bin/sh

JETTYRUNNER="${HOME}/.m2/repository/org/eclipse/jetty/jetty-runner/9.0.5.v20130815/jetty-runner-9.0.5.v20130815.jar"
JETTY_HOME=/usr/local/java/jetty

CLJ="${HOME}/.m2/repository/org/clojure/clojure/1.5.1/clojure-1.5.1.jar"

# NOTE: 'lein compile' puts class file dependencies from clojure code
# (e.g. from ring, compojure, etc.) in the war/WEB-INF/classes dir,
# which is why they are found at runtime.  But it can only do this if
# things are properly arranged in the leiningen repo
# (~/.m2/repository).  If the artifact name does not match the path
# under which it is stored in the repo then leiningen will not be able
# to find it.

# In this case, the needed class is
# org.apache.commons.codec.binary.Base64.  But this is stored under
# commons-codec in the repo (rather than org/apache/commons/... as
# expected); hence we must explicitly add it to the classpath:
CODEC="${HOME}/.m2/repository/commons-codec/commons-codec/1.7/commons-codec-1.7.jar"
# (NB: is there a way to do this in project.clj?)

case $1 in
    start)
	java -jar ${JETTYRUNNER} \
	    --out jetty.err.log \
	    --log jetty.rqst.log \
	    --classes src/ \
	    --jar ${CLJ} \
	    --jar ${CODEC} \
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
