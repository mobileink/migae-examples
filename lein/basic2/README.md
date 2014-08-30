# basic2

A slightly more elaborate Clojure servlet.

##

Try running this under `../migae-jetty.sh` out of the box, after running `lein jar`.  It should run with no problem.

Then try it with `dev_appserver`.  You should see on the console something like 
```
WARNING: Error for /test/foo
java.lang.NoClassDefFoundError: clojure/lang/IFn
```

That's because we have not installed the Clojure runtime.  If you look
at the code in migae-jetty.sh, you'll see we reference the Clojure
runtime.  Jetty allows this, but dev_appserver does not, for security
reaons.  The GAE server is designed to run code in a secure "sandbox",
so there is no way to tell it to look for code outside of war/WEB-INF.

So to make this example run under dev_appserver, copy the Clojure jar
to war/WEB-INF/lib.

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
