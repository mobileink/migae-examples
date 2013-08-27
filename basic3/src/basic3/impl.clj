(ns basic3.impl
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

(defn test-objinit
  [& rest]
  (println "basic3.impl test-objinit invoked"))

(defn test-objpostinit
  [obj & rest]
  (println "basic3.impl test-objpostinit invoked"))

(defn test-init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "basic3.impl test-init method implementation invoked"))

;; The bad way to do it - using the Java API instead of ring:
(defn test-doGet
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  ;; PrintWriter out = response.getWriter();
  ;; out.println("Hello World");
  (do
    (println "basic3.impl test-doGet method implementation invoked")
    (.println (.getWriter resp) "Hello World - the bad way!")))

(defn test-destroy
  []
  (println "basic3.impl test-destroy method implementation invoked"))

;; Expected console output when localhost:8080/request/foo accessed:

;; basic3.impl test-objinit invoked
;; basic3.impl test-objpostinit invoked
;; basic3.impl test-init method implementation invoked
;; basic3.impl test-service method implementation invoked



