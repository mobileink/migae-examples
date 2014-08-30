(ns migae-tictactoe.user-impl
  (:import com.google.apphosting.api.ApiProxy)
  (:use compojure.core
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]]
        [ring.util.response :as resp]
        [clojure.tools.logging :as log :only [debug info]])
  (:require [compojure.route :as route]
            [migae.migae-user :as user]
            [hiccup.core :as hiccup]
            [hiccup.element :as helt]
            [hiccup.page :as page]))

(def head
  [:head
    [:title "migae-test | user"]
    (page/include-css "/css/migae-test.css")
    (page/include-js "/js/migae-test.js")])

(defn environ
  []
  (let [jv (str "Java version: "
                (System/getProperty "java.version"))
        jspecname (str "Java spec name: "
                    (System/getProperty "java.specification.name"))
        jspecvendor (str "Java spec vendor: "
                    (System/getProperty "java.specification.vendor"))
        jspecversion (str "Java spec version: "
                    (System/getProperty "java.specification.version"))
        jvmspecname (str "Java vm spec name: "
                    (System/getProperty "java.vm.specification.name"))
        jvmspecvendor (str "Java vm spec vendor: "
                    (System/getProperty "java.vm.specification.vendor"))
        jvmspecversion (str "Java vm spec version: "
                    (System/getProperty "java.vm.specification.version"))

        loggedin? (str "User is logged in? " (user/user-logged-in?))
        user (str "User: " (if loggedin?
                             (user/current-user)
                             "-"))
        admin? (str "User is admin? "
                    (if (user/user-logged-in?)
                         (user/user-admin?)
                         false))

        userdir (str "User dir: "
                    (System/getProperty "user.dir"))
        instance (str "Instance Id: "
 ;                     (.get
                       (.getAttributes
                        (com.google.apphosting.api.ApiProxy/getCurrentEnvironment)))
;                      "com.google.appengine.instance.id")
        e (str "Environment: "
               (System/getProperty "com.google.appengine.runtime.environment"))
        ;; TODO: use com.google.appengine.api.utils.SystemProperty
        v (str "Runtime Version: "
               (System/getProperty "com.google.appengine.runtime.version"))
        appid (str "App Id: "
                   (System/getProperty "com.google.appengine.application.id"))
        appversion (str "App Version: "
                       (System/getProperty "com.google.appengine.application.version"))]
    (helt/unordered-list [user admin? loggedin?
                          e v appid appversion jv
                          jspecname jspecvendor jspecversion
                          jvmspecname jvmspecvendor jvmspecversion
                          userdir])))

(defroutes user-routes

  (GET "/_ah/login_required" [continue]
    (do
      (log/info "_ah/login_requiured served by user servlet")
      {:status 200
       :headers {"Content-Type" "text/html"}
       :body (format
              "<html><body><p>login: [<a href=\"%s\">gmail</a></body>][<a href=\"%s\">myopenid</a>]</p></body></html>"
              (user/login-federated :destination "/tictactoe" :fedIdentity "gmail.com")
              (user/login-federated :destination "/tictactoe" :fedIdentity "myopenid.com"))
       }))

  (GET "/user/:arg" [arg]
    (str (format "<h1>Ohayo %s from migae-test.user-impl servlet path /user/*!</h1>"
                 arg)
         "\n\n<a href='/'>home</a>"))

  (route/not-found "<h1>user service test - page not found</h1>"))

(def user-handler
  (-> #'user-routes
      wrap-params
      ))

