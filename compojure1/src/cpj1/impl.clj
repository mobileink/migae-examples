(ns cpj1.impl
  (:use compojure.core)
  (:require [compojure.route :as route]))

;; CAUTION: these routes must match up with the URL pattern specified in
;; web.xml for this servlet.
(defroutes theRouter
  (GET "/test/hello" [] "<h1>Hello World from Compojure!</h1>")
  (GET "/test/goodbye" [] "<h1>Goodbye World from Compojure!</h1>")
  (route/not-found "<h1>Page not found</h1>"))

