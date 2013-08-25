(defproject gae1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:all"]
  :repl-options {:port 4005}
  :aot [gae1.main gae1.user gae1.filter]
  ;; :aot [#"gae1*"]
  :compile-path "war/WEB-INF/classes"
  :resource-paths ["src"] ;; this puts clj source in the jarfile so non-aot methods available
  :target-path "war/WEB-INF/lib"
  ;; CRITICAL!  Use :jar-exclusions to make sure the implementation code
  ;; is not byte compiled into the jar.
  :jar-exclusions [#".*impl*"]
  ;; :keep-non-project-classes false
  :omit-source true ;; default
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-servlet "1.2.0"]]
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
