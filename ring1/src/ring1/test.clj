(ns ring1.test
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:require [ring.util.servlet :as ring])
  (:gen-class :extends javax.servlet.http.HttpServlet
              :impl-ns "ring1.impl"
              :prefix "ring1-"))
