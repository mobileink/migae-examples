(ns ex1b.request
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet
              :prefix "request-"
              :impl-ns ex1b.impl
              :init "objinit"
              :post-init "objpostinit"))

(println "ex1b.request:  why am I being evaluated??")

