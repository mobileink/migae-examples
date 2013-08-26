(defproject gae2 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:all"]
  :repl-options {:port 4005}
  :aot [gae2.main gae2.user gae2.filter]
  ;; :aot [#"gae2*"]
  :compile-path "war/WEB-INF/classes"
  :resource-paths ["src"] ;; this puts clj source in the jarfile so non-aot methods available
                   ;; "war/WEB-INF/lib/*"
                   ;; "/usr/local/java/appengine/lib/shared/*"]
  ;; :checkout-deps-shares [:resource-paths
  ;;                        ~(fn [p] "/usr/local/java/appengine/lib/shared/*")]
  ;; <fileset dir="${appengine.sdk}/lib">
  ;;     <include name="shared/**/*.jar" />
  :target-path "war/WEB-INF/lib"
  ;; CRITICAL!  Use :jar-exclusions to make sure the implementation code
  ;; is not byte compiled into the jar.
  :jar-exclusions [#".*impl*"]
  ;; :keep-non-project-classes false
  :omit-source true ;; default
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-servlet "1.2.0"]
                 [com.google.appengine/appengine-api-1.0-sdk "1.8.3"]
                 ;; [com.google.appengine/appengine-api-labs "1.8.3"]
                 ;; [com.google.appengine/appengine-api-stubs "1.8.3"]
                 ;; [com.google.appengine/appengine-endpoints "1.8.3"]
                 ;; [com.google.appengine/appengine-local-endpoints "1.8.3"]
                 ;; [com.google.appengine/appengine-jsr107cache "1.8.3"]
                 ;; [com.google.appengine/appengine-testing "1.8.3"]
                 ;; [com.google.appengine/appengine-tools-sdk "1.8.3"]]
                 ]
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
