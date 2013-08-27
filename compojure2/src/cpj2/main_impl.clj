(ns cpj2.main-impl
  (:use compojure.core)
  (:require [compojure.route :as route]))

;; CAUTION: these routes must match up with the URL pattern specified in
;; web.xml for this servlet.
(defroutes theRouter
  (GET "/main/hello" [] "<h1>Hello World from cpj2.main servlet!</h1>")
  (GET "/main/goodbye" [] "<h1>Sayonara World from cpj2.main servlet!</h1>")
  (route/not-found "<h1>Page not found</h1>"))

