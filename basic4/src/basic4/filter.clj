(ns basic4.filter
  (:import (javax.servlet Filter FilterChain FilterConfig
                          ServletRequest ServletResponse))
  (:gen-class :implements [javax.servlet.Filter]
              :init finit))

(defn -finit
  [& rest]
  (println "basic4.filter finit implemenation invoked"))

(defn -init
  [^Filter this
   ^FilterConfig cfg]
  (println "basic4.filter init implemenation invoked"))

(defn -doFilter
  [^Filter this
   ^ServletRequest rqst
   ^ServletResponse resp
   ^FilterChain chain]
  (do
    (println "basic4.filter doFilter implementation invoked")
    (require 'basic4.impl :reload :verbose)
    ;; if this is the last filter in the chain, the following
    ;; will invoke the the "target Web resource", i.e. the servlet
    ;; without this doFilter call, the servlet will not be invoked
    ;; Remember, we pass the "this" obj as first arg
    (.doFilter chain rqst resp)))

(defn -destroy
  [^Filter this]
  (println "basic4.filter destroy implementation invoke"))
