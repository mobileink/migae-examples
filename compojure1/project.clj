(defproject cpj1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:all"]
  :repl-options {:port 4005}
  :aot [cpj1.theServlet cpj1.filter]
  :compile-path "war/WEB-INF/classes"
  :resource-paths ["src"] ;; this puts clj source in the jarfile so non-aot methods available
  :target-path "war/WEB-INF/lib"
  :keep-non-project-classes false
  :omit-source true ;; default
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [commons-codec "1.7"]
                 [ring/ring-servlet "1.2.0"]]
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
