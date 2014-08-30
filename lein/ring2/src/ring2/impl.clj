(ns ring2.impl
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

;; A basic ring handler:
(defn service [request]
  (do
    (println "ring2.impl service function invoked")
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body "Hello World from dynamic ring service handler ring2.impl$service!  Edit my source code and watch me change!"}))

;; (defn doGet [request]
;;   (do
;;     (println "ring2.impl doGet function invoked")
;;     {:status 200
;;      :headers {"Content-Type" "text/html"}
;;      :body "Hello World from dynamic ring doGet handler ring2.impl$doGet..."}))

