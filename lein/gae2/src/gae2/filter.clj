(ns gae2.filter
  (:import (javax.servlet Filter FilterChain FilterConfig
                          ServletRequest ServletResponse))
  (:gen-class :implements [javax.servlet.Filter]))

(defn -init [^Filter this ^FilterConfig cfg])

(defn -doFilter
  [^Filter this
   ^ServletRequest rqst
   ^ServletResponse resp
   ^FilterChain chain]
  (do
    ;; TODO: use https://github.com/weavejester/ns-tracker
    (require 'gae2.main-impl 'gae2.user-impl :reload) ;; :verbose)
    (.doFilter chain rqst resp)))
