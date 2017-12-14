(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.test.numbers.sum
  
  {:doc "Unit tests for palisades.lakes.elements.java.numbers.sum"
   :author "palisades dot lakes at gmail dot com"
   :since "2017-03-27"
   :version "2017-04-20"}
  
  (:require [clojure.test :as test]
            [palisades.lakes.elements.api :as mc])
  (:import [java.math BigDecimal]
           [clojure.lang Numbers Ratio]
           [palisades.lakes.elements.java.numbers.sum Kahan Naive Pairwise]))
;;----------------------------------------------------------------
(def integers [1 2 3 4])
(def fractions [0.1 0.2 0.3 0.4])
;;----------------------------------------------------------------
(test/deftest naive
  (let [x (double-array integers)]
    (test/is (== 10.0 (Naive/sum x))))
  (let [x (double-array fractions)]
    (test/is (== 1.0 (Naive/sum x))))
  (let [x (mc/array-of-BigDecimal integers)]
    (test/is (== 10.0 (Naive/sum x))))
  (let [x (mc/array-of-BigDecimal fractions)]
    (test/is (== 1.0 (Naive/sum x))))
  (let [x (mc/array-of-Ratio integers)]
    (test/is (== 10.0 (Naive/sum x))))
  (let [x (mc/array-of-Ratio fractions)]
    (test/is (== 1.0 (Naive/sum x)))))
 ;----------------------------------------------------------------
(test/deftest pairwise
  (let [x (double-array [1.0 2.0 3.0 4.0])]
    (test/is (== 10.0 (Pairwise/sum x)))))
;;----------------------------------------------------------------
