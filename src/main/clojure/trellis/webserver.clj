(ns trellis.webserver
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server :as hks])
  
  (:use [mikera.cljutils.error]))

(def DEFAULT-PORT 8080)

(defrecord WebServer []
  component/Lifecycle
	  (start [this]
	    (let [handler (or (:handler this) (error "No :handler specified for web server"))
            port (or (:port this) (error "No :port specified for web server")) 
            server (hks/run-server handler {:port port})]
	          (assoc this :server server)))
	
	  (stop [this]
	    (when-let [server (:server this)]
	      (server) ;; calling the server function shuts down the server
	      (dissoc this :server))))

(defn server
  ([handler opts]
    (let [opts (or opts {:port DEFAULT-PORT})
          opts (assoc opts :handler handler)]
      (map->WebServer opts))))