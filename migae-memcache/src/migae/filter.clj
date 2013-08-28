(ns migae.filter
  (:import (javax.servlet Filter FilterChain FilterConfig
                          ServletRequest ServletResponse))
  (:use [migae.migae-core :as core]
        [migae.migae-memcache :as mc])
  (:gen-class :implements [javax.servlet.Filter]))

(def foo migae-type)

(defn -init [^Filter this ^FilterConfig cfg])

(defn -doFilter
  [^Filter this
   ^ServletRequest rqst
   ^ServletResponse resp
   ^FilterChain chain]
  (do
    ;; TODO: use https://github.com/weavejester/ns-tracker
    (require 'migae.user-impl 'migae.cache-impl :reload) ;; :verbose)
    (.doFilter chain rqst resp)))
