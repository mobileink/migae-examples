(ns ex2a.test
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:require [ring.util.servlet :as ring])
  (:gen-class :extends javax.servlet.http.HttpServlet
              :impl-ns "ex2a.impl"
              :prefix "ex2a-"))
