(ns trellis.sente
  (:require
    [clojure.tools.logging :as log]
    [com.stuartsierra.component :as component]
    [taoensso.sente :as sente]
    ))

(defrecord SenteServer [ch-recv 
                        send-fn 
                        ajax-post-fn 
                        ajax-get-or-ws-handshake-fn 
                        connected-uids]
  )

(defn sente-server 
  "Returns a sente server-side component. 
   The ajax functions are intented to be used as ring handlers. " []
  (let [{:keys [ch-recv ; ChannelSocket's receive channel
                send-fn ; ChannelSocket's send API fn
                ajax-post-fn ; function for Ring handler POST 
                ajax-get-or-ws-handshake-fn ; function for Ring handler GET
                connected-uids ; Watchable, read-only atom
                ]}
        (sente/make-channel-socket! {})]
     (SenteServer. ch-recv 
                   send-fn 
                   ajax-post-fn 
                   ajax-get-or-ws-handshake-fn 
                   connected-uids)))