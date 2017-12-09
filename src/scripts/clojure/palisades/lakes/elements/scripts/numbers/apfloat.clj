(set! *warn-on-reflection* true)
;;(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.apfloat
  
  {:doc "Apfloat experiments."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-24"
   :version "2017-05-24"}
  
  #_(:require [palisades.lakes.elements.api :as mc])
  (:import [org.apfloat Apfloat ApfloatMath]))
;;----------------------------------------------------------------
(println "round trip (Apfloat. d)")
(loop [d (double (Math/nextUp (/ 1.0 Math/E)))]
  (let [a (Apfloat. d)
        da (.doubleValue a)]
    (if (== d da)
      (recur (Math/nextUp d))
      (do 
        (println d)
        (println a)
        (println da)
        (println)))))
;;----------------------------------------------------------------
(println "round trip 'binary128' (Apfloat. d)")
(loop [i 0
       d (double (Math/nextUp (/ 1.0 Math/E)))]
  (let [a (Apfloat. d (long 113) (int 2))
        da (.doubleValue a)]
    (if (and (< i 1000000)
             (== d da))
      (recur (Math/nextUp d))
      (do 
        (println i)
        (println d)
        (println a)
        (println da)
        (println)))))
;;----------------------------------------------------------------
