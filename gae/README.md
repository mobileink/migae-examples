# gae

First App Engine exercise.

## Introduction

You can serve up your servlet using either jetty-runner.jar or the GAE
dev_server.  The difference is that with dev_server, all jar
dependencies must be in war/WEB-INF/lib, and all application code must
be either jarred up in war/WEB-INF/lib or in war/WEB-INF/classes.

If you put the app code in a jar, you lose interactive development.
You can get interactive development under dev_server, but at a cost:
you must put your source tree in war/WEB-INF/classes, and you must use
`:jar-exclusions` (see project.clj) to exclude the dynamic source from
your jar file.  Obviously this is not advisable, since the classes dir
and contents are dynamically created by `lein compile`.  So you would
not want to do this as a matter of course, but in exploring you might
as well give it a try - edit project.clj appropriately, copy
src/gae/user_impl.clj to war/WEB-INF/classes/gae, fire up
dev_appserver, edit war/WEB-INF/classes/gae/user_impl.clj, and refresh
the browser.  This just demonstrates that the Clojure runtime's code
loading and evaluation functionality continues to operate in GAE.

## Tasks

To run your servlet without interactive dev capabilities:

 1.  edit :aot in project.clj to aot compile everything.

 2.  copy dependency jars from ~/.m2/repository to war/WEB-INF/lib
 (later we will automate this)

 3.  

## A Note on Dependencies

You don't need to copy everything listed in :dependencies.  You will
always need the Clojure jar, since that contains the runtime.
Leiningen will arrange things so that the bits of code your
application relies on will be installed in war/WEB-INF/classes and the
app jarfile.  So, for example, you do not need to copy the compojure
jar into war/WEB-INF/lib, even though [compojure "1.1.5"] is included
in :dependencies.

However, Leiningen functionality depends on a set of naming
conventions.  Dependency names, like [compojure "1.1.5"], will
correspond in a systematic way with paths in ~/.m2/repository and with
classnames.  So when it compiles something that uses compojure,
Leiningen can find the jar in the repository, extract the source code
(try using `jar tf` to inspect the contents of the compojure jarfile
in the repo), compile it, and put the result in
war/WEB-INF/classes/compojure.  Then `lein jar` will put those class
files in your application jar.  The (only?) exception to this is the
Clojure runtime itself.

So long as maven packages follow the naming conventions, all will be
copacetic.  But sometimes that is not the case.  This project has an
example.  It indirectly uses ring-codec, which in turn depends on
org.apache.commons.codec.binary.Base64.  The problem is that that
class object is not stored under
~/.m2/repository/org/apache/commons/codec ... as Leiningen expects, so
it cannot find it.  At runtime, you will get a class not found error.
As it happens, this code is packaged in
`~/.m2/repository/commons-codec/commons-codec/1.7/commons-codec-1.7.jar`

Now there is probably a way to tell Leiningen to add that path to its
search path for `lein jar`, but I don't know what it is.  So for now,
the way to fix this sort of problem is simply to copy the needed jar
to war/WEB-INF/lib.

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
