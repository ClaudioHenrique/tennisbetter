(ns server.routes
  (:require [compojure.core :refer [GET POST defroutes context]]
            [compojure.route :refer [not-found resources]]
            [ring.util.response :refer [resource-response]]
            [server.auth :as auth]))

(defn ^:private four-oh-four-page []
  {:status 404 :body "Ressource not found :("})

(defroutes ^:private api
  (POST "/login" {params :params} (auth/login params))
  (POST "/logout" {params :params} (auth/logout params))
  (GET "/test-token" {params :params} (auth/test-token params)))

(defroutes app
  (GET  "/" [] (resource-response "index.html" {:root "public"}))
  (resources "/")
  (context "/api" [] api)
  (not-found (four-oh-four-page)))
