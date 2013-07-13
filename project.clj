(defproject async.get "0.1.0-SNAPSHOT"
  :plugins [[lein-cljsbuild "0.3.2"]
            [lein-ring "0.8.6"]]
  :hooks [leiningen.cljsbuild]
  :repositories {
    "sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"
  }
  :dependencies [
    ; Server Side
    [org.clojure/clojure "1.5.1"]
    [ring "1.2.0"]
    ; Client Side
    [org.clojure/clojurescript "0.0-1820"]
    [org.clojure/core.async "0.1.0-SNAPSHOT"]
    [prismatic/dommy "0.1.1"]
  ]
  :source-paths ["clj"]
  :ring {:handler server/app}
  :cljsbuild
    {:builds
     [{:source-paths ["cljs"],
       :incremental true,
       :compiler
       {:pretty-print true,
        :output-to "html/app.js",
        :warnings true,
        :optimizations :whitespace}}
     ]}
)
