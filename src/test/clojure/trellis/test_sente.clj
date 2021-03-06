(ns trellis.test-sente
  (:use clojure.test)
  (:require [clojure.java.jdbc :as j])
  (:require 
      [com.stuartsierra.component :as component]
      [trellis.sente :as sente]
      [trellis.webserver :as web]
      [trellis.component :refer [with-component]])
  (:use [mikera.cljutils error]))

(deftest sente-test 
  (let [s (sente/sente-server)]
    (with-component [s s]
      (is (:ch-recv s))
      ;; TODO: figure out how to test with a Clojure client
      ;; probably depends on: https://github.com/ptaoussanis/sente/issues/80
      (let []
        ))))

