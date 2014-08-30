# basic4

Introduce Servlet Filters as a means of getting dynamic code reloading.

## Servlet Filters

You use web.xml to install the filter.

## Dynamic code reloading and evaluation

See the Servlet Specification for details.  It's pretty clear.

The filter gets control before the call to the servlet's service
method.  So that's when we force a code reload, using Clojure's
`require` function.  For simplicity's sake we just reload everything;
a better implementation would only reload code that has changed.

To explore this run migae-jetty.sh and monitor the error log.  Access
`http://localhost:8080/test/foo`, observe the output, then edit
`impl.clj` to change the output message.  Then refresh the browser and
you should see the changed message almost immediately.  In the log
you'll see messages indicating the sequence of events.

That's under jetty-runner; try it under dev_appserver and it won't
work.  To even make it function under dev_appserver (so far) we have
to aot-compile everything, which means nothing is available for
dynamic evaluation.

Gentle reader, do not despair.  All we need to do is make our Clojure
source code available to dev_appserver, which means putting it on the
classpath.  Since the classpath includes war/WEB-INF/classes, all we
need to do is put our source code there.  In example gae2 we will
demonstrate a simple and dastardly way to do this.

For now, though, we'll stick with jetty-runner, since we just want to
explore how Clojure can be used to implement servlets.  We'll worry
about using GAE services later.


## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
