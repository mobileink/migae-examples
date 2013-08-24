(ns ex1c.test
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet
              :prefix "ex1c-"
              :impl-ns ex1c.impl
              :init "objinit"
              :post-init "objpostinit"))

