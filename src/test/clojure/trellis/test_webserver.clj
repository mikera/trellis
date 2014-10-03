(ns trellis.test-webserver
  (:use clojure.test)
  (:require [clojure.java.jdbc :as j])
  (:require 
      [com.stuartsierra.component :as component]
      [trellis.component :refer [with-component]]
      [trellis.webserver :as web])
  (:require [org.httpkit.client :as http])
  (:use [mikera.cljutils error]))

(deftest webserver-creation
  (let [ws (web/server web/DEFAULT-HANDLER {:port 9999})
        ws (component/start ws)]
    (is (:server ws))
    (is (= 9999 (:port ws)))
    (let [ws (component/stop ws)]
      (is (not (:server ws))))))

(deftest test-with-component
  (with-component [ws (web/server web/DEFAULT-HANDLER {:port 9999})]
    ;; (println (str ws))
    (is (== 200 (:status ((:handler ws) {}))))
;    (let [resp @(http/get (str "http://127.0.0.1:9999/"))]
;      (println resp)
;      (is (== 200 (:status resp))))
    (is (:server ws))))

