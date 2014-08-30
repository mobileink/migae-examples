(ns migae.user-impl
  (:use compojure.core
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]])
  (:require [compojure.route :as route]))

(defroutes user-routes
  (GET "/user/:arg" [arg]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (format "This is the <i>GET user</i> service of the <i><b>migae.user</b></i> servlet.   Now serving <i>%s</i>." arg)})

  (GET "/_ah/login_required" []
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (format "This is the <i>GET login</i> service of the <i><b>migae.user</b></i> servlet. ")})


;;  (route/files "/" {:root "/public/"})

  (route/not-found "Sorry, migae.user page not found\n"))

(def user-handler
  (-> #'user-routes
      wrap-params
      wrap-file-info
      ;; handle-dump
      ))

