# basic3

Basic Clojure servlet using gen-class with :impl-ns

##

Use of the `:impl-ns` option of `gen-class` allows us to put
implementation code in a separate namespace, which means it need not
be aot-compiled along with the gen-class'd namespace.  Which makes the
code eligible for reload and evaluation under control of the Clojure
runtime.

When you run `$ lein compile` notice that the implementation
`basic3/impl.clj` file does not get compiled, since we said `:aot
[basic3.test]` in project.clj.  (Remember, pathnames like
`basic3/impl.clj` must correspond to the namespace in impl.clj
(`basic3.impl`) after swapping / for . and \- for _ and dropping the
.clj suffix.)  The gen-class'd namespace *must* be aot-compile, but by
using the `:impl-ns` option we can effectively tell Clojure: "compile
the gen-class'd namespace, but arrange things to that calls to its
methods get forwarded to this other namespace, which will not be
compiled."  The result is that, at runtime, when the servlet container
invokes the servlet methods, the calls will be forwarded - via the
Clojure runtime - to objects that do not exist as byte code.  When
Clojure sees this it will search for source code, which it will load
and evaluate to produce byte code dynamically.

This works just great under jetty-runner (migae-jetty.sh), but it
throws a spanner into the works if you try to run it under
dev_appserver.  (Remember to copy the Clojure runtime jar to
war/WEB-INF/lib.)  The Clojure runtime will look for the source code
in impl.clj, which is under our src/ directory - outside of
war/WEB-INF.  That's fine under jetty-runner, but it violates the security constraints of dev_appserver, so we'll get a `java.io.FileNotFoundException: Could not locate basic3/impl__init.class or basic3/impl.clj on classpath:`

The simple way to fix this is to add basic.impl to the :aot option.
Of course, what this means is no more dynamic code evaluation, so we
lose interactive development.  In a later exercise (gae2) we will
demonstrate a wonderfully devious workaround that will restore
interactive development under dev_appserver.

This example returns an HTML response.  But don't forget to check the
log file to see the sequence of actions.  You can use `tail -f
jetty.err.log` as you refresh the browser to see the log dynamically.

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
