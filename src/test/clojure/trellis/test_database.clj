(ns trellis.test-database
  (:use clojure.test)
  (:require [clojure.java.jdbc :as j])
  (:require 
      [com.stuartsierra.component :as component]
      [trellis.database :as data])
  (:use [mikera.cljutils error]))

(def test-spec 
  {:classname   "org.h2.Driver" ; must be in classpath
   :subprotocol "h2"
   :subname "mem:test1"
   :user     ""
   :password ""
   :DB_CLOSE_DELAY "-1"})

(deftest test-connection
  (let [db (data/new-database test-spec)]
    (is (not (:connection db)))
    (let [db (component/start db)]
      (is (:connection db))
      (j/execute! db ["create table Test (ID bigint PRIMARY KEY AUTO_INCREMENT NOT NULL, Name Varchar)"]))))