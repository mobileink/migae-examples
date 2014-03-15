# Minimal Pure Java Servlet

This is a very simple example whose purpose is not to teach Java
servlet programming but to begin our exploration of how Clojure works.

Purpose: demonstrate how Leiningen handles Java code; jetty-runner and
dev_appserver

## Configuration

### Leiningen

project.clj:

```Clojure
:javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:all"]
:java-source-paths ["src/java"]
:compile-path "war/WEB-INF/classes"
:target-path "war/WEB-INF/lib"
```

`:java-source-paths` tells Leiningen where you have stored you Java
source.  The usual Java requirements with respect to file, directory
path, package, and object naming still applies.

`:compile-path` tells Leiningen where to put byte code (.class files)
resulting from either Java compilation (`lein javac`) or Clojure
aot-compilation (`lein compile`).

`target-path` tells Leiningen where to put jarfiles created by `lein
jar` or `lein uberjar`.

### Java Servlet

Servlet configuration, the Servlet life-cycle, etc. are all well
described in the official documentation, at
http://jcp.org/en/jsr/detail?id=154.

In war/WEB-INF:
```
web.xml  -- standard Servlet deployment descriptor
appengine-web.xml -- required by GAE, ignored by std servlet containers
```

### Compile

    basic0	 $ lein javac

The result should be a file HelloWorld.class in war/WEB-INF/classes.

### Testing

#### Standard Servlet container

Now let's test the servlet, first with lein-migae then with ../migae-jetty.sh.

##### lein-migae

The "lein migae jetty" command makes some assumptions, such as that your jetty runner is called jetty-runner.jar, and is stored in $JARDIR.  See the code at 
https://github.com/mobileink/lein-migae/blob/master/src/leiningen/migae/jetty.clj

To make this work you must:

  1 Decide on a place to put jarfiles.  Good choices are ~/.jar
  (hidden), /usr/local/jar, /usr/local/lib.  Define an environment var
  JARDIR pointing to this dir and put it in ~/.bash_profile: export
  JARDIR="~/.jar"

  2 Run "lein deps" to download the jetty jars; leiningen installs them in the local maven repository.

  3 Find the jetty jars in the local maven repo; usually this is
  ~/.m2/repository.  See the project.clj file to discover the verion
  number to look for.  For example:

    /.m2/repository/org/eclipse/jetty/jetty-runner/9.0.5.v20130815/jetty-runner-9.0.5.v20130815.jar

  4 Make a softlink from the jetty jar to your JARDIR: e.g.

    "~ $ ln -s /.m2/repository/org/eclipse/jetty/jetty-runner/9.0.5.v20130815/jetty-runner-9.0.5.v20130815.jar $JARDIR/jetty-runner.jar"

    While you're at it, make a link for the clojure jar:

    "~ $ ln -s /.m2/repository/org/clojure/clojure/1.5.1/clojure-1.5.1.jar $JARDIR/jetty-runner.jar"

    Making links like this immunizes you from hardcoded version
    numbers.  When you install a new version, you can just redo the
    links instead of editing lots of hardcoded versioned names.



    basic0 $ lein deps   // downloads jetty-runner (see project.clj)
    basic0 $ lein migae jetty start

If not, edit ./migae-jetty.sh as appropriate and do:

    basic0 $ ./migae-jetty.sh start

Use `ps` to verify that it's running and inspect the error log it
creates to make sure there are no errors.

(You can stop the server with `migae-jetty.sh stop` or `lein migae jetty stop`.)

Now send your browser to localhost:8080/.  You should get a "Hello World" result.

Take a look at jetty.err.log and jetty.rqst.log so you'll get an idea
of how jetty tries to communicate with us.

#### GAE dev_appserver

Stop the jetty server (`./migae-jetty.sh stop`) and do:

    basic0 $ ${GAESDK_HOME}/bin/dev_appserver.sh war

where ${GAESDK_HOME} is the path to your GAE SDK installation.

Note the messages in the console.

dev_appserver is configured to support so-called "hot" deployment.  If
you change web.xml or appengine-web.xml, the server will detect this
and reinitialize.  Try changing the version number in
appengine-web.xml to 0-1-1 and watch the console messages.

## License

Copyright © 2013 Gregg Reynolds

Distributed under the Eclipse Public License, the same as Clojure.
