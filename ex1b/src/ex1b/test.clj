(ns ex1b.test
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet
              :prefix "test-"
              :impl-ns ex1b.impl
              :init "objinit"
              :post-init "objpostinit"))

