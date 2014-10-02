(ns trellis.database
  (:require [clojure.java.jdbc :as jdbc])
  (:require [clojure.tools.logging :as log])
  (:require [com.stuartsierra.component :as component])
  (:use [mikera.cljutils.error]))

;; ================================================
;; Database connection spec examples

;; Standard spec for H2 database"
(def h2-spec
  {:classname "org.h2.Driver" ; must be in classpath
   :subprotocol "h2"
   :subname "~/test"
   :user     "sa"
   :password ""
   :host "127.0.0.1" 
   :AUTO_SERVER "TRUE"
   :DB_CLOSE_DELAY "-1"})

;; Standard spec for in-memory testing with H2 database
(def test-spec 
  {:classname   "org.h2.Driver" ; must be in classpath
   :subprotocol "h2"
   :subname "mem:db1"
   :user     ""
   :password ""
   :DB_CLOSE_DELAY "-1"})

(def ^:dynamic *default-db-spec* h2-spec)


;; ================================================
;; JDBC Database component
;;
;; wraps a tools.jdbc connection

(defrecord Database [db-spec connection]
  component/Lifecycle

  (start [component]
    (let [conn (jdbc/get-connection (:db-spec component))]
      (assoc component :connection conn)))

  (stop [component]
    (.close connection)
    (assoc component :connection nil)))


(defn new-database 
  "Creates a new database" 
  ([]
    (new-database *default-db-spec*))
  ([db-spec]
    (Database. db-spec nil)))