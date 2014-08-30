(ns gae3.filter
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
    (require 'gae3.main-impl 'gae3.cache-impl :reload) ;; :verbose)
    (.doFilter chain rqst resp)))
