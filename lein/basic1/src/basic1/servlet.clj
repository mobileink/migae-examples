(ns basic1.servlet
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse))
  (:gen-class :extends javax.servlet.http.HttpServlet))

(defn -service
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  (println "basic1.servlet service method invoked"))
