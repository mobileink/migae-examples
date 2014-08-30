(ns migae.cache-impl
  (:use compojure.core
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]]
        [compojure.route :as route]
        [migae.migae-core :as core]
        [migae.migae-memcache :as mc])
  (:refer-clojure :exclude [contains? get])
  (:import [com.google.appengine.api.memcache MemcacheService MemcacheServiceFactory]))

(defn get-mc-service [] (MemcacheServiceFactory/getMemcacheService))

(defroutes cache-routes
  (GET "/cache/hello" [] "<h1>Hi there Cache from migae.cache servlet!</h1>")

  (GET "/cache/goodbye" [] "<h1>Sayonara from migae.cache servlet!</h1>")

  (GET "/cache/put/:key/:val" [key val]
    (do (println (format "putting cache val %s under key %s" key val))
        (mc/put! key val)
        (format "putting cache val %s under key %s" key val)))

  (GET "/cache/get/:key" [key]
    (do (println (format "getting cache val under key %s" key))
        (let [val (mc/get key)]
          (format "getting cache val under key %s: %s" key val))))

  (GET "/cache/del/:key" [key]
    (do (println (format "deleting cache val under key %s" key))
        (mc/delete! key)
        (format "deleting cache val under key %s" key)))

  (GET "/cache/clear" []
    (do (println (format "clearing cache"))
        (mc/clear-all!)
        (format "clearing cache")))

  (route/not-found "<h1>Cache page not found</h1>"))

(def cache-handler
  (-> #'cache-routes
      wrap-params
      wrap-file-info
      ;; handle-dump
      ))

