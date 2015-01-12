(ns trellis.environment
  (:require [environ.core :refer [env]]))

(defn env-map 
  "Pulls environment variables for each of the keys in ks.  These can be
  contained in a project profile.clj (for local testing, requiers lein-environ
  plugin), shell env variables or java properties. "
  [ks]
  {:post [(or (not (some nil? (vals %)))
              (throw (Exception. (str "Environment variable missing (out of env map) " %))))]}
  (into {} 
        (map (juxt identity env)
             ks)))
