(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.ratio
  
  {:doc "Test (double (rationalize (double x)))."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-04-14"
   :version "2017-05-24"}
  
  #_(:require [palisades.lakes.elements.api :as mc])
  (:import [clojure.lang Ratio]))
;;----------------------------------------------------------------
#_(doseq [d [(/ 1.0 Math/E) 
            (Math/nextUp (/ 1.0 Math/E)) 
            (Math/nextDown (/ 1.0 Math/E))]]
   (println d)
   (println (double (rationalize (double d))))
  (println (double (BigFraction. (double d)))))
;;----------------------------------------------------------------
#_(defn double<-ratio ^double [^Ratio r] (double r))
#_(defn double<-ratio ^double [^Ratio r] 
    (double (.decimalValue r)))
(defn double<-ratio ^double [^Ratio r]
  (.doubleValue (.decimalValue r)))
;;----------------------------------------------------------------
(println "round trip (double (rationalize (double d)))")
(loop [i (int 0)
       d (double (/ 1.0 Math/E))]
  (let [r (rationalize d)
        dr (double<-ratio r)]
    (if (and (< i (int 3))
             (== d dr))
      (recur (inc i)
             (Math/nextUp d))
      (do 
        (println i (== d dr))
        (println d (Double/toHexString d))
        (println r)
        (println dr (Double/toHexString dr))
        (println)))))
;;----------------------------------------------------------------
