(ns basic3.test
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet
              :prefix "test-"
              :impl-ns basic3.impl
              :init "objinit"
              :post-init "objpostinit"))

