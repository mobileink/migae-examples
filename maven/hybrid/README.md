hybrid

=============================

Simple App Engine web app server using Clojure (with Compojure) and
Java.

### Testing

```
	$ mvn clean install
	$ mvn appengine:devserver
```

Then load `localhost:8080`

If, when you run the devserver, you get a message like "There is a new
version of the SDK available.", just edit pom.xml to put the latest
version number in the `<appengine.target.version>` element.  Then the
next time you run `mvn`, maven will download and install the specified
version of the SDK.
