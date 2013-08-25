(ns ex1b.impl
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig)))

(defn test-objinit
  [& rest]
  (println "ex1b.impl test-objinit invoked"))

(defn test-objpostinit
  [obj & rest]
  (println "ex1b.impl test-objpostinit invoked"))

(defn test-init
  [^HttpServlet servlet
   ^ServletConfig cfg]
  (println "ex1b.impl test-init method implementation invoked"))

;; The bad way to do it - using the Java API instead of ring:
(defn test-doGet
  [^HttpServlet servlet
   ^HttpServletRequest rqst
   ^HttpServletResponse resp]
  ;; PrintWriter out = response.getWriter();
  ;; out.println("Hello World");
  (do
    (println "ex1b.impl test-doGet method implementation invoked")
    (.println (.getWriter resp) "Hello World - the bad way!")))

(defn test-destroy
  []
  (println "ex1b.impl test-destroy method implementation invoked"))

;; Expected console output when localhost:8080/request/foo accessed:

;; ex1b.impl test-objinit invoked
;; ex1b.impl test-objpostinit invoked
;; ex1b.impl test-init method implementation invoked
;; ex1b.impl test-service method implementation invoked

;; (assuming use of jetty-runner.jar)

