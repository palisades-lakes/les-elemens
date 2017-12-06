#_(set! *warn-on-reflection* true)
#_(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.scripts.doc.codox
  
  {:doc "Generate codox docs manually (not using leiningen)."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-24"
   :version "2017-05-24"}
  
  (:require [codox.main :as codox]))
;;----------------------------------------------------------------
(def options (assoc codox/defaults
                     :language     :clojure
   :root-path    (System/getProperty "user.dir")
   :output-path  "target/doc"
   :source-paths ["src/main/clojure"]
   :doc-paths    ["doc"]
   :doc-files    :all
   :namespaces   :all
   :exclude-vars #"^(map)?->\p{Upper}"
   :metadata     {}
   :themes       [:default]))

(codox/generate-docs options)
