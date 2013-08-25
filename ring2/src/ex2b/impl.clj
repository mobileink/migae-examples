(ns ex2b.impl
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

;; A basic ring handler:
(defn service [request]
  (do
    (println "ex2b.impl service function invoked")
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body "Hello World from dynamic ring service handler ex2b.impl$service!"}))

;; (defn doGet [request]
;;   (do
;;     (println "ex2b.impl doGet function invoked")
;;     {:status 200
;;      :headers {"Content-Type" "text/html"}
;;      :body "Hello World from dynamic ring doGet handler ex2b.impl$doGet..."}))

