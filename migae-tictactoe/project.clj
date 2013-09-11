(defproject migae-tictactoe "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :min-lein-version "2.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "http://example.com/FIXME"

  :migae {:sdk "/usr/local/java/appengine"
          :devlog "devserver.log" ;; for 'lein migae run' stdout/stderr
          :id ""
          ;; GAE version ID
          ;; using '-' prefix on version nbr forces user to customize
          :version  {:dev "-0-1-0"
                     :test "-0-1-0"
                     :prod "-0-1-0"}
          :filters [{:filter "reload_filter"
                     :ns "migae-tictactoe.reload-filter"
                     :class "migae_tictactoe.reload_filter"}]
          :servlets [{:servlet "TicTacToe",
                      :src "migae_tictactoe/TicTacToeServlet.clj"
                      :ns "migae-tictactoe.TicTacToeServlet",
                      :class "migae_tictactoe.TicTacToeServlet",
                      :filters [{:filter "reload_filter"}]
                      :services [{:service "request" :url-pattern  "/*"}]}
                     {:servlet "Opened",
                      :src "migae_tictactoe/OpenedServlet.clj"
                      :ns "migae-tictactoe.OpenedServlet",
                      :class "migae_tictactoe.OpenedServlet",
                      :filters [{:filter "reload_filter"}]
                      :services [{:service "prefs" :url-pattern  "/opened"}]}
                     {:servlet "Move",
                      :src "migae_tictactoe/MoveServlet.clj"
                      :ns "migae-tictactoe.MoveServlet",
                      :class "migae_tictactoe.MoveServlet",
                      :filters [{:filter "reload_filter"}]
                      :services [{:service "prefs" :url-pattern  "/move"}]}
                     {:servlet "user",
                      :src "migae_tictactoe/user_servlet.clj"
                      :ns "migae-tictactoe.user-servlet",
                      :class "migae_tictactoe.user_servlet",
                      :filters [{:filter "reload_filter"}]
                      :services [{:service "login" :url-pattern  "/_ah/login_required"}]}
                     ]
          :security [{:url-pattern "/*"
                     :web-resource-name "player"
                     :role-name "*"
                     }]
          :war "war"
          :display-name "migae-tictactoe"
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
  :aot [#".*[Ss]ervlet" #".*filter"]
  :resource-paths ["src/"]
  :web-inf "war/WEB-INF"
  :compile-path "war/WEB-INF/classes"
  :target-path "war/WEB-INF/lib"
  :libdir-path "war/WEB-INF/lib"
  :jar-exclusions [#".*impl*" #"^WEB-INF/appengine-generated.*$"]
  :clean-targets [:web-inf]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.5"]
                 [ring/ring-servlet "1.2.0"]
                 [hiccup "1.0.4"]
                 ;; [migae/migae-env "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-blobstore "0.1.0-SNAPSHOT"]
                 [migae/migae-channel "0.1.0-SNAPSHOT"]
                 [migae/migae-datastore "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-images "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-mail "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-memcache "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-taskqueues "0.1.0-SNAPSHOT"]
                 ;; [migae/migae-urlfetch "0.1.0-SNAPSHOT"]
                 [migae/migae-user "0.1.0-SNAPSHOT"]
                 [org.clojure/tools.logging "0.2.3"]]
  :profiles {:dev {:plugins [[lein-migae "0.1.6-SNAPSHOT"]
                             [lein-libdir "0.1.1"]]}})
