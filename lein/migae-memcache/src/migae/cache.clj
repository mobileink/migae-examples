(ns migae.cache
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require [migae.cache-impl :as impl]
            [ring.util.servlet :as ring]))

(defn -service
  [this rqst resp]
  (do
    (let [request-map  (ring/build-request-map rqst)
          response-map (impl/cache-handler request-map)]
      (when response-map
        (ring/update-servlet-response resp response-map)))))

