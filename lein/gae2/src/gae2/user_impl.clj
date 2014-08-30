(ns gae2.user-impl
  (:use compojure.core)
  (:require [compojure.route :as route]))

(defroutes theRouter
  (GET "/user/hello" [] "<h1>Hi there User from gae2.user servlet!</h1>")
  (GET "/user/goodbye" [] "<h1>Sayonara from gae2.user servlet!</h1>")
  (route/not-found "<h1>User page not found</h1>"))

