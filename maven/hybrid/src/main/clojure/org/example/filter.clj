(ns org.example.filter
  (:import (javax.servlet Filter FilterChain FilterConfig
                          ServletRequest ServletResponse))
  (:gen-class :implements [javax.servlet.Filter]))

(defn -init [^Filter this ^FilterConfig cfg])

(defn -destroy [^Filter this])

(defn -doFilter
  [^Filter this
   ^ServletRequest rqst
   ^ServletResponse resp
   ^FilterChain chain]
  (do
    (require 'org.example.request-impl 'org.example.user-impl :reload);; :verbose)
    (.doFilter chain rqst resp)))
