(ns migae-tictactoe.MoveServlet
    (:gen-class :extends javax.servlet.http.HttpServlet)
    (:require [migae-tictactoe.Move-impl :as impl]
            [ring.util.servlet :as ring]))

(defn -service
  [this rqst resp]
    (let [request-map  (ring/build-request-map rqst)
          response-map (impl/Move-handler request-map)]
    (when response-map
      (ring/update-servlet-response resp response-map))))
