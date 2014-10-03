(ns trellis.component
  (:require [clojure.tools.logging :as log])
  (:require [com.stuartsierra.component :as component])
  (:use [mikera.cljutils.error]))

(defmacro with-component
  "Performs the operations specified by body after starting the given component and binding it to a symbol. Stops
   the component once complete (in a finally clause)."
  [[sym comp :as v] & body]
  (when-not (and (vector? v) (== 2 (count v))) (error "with-component requires a 2-element binding vector"))
  (when-not (symbol? sym) (error "with-component requires a symbol to bind"))
  `(let [~sym ~comp 
         ~sym (component/start ~sym)]
     (try 
       ~@body 
       (finally (component/stop ~sym)))))

