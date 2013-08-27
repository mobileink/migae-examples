(ns basic4.impl
  (:use basic4.test)
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

(defn basic4-objinit
  [& rest]
  (println "basic4.impl basic4-objinit invoked"))

(defn basic4-objpostinit
  [obj & rest]
  (println "basic4.impl basic4-objpostinit invoked"))

(defn basic4-init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "basic4.impl basic4-init method implementation invoked"))

;; (defn basic4-service
;;   [^HttpServlet servlet
;;    ^HttpServletRequest rqst
;;    ^HttpServletResponse resp]
;;   (println "basic4.impl basic4-service method implementation invoked"))

;(remove-ns 'basic4.test)

(defn foo [] (println "foo"))

(defn basic4-doGet
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  ;; PrintWriter out = response.getWriter();
  ;; out.println("Hello World");
  (do
;    (foo)
    (println "basic4.impl basic4-doGet method implementation invoked")
    (.println (.getWriter resp) "Hello World - the badder way!!!")))

(defn basic4-destroy
  []
  (println "basic4.impl basic4-destroy method implementation invoked"))

;; Expected console output when localhost:8080/request/foo accessed:

;; basic4.impl basic4-objinit invoked
;; basic4.impl basic4-objpostinit invoked
;; basic4.impl basic4-init method implementation invoked
;; basic4.impl basic4-service method implementation invoked


