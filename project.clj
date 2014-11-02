(defproject net.mikera/trellis "0.0.5-SNAPSHOT"
  :description "Trellis Web Framework"
  :url "http://github.com/mikera/trellis"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.reader "0.8.11"]
                 [org.clojure/tools.nrepl "0.2.6"]
                 
                 ;; CLJ
                 [ring/ring-core "1.3.1"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/java.jdbc "0.3.6"]
                 [org.clojure/tools.logging "0.3.1"]
                 [clj-time "0.8.0"]
                 [ring-cors "0.1.4"]
                 [compojure "1.2.1"]
                 [liberator "0.12.2"]
                 [cheshire "5.3.1"]
                 [net.mikera/clojure-utils "0.6.1"]
                 [javax.servlet/servlet-api "2.5"]        
                 [com.stuartsierra/component "0.2.2"]
                 [prismatic/schema "0.3.2"]
                 
                 ;; CLJS
                 [org.clojure/clojurescript "0.0-2371"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [secretary "1.2.1"]
                 [markdown-clj "0.9.55" :exclusions [org.clojure/clojure]]
                 [cljs-http "0.1.19"]
                 [om "0.7.3"]
                 [http-kit "2.1.19"]
                 
                 ;; Both CLJ and CLJS
                 [com.taoensso/sente "1.2.0"]]
  
  :profiles {:dev {:dependencies [[com.h2database/h2 "1.4.182"]
                                  [net.mikera/cljunit "0.3.1"]]
                   :java-source-paths ["src/test/java"]}}

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.7"]
            [lein-release "1.0.5"]]

  :ring {:handler trellis.core/app
         }

  :source-paths ["src/main/clojure" "src/main/cljs"]
  :test-paths ["src/test/clojure"]
  
  :resource-paths ["resources"]
  
  :main trellis.core

  :cljsbuild {
              :builds [{:id "release"
                        :source-paths ["src/main/cljs"]
                        :compiler {
                                   :output-to "resources/public/js/app.js"
                                   :output-dir "resources/public/js/out"
                                   :source-map "resources/public/js/app.js.map"
                                   :optimizations :advanced
                                   :pretty-print false
                                   :output-wrapper false
                                   :preamble ["om/react.min.js"]
                                   :externs ["om/externs/react.js"]
                                   :closure-warnings {:non-standard-jsdoc :off}}}]})
