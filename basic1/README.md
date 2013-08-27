# Experiment basic1

gen-class magic, demystified

*CAVEAT* This text has not yet been vetted by people Who Know What
 They're Talking About.  It's my best guess as to how the magic of
 Clojure works in the context of a sservlet container.  It's mostly
 correct, but there are some things I say that are based on inference
 rather than knowledge.

## Getting started

Start by running

    basic1 $ lein deps

That will make sure you have the right version of Clojure as well as
the jetty-runner.jar we will use to run our servlets.

## Usage

Start out by using the Leiningen "jar" task:

    basic1 $ lein jar

The result should be a jarfile in war/WEB-INF/lib; that's because our
project.clj contains this clause:

	    :target-path "war/WEB-INF/lib"

Verify:

    basic1 $ ls -l war/WEB-INF/lib
    -rw-r--r--  1 <uid>  <gid>  2986 Aug 24 08:55 basic1-0.1.0-SNAPSHOT.jar
    drwxr-xr-x  3 <uid>  <gid>   102 Aug 24 08:49 stale

You can ignore the "stale" directory; it has something to do with how
Leiningen works.

Now let's test the servlet.  If you've installed lein-migae, do:

    basic1 $ lein migae jetty start

If not, do:

    basic1 $ ./migae-jetty.sh start

Use `ps` to verify that it's running and inspect the error log it
creates to make sure there are no errors.

(You can stop the server with `migae-jetty.sh stop` or `lein migae jetty stop`.)

Now send your browser to localhost:8080/test/foo.  You should get a
503 error, Service Unavailable.  In jetty.err.log you should see
something like:

    2013-08-24 10:01:19.468:WARN:oejs.Holder:main: 
    java.lang.ClassNotFoundException: basic1.servlet

