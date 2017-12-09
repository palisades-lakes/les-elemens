(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.test.sets
  
  {:doc "Unit tests for palisades.lakes.elements.sets"
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-20"
   :version "2017-04-20"}
  
  (:require [clojure.test :as test]
            [palisades.lakes.elements.api :as mc]))
;; mvn clojure:test -Dtest=palisades.lakes.elements.test.sets
;;----------------------------------------------------------------
(test/deftest clojure
  (test/is (mc/element? :a #{:a :b :c})))
;;----------------------------------------------------------------
