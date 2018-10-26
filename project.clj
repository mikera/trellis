(defproject net.mikera/trellis "0.1.0"
  :description "Trellis Web Framework"
  :url "http://github.com/mikera/trellis"

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.reader "1.3.2"]
                 [org.clojure/tools.nrepl "0.2.13"]

                 ;; CLJ
                 [ring/ring-core "1.7.0"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [org.clojure/tools.logging "0.4.1"]
                 [clj-time "0.15.1"]
                 [ring-cors "0.1.12"]
                 [compojure "1.6.1"]
                 [liberator "0.15.2"]
                 [cheshire "5.8.1"]
                 [net.mikera/clojure-utils "0.8.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [com.stuartsierra/component "0.3.2"]
                 [prismatic/schema "1.1.9"]
                 [environ "1.0.2"]
                 ;; CLJS
                 [org.clojure/clojurescript "1.10.339"]
                 [org.clojure/core.async "0.4.474"]
                 [secretary "1.2.3"]
                 [markdown-clj "1.0.5" :exclusions [org.clojure/clojure]]
                 [cljs-http "0.1.45"]
                 [om "0.7.3"]
                 [http-kit "2.3.0"]

                 ;; Both CLJ and CLJS
                 [com.taoensso/sente "1.3.0"]]

  :profiles {:dev {:dependencies [[com.h2database/h2 "1.4.197"]
                                  [net.mikera/cljunit "0.6.0"]]
                   :java-source-paths ["src/test/java"]}}

  :plugins [[lein-cljsbuild "1.0.3"]
            [lein-ring "0.8.7"]
            [lein-ancient "0.6.15"]
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
