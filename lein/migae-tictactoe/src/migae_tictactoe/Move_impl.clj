(ns migae-tictactoe.Move-impl
  (:use compojure.core
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]])
  (:require [compojure.route :as route]))

(defroutes Move-routes
  (GET "/move" []
    (str (format "<h1>Ohayo from migae-tictactoe.Move-impl servlet path /move!</h1>")
         "\n\n<a href='/'>home</a>"))

  (route/not-found "<h1>Page not found</h1>"))

(def Move-handler
  (-> #'Move-routes
      wrap-params
      wrap-file-info
      ))

