(ns ex1a.request
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn -init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "ex1a.request init method invoked"))

(defn -service
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  (println "ex1a.request service method invoked"))

(defn -destroy
  []
  (println "ex1a.request destroy method invoked"))

(println "ex1a.request:  why am I being evaluated??")

