(ns gae1.user
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require [gae1.user-impl :as impl]
            [ring.util.servlet :as ring]))

(defn -service
  [this rqst resp]
  (do
    (println "gae1.user -service method implementation invoked")
    (let [request-map  (ring/build-request-map rqst)
          response-map (impl/theRouter request-map)]
      (when response-map
        (ring/update-servlet-response resp response-map)))))

