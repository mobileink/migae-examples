(ns ex1c.impl
  (:use ex1c.test)
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

(defn ex1c-objinit
  [& rest]
  (println "ex1c.impl ex1c-objinit invoked"))

(defn ex1c-objpostinit
  [obj & rest]
  (println "ex1c.impl ex1c-objpostinit invoked"))

(defn ex1c-init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "ex1c.impl ex1c-init method implementation invoked"))

;; (defn ex1c-service
;;   [^HttpServlet servlet
;;    ^HttpServletRequest rqst
;;    ^HttpServletResponse resp]
;;   (println "ex1c.impl ex1c-service method implementation invoked"))

;(remove-ns 'ex1c.test)

(defn foo [] (println "foo"))

(defn ex1c-doGet
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  ;; PrintWriter out = response.getWriter();
  ;; out.println("Hello World");
  (do
;    (foo)
    (println "ex1c.impl ex1c-doGet method implementation invoked")
    (.println (.getWriter resp) "Hello World - the bad way!!!")))

(defn ex1c-destroy
  []
  (println "ex1c.impl ex1c-destroy method implementation invoked"))

;; Expected console output when localhost:8080/request/foo accessed:

;; ex1c.impl ex1c-objinit invoked
;; ex1c.impl ex1c-objpostinit invoked
;; ex1c.impl ex1c-init method implementation invoked
;; ex1c.impl ex1c-service method implementation invoked

;; (assuming use of jetty-runner.jar)

