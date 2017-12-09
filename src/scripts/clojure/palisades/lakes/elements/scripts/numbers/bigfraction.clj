(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.bigfraction
  
  {:doc "BigFraction experiments."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-24"
   :version "2017-05-24"}
  
  #_(:require [palisades.lakes.elements.api :as mc])
  (:import [org.apache.commons.math3.fraction BigFraction]))
;;----------------------------------------------------------------
(println "round trip (BigFraction. (double d))")
(loop [i (int 0)
       d (double (/ 1.0 Math/E))]
  (let [^BigFraction bf (BigFraction. d)
        dbf (.doubleValue bf)]
    (if (and (< i (int 2))
             (== d dbf))
      (recur (inc i)
             (Math/nextUp d))
      (do 
        (println i (== d dbf))
        (println d (Double/toHexString d))
        (println bf)
        (println dbf (Double/toHexString dbf))
        (println)))))
;;----------------------------------------------------------------
