(ns basic4.test
  (:import (javax.servlet.http HttpServlet
                               HttpServletRequest
                               HttpServletResponse)
           (javax.servlet ServletConfig))
  (:gen-class :extends javax.servlet.http.HttpServlet
              :prefix "basic4-"
              :impl-ns basic4.impl
              :init "objinit"
              :post-init "objpostinit"))

