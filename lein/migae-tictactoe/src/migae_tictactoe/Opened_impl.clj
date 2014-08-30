(ns migae-tictactoe.Opened-impl
  (:use compojure.core
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]])
  (:require [compojure.route :as route]))

(defroutes Opened-routes
  (GET "/opened" []
    (str (format "<h1>Ohayo from migae-tictactoe.Opened-impl servlet path /opened</h1>")
         "\n\n<a href='/'>home</a>"))

  (route/not-found "<h1>Page not found</h1>"))

(def Opened-handler
  (-> #'Opened-routes
      wrap-params
      wrap-file-info
      ))

