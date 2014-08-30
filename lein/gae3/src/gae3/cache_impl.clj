(ns gae3.cache-impl
  (:use compojure.core)
  (:require [compojure.route :as route])
  (:import [com.google.appengine.api.memcache MemcacheService MemcacheServiceFactory]))

(defn get-mc-service [] (MemcacheServiceFactory/getMemcacheService))

(defroutes theRouter
  (GET "/cache/hello" [] "<h1>Hi there Cache from gae3.cache servlet!</h1>")

  (GET "/cache/goodbye" [] "<h1>Sayonara from gae3.cache servlet!</h1>")

  (GET "/cache/put/:key/:val" [key val]
    (do (println (format "putting cache val %s under key %s" key val))
        (.put (get-mc-service) key val)
        (format "putting cache val %s under key %s" key val)))

  (GET "/cache/get/:key" [key]
    (do (println (format "getting cache val under key %s" key))
        (let [val (.get (get-mc-service) key)]
          (format "getting cache val under key %s: %s" key val))))

  (GET "/cache/del/:key" [key]
    (do (println (format "deleting cache val under key %s" key))
        (.delete (get-mc-service) key)
        (format "deleting cache val under key %s" key)))

  (GET "/cache/clear" []
    (do (println (format "clearing cache"))
        (.clearAll (get-mc-service))
        (format "clearing cache")))

  (route/not-found "<h1>Cache page not found</h1>"))

