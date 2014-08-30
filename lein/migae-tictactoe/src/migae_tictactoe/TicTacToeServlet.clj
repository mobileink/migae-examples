(ns migae-tictactoe.TicTacToeServlet
    (:gen-class :extends javax.servlet.http.HttpServlet)
    (:require [migae-tictactoe.TicTacToe-impl :as impl]
            [ring.util.servlet :as ring]))

(defn -service
  [this rqst resp]
    (let [request-map  (ring/build-request-map rqst)
          response-map (impl/TicTacToe-handler request-map)]
    (when response-map
      (ring/update-servlet-response resp response-map))))
