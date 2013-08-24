(ns ex1.request
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn -init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "ex1.request init method invoked"))

(defn -service
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  (println "ex1.request service method invoked"))

(defn -destroy
  []
  (println "ex1.request destroy method invoked"))

(println "ex1.request:  why am I being evaluated??")

