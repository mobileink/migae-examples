(defproject migae "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url http://example.com/FIXME

;; UNCOMMENT to disable security checks:
;;  :java-source-paths ["src/main/java"]
  :javac-options ["-nowarn" "-target" "1.7" "-source" "1.7" "-Xlint:-options"]
  :jvm-opts ["-javaagent:/usr/local/java/appengine/lib/agent/appengine-agent.jar"
             "-Xbootclasspath/p:war/WEB-INF/lib/appengine-dev-jdk-overrides.jar"
             "-D--startOnFirstThread" ;; if os x
             "-Ddatastore.auto_id_allocation_policy=scattered"
             "-Dappengine.sdk.root=/usr/local/java/appengine"
             "-D--property=kickstart.user.dir=migae"
             "-D--enable_all_permissions=true"
             "-Djava.awt.headless=true"]

  :repl-options {:port 4005
                 :init (do
(import 'com.google.appengine.tools.development.DevAppServerMain)
(defn jetty []
  (do (println "launching migae dev server")))
(defn gserver []
  (do (println "launching GAE DevAppServer")
      (DevAppServerMain/main
       (into-array String
                   [;;"--address=localhost"
                    ;;"--port=8082"
                    "--sdk_root=war/WEB-INF/sdk"
                    "--disable_update_check"
                    "--property=kickstart.user.dir=migae"
                    "war"])))))
}
  :gae-sdk "/usr/local/java/appengine"
  :gae-app {:id "migae-01"
            ;; using '-' prefix on version nbr forces user to customize
            :version  {:dev "-0-1-0"
                       :test "-0-1-0"
                       :prod "-0-1-0"}
            :servlets [{:name "migae", :class "cache",
                       :services [{:svcname "cache" :url-pattern  "/cache/*"}
                                  ]}
                       {:name "migae", :class "user",
                       :services [{:svcname "user" :url-pattern  "/user/*"}
                                  {:svcname "login" :url-pattern  "/_ah/login_required"}
                                  ]}
                       ]
            :war "war"
            :display-name "migae"
            :welcome "index.html"
            :threads true,
            :sessions true,
            :java-logging "logging.properties",
            ;; static-files: html, css, js, etc.
            :statics {:src "src/main/public"
                      :dest ""
                      :include {:pattern "public/**"
                                ;; :expire "5d"
                                }
                      ;; :exclude {:pattern "foo/**"}
                      }
            ;; resources: img, etc. - use lein default
            :resources {:src "src/main/resource"
                        :dest ""
                        :include {:pattern "public/**"
                                  ;; :expire "5d"
                                  }
                        ;; :exclude {:pattern "bar/**"}
                        }
            }
  :aot [migae.cache migae.user migae.filter]
  ;; :resource-paths ["src"]
  :compile-path "war/WEB-INF/classes"
  :target-path "war/WEB-INF/lib"
  ;; :keep-non-project-classes false
  ;; :omit-source true ;; default
  :jar-exclusions [#"^WEB-INF/appengine-generated.*$" #".*impl*"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [migae/migae-core "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-blobstore "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-channel "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-datastore "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-images "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-mail "0.1.0-SNAPSHOT"]
                 [migae/migae-memcache "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-taskqueues "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-urlfetch "0.1.0-SNAPSHOT"]
                 [migae/migae-user "0.1.0-SNAPSHOT"]
                 [ring/ring-servlet "1.2.0"]
                 [ring/ring-devel "1.2.0"]
                 [hiccup "1.0.4"]
                 [commons-codec "1.7"]
                 [org.clojure/tools.logging "0.2.6"]
                 [com.google.appengine/appengine-api-1.0-sdk "1.8.3"]]
;;  :profiles {:dev {:dependencies [[]]}}
  :plugins [[lein-migae "0.1.0-SNAPSHOT"]])
