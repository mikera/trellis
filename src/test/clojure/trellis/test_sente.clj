(ns trellis.test-sente
  (:use clojure.test)
  (:require [clojure.java.jdbc :as j])
  (:require 
      [com.stuartsierra.component :as component]
      [trellis.sente :as sente])
  (:use [mikera.cljutils error]))

(deftest sente-test 
  (let [s (sente/sente-server)
        s (component/start s)
        s (component/stop s)]))

