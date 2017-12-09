(set! *warn-on-reflection* true)
;;(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.half-ulp
  
  {:doc "Test half-ulp calculations."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-11"
   :version "2017-05-11"}
  
  (:require [clojure.java.io :as io]
            [palisades.lakes.elements.api :as mc])
  (:import [palisades.lakes.elements.java.numbers Doubles]))
;;----------------------------------------------------------------
(def ^{:private true :tag ints} -seed- 
  (mc/read-seed 
    (io/resource "seeds/Well44497b-2017-05-11.edn")))
(def z0 (doubles (Doubles/randomDoubles 4 (mc/well44497b -seed-))))
(def z1 (doubles (Doubles/randomSubnormals 4 (mc/well44497b -seed-))))
(def z2 (doubles (Doubles/randomNonfinites 4 (mc/well44497b -seed-))))
;;----------------------------------------------------------------
(doseq [z [z0 z1 z2]]
  (dotimes [i (int (alength (doubles z)))]
    (let [zi (aget (doubles z) i)]
      (println i zi 
               (Double/toHexString zi) 
               (Double/toHexString (Math/ulp zi)))
      (println i 
               (Double/toHexString (* 0.5 (Math/ulp zi)))
               (Doubles/isHalfUlp (* 0.5 (Math/ulp zi))))
      (println i (Doubles/isNormal (* 0.5 (Math/ulp zi))))
      (println)))
  (println))
;;----------------------------------------------------------------
