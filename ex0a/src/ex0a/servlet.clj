(ns ex0a.servlet
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse))
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn -service
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  (println "ex0a.servlet service method invoked"))
