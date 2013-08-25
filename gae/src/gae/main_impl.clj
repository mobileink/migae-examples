(ns gae.main-impl
  (:use compojure.core)
  (:require [compojure.route :as route]))

;; CAUTION: these routes must match up with the URL pattern specified in
;; web.xml for this servlet.
(defroutes theRouter
  (GET "/hello" [] "<h1>Hello World from gae.main servlet!</h1>")
  (GET "/goodbye" [] "<h1>Sayonara World from gae.main servlet!</h1>")
  (route/not-found "<h1>Main page not found</h1>"))

