(defproject basic3 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:all"]
  :aot [basic3.test]
  :compile-path "war/WEB-INF/classes"
  :resource-paths ["src"] ;; this puts clj source in the jarfile so non-aot methods available
  :target-path "war/WEB-INF/lib"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [javax.servlet/servlet-api "2.5"]]
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
