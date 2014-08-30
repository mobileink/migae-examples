(defproject cpj2 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:all"]
  :aot [cpj2.main cpj2.user cpj2.filter]
  :compile-path "war/WEB-INF/classes"
  :resource-paths ["src"]
  :target-path "war/WEB-INF/lib"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-servlet "1.2.0"]]
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
