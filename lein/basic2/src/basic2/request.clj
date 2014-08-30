(ns basic2.request
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn -init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "basic2.request init method invoked"))

(defn -service
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  (println "basic2.request service method invoked"))

(defn -destroy
  []
  (println "basic2.request destroy method invoked"))

