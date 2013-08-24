(ns ex2b.filter
  (:import (javax.servlet Filter FilterChain FilterConfig
                          ServletRequest ServletResponse))
  (:gen-class :implements [javax.servlet.Filter]))

(defn -doFilter
  [^Filter this
   ^ServletRequest rqst
   ^ServletResponse resp
   ^FilterChain chain]
  (do
    (println "ex2b.filter doFilter implementation invoked")
    (require 'ex2b.impl :reload :verbose)
    ;; if this is the last filter in the chain, the following
    ;; will invoke the the "target Web resource", i.e. the servlet
    ;; without this doFilter call, the servlet will not be invoked
    ;; Remember, we pass the "this" obj as first arg
    (.doFilter chain rqst resp)))
