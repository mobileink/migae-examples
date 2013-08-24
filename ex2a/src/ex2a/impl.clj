(ns ex2a.impl
  (:use ex2a.test)
  (:require [ring.util.servlet :as ring])
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

(defn ex2a-objinit
  [& rest]
  (println "ex2a.impl ex2a-objinit invoked"))

(defn ex2a-objpostinit
  [obj & rest]
  (println "ex2a.impl ex2a-objpostinit invoked"))

(defn ex2a-init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "ex2a.impl ex2a-init method implementation invoked"))

;; A basic ring handler:
(defn theHandler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World from ring handler ex2a.impl$theHandler!"})

;; connect ring handler to servlet api:
;; (see ring-jetty-adapter, ring-servlet)
(defn ex2a-service
  [^HttpServlet this
   ^HttpServletRequest rqst
   ^HttpServletRequest resp]
  (let [request-map  (ring/build-request-map rqst)
        response-map (theHandler request-map)]
    (when response-map
      (ring/update-servlet-response resp response-map))))

(defn ex2a-destroy
  []
  (println "ex2a.impl ex2a-destroy method implementation invoked"))

