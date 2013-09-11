(ns migae-tictactoe.TicTacToe-impl
  (:use compojure.core
        [ring.middleware.params :only [wrap-params]]
        [ring.middleware.file-info :only [wrap-file-info]]
        [ring.util.response :as resp]
        [clojure.tools.logging :as log :only [debug info]])
  (:require [compojure.route :as route]
            [migae.migae-user :as user]
            [hiccup.core :as hiccup]
            [hiccup.element :as helt]
            [hiccup.page :as page]))

(def head
  [:head
    [:title "migae-tictactoe | player"]
    (page/include-css "/css/migae-tictactoe.css")
    (page/include-js "/js/migae-tictactoe.js")])

(defroutes TicTacToe-routes
  (GET "/logout" [arg]
    (let [r (resp/response ;; construct ring response map
             (page/html5   ;; construct html content
              head
              [:body
               [:h1 "Tic Tac Toe"]
               [:ul
                [:li [:a {:href "/_ah/login_required"} "Login"]]
                [:li [:a {:href (user/logout-url :destination "/")} "Logout"]]
                 ]]))
          rr (-> r (resp/content-type "text/html"))]
      (do
;        (log/info (str rr))
        rr)))

  (GET "/:arg" [arg]
    (str (format "<h1>Ohayo from migae-tictactoe.TicTacToe-impl servlet path %s!</h1>"
                 arg)
         "\n\n<a href='/'>home</a>"))

  (route/not-found "<h1>Page not found</h1>"))

(def TicTacToe-handler
  (-> #'TicTacToe-routes
      wrap-params
      wrap-file-info
      ))

