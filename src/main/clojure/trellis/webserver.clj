(ns trellis.webserver
  (:require [com.stuartsierra.component :as component]
            [org.httpkit.server :as hks])
  (:use [mikera.cljutils.error]))

(def DEFAULT-PORT 8080)

(defn DEFAULT-HANDLER [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Trellis Default Ring Handler"})

(defrecord WebServer [port]
  component/Lifecycle
  (start [component]
    (let [handler (or (:handler (:handler component)) 
                      (error "No handler was provided as part of the handler 
                              component supplied to the web component"))
          port (or port (error "No :port specified for web server")) 
          server (try
                   (hks/run-server handler {:port port})
                   (catch Throwable t
                     (throw (java.lang.Error.
                              (str "Error starting server for component: " (pr-str component))
                              t))))]
      (assoc component :server server)))

  (stop [component]
    (when-let [server (:server component)]
      (server :timeout 100) ;; calling the server function shuts down the server
      (dissoc component :server))))

(defn new-web-server [port]
  (map->WebServer {:port port}))

; Dummy handler component 
; Generally you will want to implement your own handler component
; to do the setup and tear down
(defrecord Handler [handler]
  component/Lifecycle
  (start [component])
  (stop [component]))

(defn new-handler [handler]
  (map->Handler {:handler handler}))

(defn server
  "Creates a WebServer component with the given ring handler and options map.

   Options map defaults to {:port 8080} if not specified otherwise."
  ([handler]
    (server handler {}))
  ([handler opts]
    (let [opts (if (:port opts) opts (assoc opts :port DEFAULT-PORT))
          opts (assoc opts :handler (new-handler handler))]
      (map->WebServer opts))))
