(ns migae-tictactoe.reload-filter
  (:import (javax.servlet Filter FilterChain FilterConfig
                          ServletRequest ServletResponse))
  (:require [clojure.tools.logging :as log :only [debug info]])
  (:gen-class :implements [javax.servlet.Filter]))

(defn -init [^Filter this ^FilterConfig cfg])
(defn -destroy [^Filter this])

(defn -doFilter
  [^Filter this
   ^ServletRequest rqst
   ^ServletResponse resp
   ^FilterChain chain]
  (do
    (log/info "reloading...")
    (require
     'migae-tictactoe.TicTacToe-impl
     'migae-tictactoe.Opened-impl
     'migae-tictactoe.Move-impl
     'migae-tictactoe.user-impl
     ;; :verbose
     :reload)
    (.doFilter chain rqst resp)))
