(ns gae3.main-impl
  (:use compojure.core)
  (:require [compojure.route :as route]))

(defroutes theRouter
  (GET "/hello" [] "<h1>Hello World from gae3.main servlet!</h1>")
  (GET "/goodbye" [] "<h1>Goodbye World! from gae3.main servlet!</h1>")
  (route/not-found "<h1>Main page not found</h1>"))

