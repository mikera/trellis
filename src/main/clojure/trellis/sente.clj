(ns trellis.sente
  (:require
    [clojure.tools.logging :as log]
    [clojure.core.async :as async :refer []]
    [com.stuartsierra.component :as component]
    [taoensso.sente :as sente]
    ))

(declare sente-server)

(defrecord SenteServer [ch-recv 
                        send-fn 
                        ajax-post-fn 
                        ajax-get-or-ws-handshake-fn 
                        connected-uids]
  component/Lifecycle
	  (start [s]
	    (if (:ch-recv s)
         s
         (do ;; start sente component
           (let [csr (sente/make-channel-socket! {})]
            (merge s csr)))))
	  (stop [s]
	    (if (:ch-recv s)
        (do ;; stop sente component
          (sente-server))
        s)))

(defn sente-server 
  "Returns a sente server-side component. 
   The ajax functions are intented to be used as ring handlers. " []
  (SenteServer. nil nil nil nil nil))

;(defn sente-server 
;  "Returns a sente server-side component. 
;   The ajax functions are intented to be used as ring handlers. " []
;  (let [{:keys [ch-recv ; ChannelSocket's receive channel
;                send-fn ; ChannelSocket's send API fn
;                ajax-post-fn ; function for Ring handler POST 
;                ajax-get-or-ws-handshake-fn ; function for Ring handler GET
;                connected-uids ; Watchable, read-only atom
;                ] :as csr}
;        (sente/make-channel-socket! {})]
;     (SenteServer. ch-recv 
;                   send-fn 
;                   ajax-post-fn 
;                   ajax-get-or-ws-handshake-fn 
;                   connected-uids)))