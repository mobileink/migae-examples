migae-examples
==============

Examples of java servlet programming in Clojure.  The designed order is:

 1. basic

  a. basic0 - plain Java servlet, no Clojure, to demo leiningen and servlet testing

  b. basic1 - minimal Clojure servlet using gen-class; explore compile v. jar

  c. basic2 - slightly more elaborate servlet

  d. basic3 - use of :impl-ns to put implementation in loadable namespace

  e. basic4 - use of Servlet Filter to enable dynamic code reloading

So far we have used Java interop facilities to deal with HTTP requests
and responses.  Ring provides a way to do this in pure Clojure.

 2. ring

  a.  ring1 - use minimal ring "glue" code to convert HttpServlet
  requests and responses to and from Clojure maps; ring code in
  implementation file

  b.  ring2 - migrate ring code to servlet (gen-class'd) namespace

Compojure is basically a set of macros that enable us to write Ring
routes in a more conventient form.

 3. compojure

  a. compojure1 - basic use of compojure

  b. compojure2 - webapp with two servlets

Up to now we've run servlets under the GAE dev_appserver environment,
but we have not used an GAE services.  The next set of examples shows
what happens when we try to use GAE runtime services.

 4. gae

  a. gae1 - minimal example, two servlets; use of :jar-exclusions to
  retain interactive development

  b. gae2 - minimal use of GAE service; repl hack: interactive
  development using GAE dev_appserver


## gen-class

The first set of examples (ex1*) demonstrates the use of gen-class to
implement a minimal servlet, configured by the standard web.xml
deployment descriptor.  The examples show what gen-class does, and
illustrate how the use of the :impl-ns option together with the use of
a simple ServletFilter makes interactive development of servlets
possible.  The Jetty server functions as a kind of repl; refreshing a
page triggers reload of Clojure code.

## Ring

The second set of examples (ex2*) demonstrates minimal use of Ring, so
that request handlers can be implemented as pure Clojure functions.

## Compojure

## Testing with Jetty

All of the examples are designed to be used with jetty-runner.jar (or
any other Jetty configuration that works as a standard Servlet
container).  This is a point of departure from standard Ring
development, which uses an embedded intance of Jetty and dynamically
creates Jetty handlers using Clojure's "proxy" function rather than
gen-class.  This is what makes possible the deployment of multiple
servlets and use of web.xml for configuration, in constrast with
standard Ring, which (as far as I can tell) supports only a single
servlet and does not use web.xml.

See http://www.eclipse.org/jetty/documentation/current/jetty-runner.html

## Google App Engine

GAE uses a modified version of Jetty, so to test a web app one must
use the dev_appserver provided in the GAE SDK.  We can use the
techniques noted above to get interactive, repl-like development, but
only at a cost: the source code must be on the classpath, which means
it must be in war/WEB-INF/classes.  This is because the GAE dev
server, unlike standard Jetty, places restrictions on the classpath; it
cannot include directories outside of the webapp.  This is problematic
since that directory is not a good place to put source code, and it
clashes with the conventions of Leiningen.

A further annoyance is that all dependencies must be in
war/WEB-INF/lib.  It is not possible, using GAE dev server, to include
jars from Leiningen's repository (~/.m2/repository) on the classpath;
you have to copy them all into WEB-INF/lib.

Nonetheless, it is possible to develop a GAE webapp using standard
Jetty and the techniques outlined above.  The cost is that standard
Jetty does not disallow the Java packages that are blacklisted by GAE.
But all that means is that you have to test using the GAE dev server
at some point.  That, and before you can deploy you must ensure all
dependencies are copied to WEB-INF/lib.  But for development purposes,
the restrictions imposed by GAE can be side-stepped by using plain
Jetty, so that you get rapid interactive development.  Only when you
are ready for system testing and deployment to production do you need
to use the GAE dev server.