(ns gae1.main
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require [gae1.main-impl :as impl]
            [ring.util.servlet :as ring]))

(defn -service
  [this rqst resp]
    (let [request-map  (ring/build-request-map rqst)
    response-map (impl/theRouter request-map)]
    (when response-map
    (ring/update-servlet-response resp response-map))))

