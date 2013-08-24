# Experiment ex0a

gen-class magic, demystified

## Usage

Start out by using the Leiningen "jar" task:

    ex0a $ lein jar

The result should be a jarfile in war/WEB-INF/lib; that's because our
project.clj contains this clause:

	    :target-path "war/WEB-INF/lib"

Verify:

    ex0a $ ls -l war/WEB-INF/lib
    -rw-r--r--  1 <uid>  <gid>  2986 Aug 24 08:55 ex0a-0.1.0-SNAPSHOT.jar
    drwxr-xr-x  3 <uid>  <gid>   102 Aug 24 08:49 stale

You can ignore the "stale" directory; it has something to do with how
Leiningen works.

Now let's test the servlet.  If you've install lein-migae, do:

    ex0a $ lein migae jetty

If not, do:

    ex0a $ ./migae-jetty.sh start

Use ps to verify that it's running and inspect the error log it
creates to make sure there are no errors.

(You can stop the server with migae-jetty.sh stop or lein migae stop.)

## Options

FIXME: listing of options this app accepts.

## Examples

...

### Bugs

...

### Any Other Sections
### That You Think
### Might be Useful

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
