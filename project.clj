(defproject net.mikera/trellis "0.0.6"
  :description "Trellis Web Framework"
  :url "http://github.com/mikera/trellis"

  :dependencies [[org.clojure/clojure "1.7.0-RC1"]
                 [org.clojure/tools.reader "0.9.2"]
                 [org.clojure/tools.nrepl "0.2.10"]
                 
                 ;; CLJ
                 [ring/ring-core "1.3.2"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/java.jdbc "0.3.7"]
                 [org.clojure/tools.logging "0.3.1"]
                 [clj-time "0.9.0"]
                 [ring-cors "0.1.7"]
                 [compojure "1.3.4"]
                 [liberator "0.13"]
                 [cheshire "5.4.0"]
                 [net.mikera/clojure-utils "0.6.1"]
                 [javax.servlet/servlet-api "2.5"]        
                 [com.stuartsierra/component "0.2.3"]
                 [prismatic/schema "0.4.3"]
                 [environ "1.0.0"]
                 
                 ;; CLJS
                 [org.clojure/clojurescript "0.0-3297"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [secretary "1.2.3"]
                 [markdown-clj "0.9.66" :exclusions [org.clojure/clojure]]
                 [cljs-http "0.1.30"]
                 [om "0.7.3"]
                 [http-kit "2.1.19"]
                 
                 ;; Both CLJ and CLJS
                 [com.taoensso/sente "1.4.1"]]
  
  :profiles {:dev {:dependencies [[com.h2database/h2 "1.4.187"]
                                  [net.mikera/cljunit "0.3.1"]]
                   :java-source-paths ["src/test/java"]}}

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.7"]
            [lein-release "1.0.6"]]

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
