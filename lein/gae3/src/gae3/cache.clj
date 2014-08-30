(ns gae3.cache
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require [gae3.cache-impl :as impl]
            [ring.util.servlet :as ring]))

(defn -service
  [this rqst resp]
  (do
    (let [request-map  (ring/build-request-map rqst)
          response-map (impl/theRouter request-map)]
      (when response-map
        (ring/update-servlet-response resp response-map)))))

