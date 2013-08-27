(ns cpj1.theServlet
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:require [cpj1.impl :as impl]
            [ring.util.servlet :as ring]))

(defn -service
  [^HttpServlet this
   ^HttpServletRequest rqst
   ^HttpServletRequest resp]
  (do
    (println "cpj1.theServlet -service method implementation invoked")
    (let [request-map  (ring/build-request-map rqst)
          response-map (impl/theRouter request-map)]
      (when response-map
        (ring/update-servlet-response resp response-map)))))

