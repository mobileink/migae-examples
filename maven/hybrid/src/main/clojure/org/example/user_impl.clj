(ns org.example.user-impl
  (:use compojure.core
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]])
  (:require [compojure.route :as route]))

(defroutes user-routes
  (GET "/user/hello/:you" [you]
    (str (format "<h1>Ohayo %s from org.example.user servlet!</h1>" you)
         "\n\n<a href='/'>home</a>"))
  (GET "/user/goodbye/:you" [you]
    (str (format "<h1>Sayonara %s from org.example.user servlet!</h1>" you)
         "\n\n<a href='/'>home</a>"))
  (route/not-found "<h1>Page not found</h1>"))

(def user-handler
  (-> #'user-routes
      wrap-params
      wrap-file-info
      ))

