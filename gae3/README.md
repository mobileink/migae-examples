# The Ghastly dev_appserver REPL Hack

This exercise demonstrates a devious hack by means of which we get
repl-like interactive development using the GAE dev_appserver.

## Instructions

To use GAE services, all required jarfiles must be in war/WEB-INF/lib.
Following the example of the build.xml files that come with the demos
in the SDK, that means everything in ${sdk}/lib/user and its
subdirectories.  So step one is to copy those files by hand.  A
leiningen plugin task that does this automatically is in the works.

The Clojure runtime (and any other dependencies) must also be
installed in war/WEB-INF/lib.

## The hack

In a nutshell: gen-class the servlet, implement the app logic in a
separate namespace so it is eligible for reload, install a Servlet
Filter to reload the Clojure code on browser refresh, and finally -
here's the real hack - arrange things so that your edited code gets
copied to war/WEB-INF/classes.  That way your source stays put in the
src/ tree, but a copy of it becomes visible to the Clojure runtime
within the dev_appserver sandbox.

There are two simple ways to do this.  So simple they hardly even
qualify as hacks.  Once is to just use brute force `cp`; that's
implemented in `repl.sh`.  The other is to have your editor's save
command trigger the copy; that's what `migae-save-buffer.el` does, for
emacs.

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
