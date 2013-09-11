(ns migae-tictactoe.user-servlet
    (:gen-class :extends javax.servlet.http.HttpServlet)
    (:require [migae-tictactoe.user-impl :as impl]
              [ring.util.servlet :as ring]))

(defn -service
  [this rqst resp]
    (let [request-map  (ring/build-request-map rqst)
          response-map (impl/user-handler request-map)]
    (when response-map
      (ring/update-servlet-response resp response-map))))
