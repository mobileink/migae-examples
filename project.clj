(defproject migae-examples "0.1.2"
  :description "Servlet examples"
  :url "http://github.com/greynolds/migae-examples"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-servlet "1.2.0"]
                 [commons-codec "1.7"]
                 [com.google.appengine/appengine-api-1.0-sdk "1.8.3"]]
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
