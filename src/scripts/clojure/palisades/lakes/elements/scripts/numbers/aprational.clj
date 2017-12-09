(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.aprational
  
  {:doc "Aprational experiments."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-24"
   :version "2017-05-24"}
  
  #_(:require [palisades.lakes.elements.api :as mc])
  (:import [java.math BigInteger RoundingMode]
           [org.apfloat Apint Aprational AprationalMath]
           [org.apache.commons.math3.fraction BigFraction]))
;;----------------------------------------------------------------
(defn big-fraction<-double ^BigFraction [^double d]
  (BigFraction. d))

(defn aprational<-big-fraction ^Aprational [^BigFraction bf]
  (let [^Apint denominator (Apint. (.getDenominator bf) (int 2))
        ^Apint numerator (Apint. (.getNumerator bf) (int 2))]
    (Aprational. numerator denominator)))

#_(defn aprational<-big-fraction ^Aprational [^BigFraction bf]
    (let [^Apint denominator (Apint. (.getDenominator bf))
          ^Apint numerator (Apint. (.getNumerator bf))
          s (str numerator " / " denominator)]
      (println s)
      (Aprational. s (int 2))))

(defn aprational<-double ^Aprational [^double d]
  (aprational<-big-fraction (big-fraction<-double d)))

#_(defn double<-aprational ^double [^Aprational a]
    (.doubleValue a))

(defn double<-aprational ^double [^Aprational a]
  (.doubleValue 
    (AprationalMath/round a (long 113) RoundingMode/HALF_EVEN)))
;;----------------------------------------------------------------
(println "round trip (aprational<-double d)")
(loop [i (int 0)
       d (double (/ 1.0 Math/E))]
  (let [^Aprational a (aprational<-double d)
        da (double<-aprational a)]
    (if (and (< i (bit-shift-left 1 53))
             (== d da))
      (recur (inc i)
             (Math/nextUp d))
      (do 
        (println i (== d da))
        (println a (.radix a) (.scale a) (.precision a))
        (println d (Double/toHexString d))
        (println a)
        (println da (Double/toHexString da))
        (println (.radix a) (.precision a))
        (println)))))
;;----------------------------------------------------------------
