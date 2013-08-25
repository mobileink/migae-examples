(defproject ex1b "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:all"]
  :repl-options {:port 4005}
  :aot [ex1b.test]
  :compile-path "war/WEB-INF/classes"
  :resource-paths ["src"] ;; this puts clj source in the jarfile so non-aot methods available
  :target-path "war/WEB-INF/lib"
  :keep-non-project-classes false
  :omit-source true ;; default
  :dependencies [[org.clojure/clojure "1.5.1"]
                 ;; uncomment if you don't already have jetty-runner:
                 ;; [org.eclipse.jetty/jetty-runner "9.0.5.v20130815"]
                 [javax.servlet/servlet-api "2.5"]]
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
