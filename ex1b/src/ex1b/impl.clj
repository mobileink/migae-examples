(ns ex1b.impl
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

(defn request-objinit
  [& rest]
  (println "ex1b.impl objinit invoked"))

(defn request-objpostinit
  [obj & rest]
  (println "ex1b.impl objpostinit invoked"))

(defn request-init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "ex1b.impl request-init method implementation invoked"))

(defn request-service
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  (println "ex1b.impl request-service method implementation invoked"))

(defn request-destroy
  []
  (println "ex1b.impl request-destroy method implementation invoked"))

(println "ex1b.impl:  why am I being evaluated??")

;; Expected console output when localhost:8080/request/foo accessed:

;; ex1b.impl:  why am I being evaluated??
;; ex1b.impl objinit invoked
;; ex1b.impl objpostinit invoked
;; ex1b.impl request-init method implementation invoked
;; ex1b.impl request-service method implementation invoked

;; (assuming use of jetty-runner.jar)
