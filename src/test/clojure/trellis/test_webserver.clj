(ns trellis.test-webserver
  (:use clojure.test)
  (:require [clojure.java.jdbc :as j])
  (:require 
      [com.stuartsierra.component :as component]
      [trellis.webserver :as web])
  (:use [mikera.cljutils error]))

(deftest webserver-creation
  (let [ws (web/server web/DEFAULT-HANDLER {:port 9999})
        ws (component/start ws)]
    (is (:server ws))
    (let [ws (component/stop ws)]
      (is (not (:server ws))))))

