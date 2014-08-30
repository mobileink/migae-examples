(ns gae1.user-impl
  (:use compojure.core)
  (:require [compojure.route :as route]))

;; CAUTION: these routes must match up with the URL pattern specified in
;; web.xml for this servlet.
(defroutes theRouter
  (GET "/user/hello" [] "<h1>Hi there User from gae1.user servlet!</h1>")
  (GET "/user/goodbye" [] "<h1>Auf wiederzehen User from gae1.user servlet!</h1>")
  (route/not-found "<h1>User page not found</h1>"))

