(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.intervals.bench
  
  {:doc "Benchmarks for (contains interval element) dispatch 
         alternatives."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-05-26"
   :version "2017-05-29"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]
            [palisades.lakes.elements.sets.multi :as multi]
            [palisades.lakes.elements.sets.protocol :as protocol]
            [palisades.lakes.elements.scripts.defs :as defs])
  
  (:import [org.apache.commons.rng UniformRandomProvider]
           [org.apache.commons.rng.sampling.distribution 
            ContinuousSampler ContinuousUniformSampler]
           [palisades.lakes.elements.java.numbers DoubleInterval]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false)
(set! *unchecked-math* false)
(require '[criterium.core :as criterium])
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(def ^{:private true :tag ints} -seed- 
  (mc/read-seed 
    (io/resource "seeds/Well44497b-2017-05-26.edn")))

(def ^{:private true :tag UniformRandomProvider :mutable true} 
     urp (mc/well44497b -seed-))

;; return dependent on mutable state, not synchronized

(defn- uniform-sampler 
  ^org.apache.commons.rng.sampling.distribution.ContinuousSampler 
  [^double z0 ^double z1]
  (assert (<= z0 z1))
  (ContinuousUniformSampler. urp z0 z1))
;;----------------------------------------------------------------
(defn data-sizes []
  (mapv #(* (long %) 64) 
        (take 6 (iterate #(* 4 (long %)) 1))))
;;----------------------------------------------------------------
;; return dependent on mutable state, not synchronized
;; TODO: fn with runtime name incorporating parameters.

(defn uniform-interval-generator 
  ^clojure.lang.IFn [^double z0 ^double z1]
  (let [^ContinuousSampler sampler (uniform-sampler z0 z1)]
    (fn uniform-interval 
      ^palisades.lakes.elements.java.numbers.DoubleInterval []
      (let [z0 (.sample sampler)
            z1 (.sample sampler)]
        (if (<= z0 z1)
          (DoubleInterval/make z0 z1)
          (DoubleInterval/make z1 z0))))))
;;----------------------------------------------------------------
(defn generate-double-intervals [^clojure.lang.IFn generator 
                                 ^long n]
  (let [gname (mc/fn-name generator)
        _ (println gname n)
        ^"[Lelements.java.numbers.DoubleInterval;" sets 
        (make-array palisades.lakes.elements.java.numbers.DoubleInterval n)]
    (dotimes [i n] 
      (aset sets i 
            ^palisades.lakes.elements.java.numbers.DoubleInterval (generator)))
    
    {:set-generator gname :nsets n :sets sets}))
;;----------------------------------------------------------------
;; return dependent on mutable state, not synchronized
;; TODO: fn with runtime name incorporating parameters.

(defn uniform-double-generator 
  ^clojure.lang.IFn [^double z0 ^double z1]
  (let [^ContinuousSampler sampler (uniform-sampler z0 z1)]
    (fn uniform-double ^double [] (.sample sampler))))
;;----------------------------------------------------------------
(defn generate-doubles [^clojure.lang.IFn$D generator ^long n]
  (let [gname (mc/fn-name generator)
        _ (println gname n)
        ^doubles raw (make-array Double/TYPE n)]
    (dotimes [i n] (aset-double raw i (.invokePrim generator)))
    {:element-generator gname
     :nelements n
     :raw raw}))
;;----------------------------------------------------------------
(defn bench [^clojure.lang.IFn f 
             ^java.util.Map data-map 
             ^clojure.lang.IFn coerce]
  (println (keys data-map))
  (let [sets (:sets data-map)
        palisades.lakes.elements (time (coerce (:raw data-map)))
        dname (.getCanonicalName (class palisades.lakes.elements))
        ename (.getCanonicalName (mc/element-type palisades.lakes.elements))
        fname (mc/fn-name f) 
        cname (mc/fn-name coerce)]
    (println fname cname dname ename)
    (try
      (let [result (criterium/benchmark (f sets palisades.lakes.elements) {})
            value (double (first (:results result)))
            result (defs/simplify 
                     (assoc 
                       (merge result 
                              (dissoc data-map :raw :sets))
                       :value value
                       :algorithm fname
                       :representation cname
                       :data dname
                       :elementtype ename))]
        (pp/pprint result)
        (println)
        (flush)
        result)
      (catch UnsupportedOperationException e
        (println "No method for" fname dname)))))
;;----------------------------------------------------------------
(defn hand ^long [^"[Lelements.java.numbers.DoubleInterval;" sets
                    ^doubles palisades.lakes.elements]
  (let [nsets (alength sets)
        nelements (alength palisades.lakes.elements)]
    (loop [i (int 0)
           total (long 0)]
      (if (>= i nsets)
        total
        (let [^DoubleInterval seti (aget sets i)
              z0 (.min seti)
              z1 (.max seti)
              ^long subtotal 
              (loop [j (int 0)
                     k (long 0)]
                (cond (>= j nelements) 
                      (long k)
                      
                      (let [zi (aget palisades.lakes.elements j)]
                        (and (<= z0 zi) (< zi z1))) 
                      (recur (inc j) (inc k))
                      
                      :else 
                      (recur (inc j) k)))]
          
          (recur (inc i) (+ total subtotal)))))))
;;----------------------------------------------------------------
(defn static ^long [^"[Lelements.java.numbers.DoubleInterval;" sets
                    ^doubles palisades.lakes.elements]
  (let [nsets (alength sets)
        nelements (alength palisades.lakes.elements)]
    (loop [i (int 0)
           total (long 0)]
      (if (>= i nsets)
        total
        (let [^DoubleInterval seti (aget sets i)
              ^long subtotal 
              (loop [j (int 0)
                     k (long 0)]
                (cond (>= j nelements) 
                      (long k)
                      
                      (.contains seti (aget palisades.lakes.elements j)) 
                      (recur (inc j) (inc k))
                      
                      :else 
                      (recur (inc j) k)))]
          
          (recur (inc i) (+ total subtotal)))))))
;;----------------------------------------------------------------
(defn protocol 
  ^long [^"[Lelements.java.numbers.DoubleInterval;" sets
         ^doubles palisades.lakes.elements]
  (let [nsets (alength sets)
        nelements (alength palisades.lakes.elements)]
    (loop [i (int 0)
           total (long 0)]
      (if (>= i nsets)
        total
        (let [^DoubleInterval seti (aget sets i)
              ^long subtotal 
              (loop [j (int 0)
                     k (long 0)]
                (cond (>= j nelements) 
                      (long k)
                      
                      (protocol/contains-double? seti (aget palisades.lakes.elements j)) 
                      (recur (inc j) (inc k))
                      
                      :else 
                      (recur (inc j) k)))]
          
          (recur (inc i) (+ total subtotal)))))))
;;----------------------------------------------------------------
(defn generic 
  ^long [^"[Lelements.java.numbers.DoubleInterval;" sets
         ^doubles palisades.lakes.elements]
  (let [nsets (alength sets)
        nelements (alength palisades.lakes.elements)]
    (loop [i (int 0)
           total (long 0)]
      (if (>= i nsets)
        total
        (let [^DoubleInterval seti (aget sets i)
              ^long subtotal 
              (loop [j (int 0)
                     k (long 0)]
                (cond (>= j nelements) 
                      (long k)
                      
                      (generic/contains-double? seti (aget palisades.lakes.elements j)) 
                      (recur (inc j) (inc k))
                      
                      :else 
                      (recur (inc j) k)))]
          
          (recur (inc i) (+ total subtotal)))))))
;;----------------------------------------------------------------