What went wrong?  First, check war/WEB-INF/web.xml to make sure we've
told the servlet container to fetch the corrct servlet.  You should
find something like:

    <servlet>
        <display-name>basic1</display-name>
        <servlet-name>test</servlet-name>
    	<servlet-class>basic1.servlet</servlet-class>
    </servlet>
    <servlet-mapping>
	<servlet-name>test</servlet-name>
    	<url-pattern>/test/*</url-pattern>
    </servlet-mapping>

That looks correct; now double-check that we indeed compiled
`basic1.servlet`.  That is, make sure our source tree is correct - it
should have /basic1/src/basic1/servlet.clj, and servlet.clj should
start by declaring the namespace that matches the directory layout:

     (ns basic1.servlet ...

In other words, ns "basic1.servlet" must be in servlet.clj, inside
directory basic1.  This is a Clojure requirement.

Then let's check the jarfile:

    basic1 $ tar tf war/WEB-INF/lib/basic1-0.1.0-SNAPSHOT.jar
    META-INF/MANIFEST.MF
    META-INF/maven/basic1/basic1/pom.xml
    META-INF/maven/basic1/basic1/pom.properties
    META-INF/leiningen/basic1/basic1/project.clj
    project.clj
    META-INF/leiningen/basic1/basic1/README.md

No wonder ClassNotFound!  `$ lein jar` created a jarfile, but didn't put
any code in it.  Why not?  Because "lein jar" does not compile
anything; it just jars up class files (and some other stuff like the
META-INF files shown).

This would not be a problem if we were doing pure Clojure work here,
since the Clojure runtime will look for .clj files as well as class
files.  But we're using a standard Servlet container, which doesn't
know anything about Clojure.  When it receives a request for a URL
(`/test/foo` in this case), it looks up the URL in the web.xml file to
discover which servlet it is mapped to.  Then it searches the
classpath for the servlet, which, of course, must be a class file -
the container is only interested in Java byte code.

### gen-class and aot compilation

To fix this, we just need to compile our servlet.  The way to do this
with Leiningen is `$ lein compile`.  To tell leiningen where the
compiled byte code should go, we use the :compile-path option in our
project file:

    :compile-path "war/WEB-INF/classes"

(Leiningen assumes Clojure source code is in the `src` subdirectory;
to tell it otherwise, use :source-paths.  To see a complete list of
options run `$ lein help sample` or go to
https://github.com/technomancy/leiningen/blob/stable/sample.project.clj)

If you run `$ lein compile` as things stand, nothing will happen.
That's because Leiningen will only compile what is listed using the
`:aot` option in project.clj.  So uncomment what's there, yielding

    :aot [basic1.servlet]

and run `$ lein compile` again.  This time you should see the class
files in war/WEB-INF/classes:

    basic1 $ ls -l war/WEB-INF/classes/basic1/

    -rw-r--r-- <uid> 1010 Aug 24 11:29 servlet$_service.class
    -rw-r--r-- <uid> 1325 Aug 24 11:29 servlet$fn__16.class
    -rw-r--r-- <uid> 1878 Aug 24 11:29 servlet$loading__4910__auto__.class
    -rw-r--r-- <uid> 6138 Aug 24 11:29 servlet.class
    -rw-r--r-- <uid> 3272 Aug 24 11:29 servlet__init.class

This gives us a clue as to what `gen-class` does.  According to the
documentation
(http://clojure.github.io/clojure/clojure.core-api.html#clojure.core/gen-class),
`gen-class` (`:gen-class` is the `ns` option corresponding to the
`gen-class` macro) generates bytecode for the class \- in this case,
the one corresponding to the namespace of our file, i.e. basic1.servlet.
But the critical clause is:

    The gen-class construct contains no implementation, as the
    implementation will be dynamically sought by the generated class in
    functions in an implementing Clojure namespace.

In other words, the generated byte code will be a stub implementation
(of HttpServlet, since we used the :extends option).  Here, that means
servlet.class in the basic1 subdirectory of classes.  Now when the
container searches for basic1.servlet it will look in the basic1
subdirectory of the classes dir, searching for servlet.class.  On
finding it, it will load it and commence the servlet life-cycle, first
calling the `init` method and then the `service` method.

Since we did not implement the `init` method (we would have to call it
`-init`, since Clojure's convention is to prefix Java method names
with \- when using `gen-class` unless `:prefix` is used), the call to
`init` will go to the superclass.  We did implement `-service`, though,
so when the container calls the `service` method it will be handled by
the stub code generated by `gen-class`.  That code is responsible for
passing control to code that implements the Clojure function
(i.e. `-service`) defined in our source code.

Our implementation of `-service` is a pure Clojure implementation - a
function in a namespace.  For this to work, it must be converted into
a Java object.  So compilation of this code generates
`servlet$_service.class` (the \- gets converted to _), which is a
distinct java object that, when constructed, executes the
implementation code.

It is not evident from this example, but the critical element in all
this is that the `gen-class` generated stub code hooks into the Clojure
runtime.  When it comes time for the `service` stub to pass control to
the implementation, it is the Clojure runtime rather than the Java
runtime that will control the searching and loading.  And since our
`-service` implementation is in the aot-compiled namespace, it will look
for the appropriate class file.  As we will see in exercise ex1b, the
`:impl-ns` allows us to put our implementation in a different namespace;
the result is that Clojure will search for either a .clj file or a
.class file; if it finds the former, it will load and evaluate it.

Exercise: try deleting the .class files one at a time, and see what
kind of errors arise when you try to run the servlet.

Exercise: try running with only the .class files, without the jar
file.  Then `$ lein jar` again and inspect the jar file; it should
contain .class files.  Try running with both, then delete the
war/WEB-INF/classes directory and run with only the jarfile.

**DON'T FORGET** Our servlet does not send an HTML response, it only
  writes to stdout.  So you won't see any result in the browser; you
  have to look in `jetty.err.log` to see the output.

The point of this trivial little exercise is to show how `gen-class`,
aot compilation, lein's jar task, and the servlet container work
together.

(The documentation on aot compilation and classes is at
http://clojure.org/compilation.)

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
