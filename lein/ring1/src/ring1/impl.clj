(ns ring1.impl
  (:use ring1.test)
  (:require [ring.util.servlet :as ring])
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

(defn ring1-objinit
  [& rest]
  (println "ring1.impl ring1-objinit invoked"))

(defn ring1-objpostinit
  [obj & rest]
  (println "ring1.impl ring1-objpostinit invoked"))

(defn ring1-init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "ring1.impl ring1-init method implementation invoked"))

;; A basic ring handler:
(defn theHandler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World from ring handler ring1.impl$theHandler!"})

;; connect ring handler to servlet api:
;; (see ring-jetty-adapter, ring-servlet)
(defn ring1-service
  [^HttpServlet this
   ^HttpServletRequest rqst
   ^HttpServletRequest resp]
  (let [request-map  (ring/build-request-map rqst)
        response-map (theHandler request-map)]
    (when response-map
      (ring/update-servlet-response resp response-map))))

(defn ring1-destroy
  []
  (println "ring1.impl ring1-destroy method implementation invoked"))

