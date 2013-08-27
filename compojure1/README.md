# compojure1

Basic compojure example.

Compile using `$ lein compile`, fire up migae-jetty.sh, and go to localhost:8080/

Edit src/impl.clj to change the message and refresh the browser.

# CAVEAT

Compojure uses Ring (more precisely, `ring.util.codec`) which uses
`org.apache.commons.codec.binary.Base64`.  Unfortunately, that object
is not stored in the maven repository under the naming convention
Leiningen expects (i.e. org/apache/..etc).  Instead it is found under
`commons-codec`.  So if you try to run with only ring in your
:dependencies you'll get an exception `Caused by:
java.lang.ClassNotFoundException:
org.apache.commons.codec.binary.Base64`.

Adding `[commons-codec "1.7"]` to your :dependencies does not seem to
help; Leiningen will still look for it under org/apache/... whereas it
is stored under commons-codec...  At least that's what it looks like
to me.

So the only way to make it go is to copy the commons jar to war/WEB-INF/lib:

    $ cp ~/.m2/repository/commons-codec/commons-codec/1.7/commons-codec-1.7.jar war/WEB-INF/lib/


## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
