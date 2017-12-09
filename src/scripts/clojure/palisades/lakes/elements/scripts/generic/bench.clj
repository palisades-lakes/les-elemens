(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.generic.bench
  
  {:doc "Benchmarks for generic function dispatch alternatives."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-29"
   :version "2017-06-06"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]
            [palisades.lakes.elements.sets.dynamic2 :as dyna]
            [palisades.lakes.elements.sets.multi :as multi]
            [palisades.lakes.elements.sets.mymulti :as mymulti]
            [palisades.lakes.elements.sets.protocol :as protocol]
            [palisades.lakes.elements.scripts.defs :as defs])
  
  (:import [org.apache.commons.rng UniformRandomProvider]
           [org.apache.commons.rng.sampling
            CollectionSampler]
           [org.apache.commons.rng.sampling.distribution 
            ContinuousSampler ContinuousUniformSampler
            DiscreteSampler DiscreteUniformSampler]
           [palisades.lakes.elements.java.numbers DoubleInterval IntegerInterval]
           [palisades.lakes.elements.java.sets Intersects IntersectsAsm 
            IntersectsCallSite IntersectsHandle 
            IntersectsMethod]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false)
(set! *unchecked-math* false)
(require '[criterium.core :as criterium])
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(defn data-sizes []
  (mapv #(* (long %) 16) 
        (take 6 (iterate #(* 4 (long %)) 1))))
;;----------------------------------------------------------------
;; return dependent on mutable state, not synchronized

(let [^UniformRandomProvider urp
      (mc/well44497b 
        (mc/read-seed 
          (io/resource "seeds/Well44497b-2017-05-28.edn")))]
  (defn- uniform-double-sampler 
    ^org.apache.commons.rng.sampling.distribution.ContinuousSampler 
    [^double z0 ^double z1]
    (assert (< z0 z1))
    (ContinuousUniformSampler. urp z0 z1)))

;; return dependent on mutable state, not synchronized

(let [^UniformRandomProvider urp
      (mc/well44497b 
        (mc/read-seed 
          (io/resource "seeds/Well44497b-2017-05-29.edn")))]
  (defn- uniform-integer-sampler 
    ^org.apache.commons.rng.sampling.distribution.DiscreteSampler 
    [^long z0 ^long z1]
    (assert (< z0 z1))
    (DiscreteUniformSampler. urp (int z0) (int z1))))
;;----------------------------------------------------------------
;; return dependent on mutable state, not synchronized
;; TODO: fn with runtime name incorporating parameters.

(defn double-intervals 
  ^clojure.lang.IFn [^double z0 ^double z1]
  (let [^ContinuousSampler sampler (uniform-double-sampler z0 z1)]
    (fn double-interval 
      ^palisades.lakes.elements.java.numbers.DoubleInterval []
      (let [z0 (.sample sampler)
            z1 (.sample sampler)]
        (if (<= z0 z1)
          (DoubleInterval/make z0 z1)
          (DoubleInterval/make z1 z0))))))
;;----------------------------------------------------------------
;; return dependent on mutable state, not synchronized
;; TODO: fn with runtime name incorporating parameters.

(defn integer-intervals
  ^clojure.lang.IFn [^double z0 ^double z1]
  (let [^DiscreteSampler sampler (uniform-integer-sampler z0 z1)]
    (fn integer-interval 
      ^palisades.lakes.elements.java.numbers.IntegerInterval []
      (let [z0 (.sample sampler)
            z1 (.sample sampler)]
        (if (<= z0 z1)
          (IntegerInterval/make z0 z1)
          (IntegerInterval/make z1 z0))))))
;;----------------------------------------------------------------
;; return dependent on mutable state, not synchronized
;; TODO: fn with runtime name incorporating parameters.

(let [^UniformRandomProvider urp
      (mc/well44497b 
        (mc/read-seed 
          (io/resource "seeds/Well44497b-2017-05-31.edn")))]
  (defn random-intervals ^clojure.lang.IFn [^double z0 ^double z1]
    (let [^CollectionSampler cs (CollectionSampler. 
                                  urp
                                  [(integer-intervals z0 z1)
                                   (double-intervals z0 z1)])]
      (fn random-interval [] ((.sample cs))))))
;;----------------------------------------------------------------
(defn generate-sets [^clojure.lang.IFn generator 
                     ^long n
                     ^long counter]
  (let [gname (mc/fn-name generator)
        _ (println gname n counter)
        ^objects sets (object-array n)]
    (dotimes [i n] (aset sets i (generator)))
    {(keyword (str "generator" counter)) gname 
     (keyword (str "sets" counter)) sets}))
;;----------------------------------------------------------------
(defn bench [^clojure.lang.IFn f 
             ^java.util.Map data-map]
  (println (keys data-map))
  (let [fname (mc/fn-name f)
        g0 (:generator0 data-map)
        ^objects s0 (:sets0 data-map)
        n0 (long (alength s0))
        g1 (:generator1 data-map)
        ^objects s1 (:sets1 data-map)
        n1 (long (alength s1))
        _(println fname g0 n0 g1 n1 (* n0 n1))
        result (criterium/benchmark (f s0 s1) {})
        value (double (first (:results result)))
        result (defs/simplify 
                 (assoc 
                   (merge result 
                          (dissoc data-map :raw :sets0 :sets1))
                   :value value
                   :algorithm fname))]
    (pp/pprint result)
    (println)
    (flush)
    result))
;;----------------------------------------------------------------
(defn- nolookup-DD ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^DoubleInterval s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (Intersects/intersects 
                    s0i ^DoubleInterval (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
(defn- nolookup-DI ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^DoubleInterval s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (Intersects/intersects 
                    s0i ^IntegerInterval (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
(defn- nolookup-ID ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^IntegerInterval s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (Intersects/intersects 
                    s0i ^DoubleInterval (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
(defn- nolookup-II ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^IntegerInterval s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (Intersects/intersects 
                    s0i ^IntegerInterval (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
(defn nolookup ^long [^objects s0 ^objects s1]
  (let [c0 (class (aget s0 0))
        c1 (class (aget s1 0))]
    (cond 
      (= DoubleInterval c0)
      (cond (= DoubleInterval c1) (nolookup-DD s0 s1)
            (= IntegerInterval c1) (nolookup-DI s0 s1)
            :else (throw (UnsupportedOperationException. 
                           (str c0 " " c1))))
      (= IntegerInterval c0)
      (cond (= DoubleInterval c1) (nolookup-ID s0 s1)
            (= IntegerInterval c1) (nolookup-II s0 s1)
            :else (throw (UnsupportedOperationException. 
                           (str c0 " " c1))))
      :else
      (throw (UnsupportedOperationException. (str c0))))))
;;----------------------------------------------------------------
(defn inline ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^Object s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (Intersects/inline s0i (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
;;----------------------------------------------------------------
(defn manual ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^Object s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (Intersects/manual s0i (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
;;----------------------------------------------------------------
(let [^IntersectsMethod g (IntersectsMethod/make)]
  (defn method ^long [^objects s0 ^objects s1]
    (let [n0 (alength s0)
          n1 (alength s1)
          ^clojure.lang.IFn$OOL subtotal 
          (fn subtotal ^long [^Object s0i ^objects s1]
            (loop [i1 (int 0)
                   k (long 0)]
              (cond (>= i1 n1) (long k)
                    (.intersects g s0i (aget s1 i1)) 
                    (recur (inc i1) (inc k))
                    :else (recur (inc i1) k))))]
      (loop [i0 (int 0)
             total (long 0)]
        (if (>= i0 n0)
          total
          (recur
            (inc i0)
            (+ total (.invokePrim subtotal (aget s0 i0) s1))))))))
;;----------------------------------------------------------------
(let [^IntersectsHandle g (IntersectsHandle/make)]
  (defn handle ^long [^objects s0 ^objects s1]
    (let [n0 (alength s0)
          n1 (alength s1)
          ^clojure.lang.IFn$OOL subtotal 
          (fn subtotal ^long [^Object s0i ^objects s1]
            (loop [i1 (int 0)
                   k (long 0)]
              (cond (>= i1 n1) (long k)
                    (.intersects g s0i (aget s1 i1)) 
                    (recur (inc i1) (inc k))
                    :else (recur (inc i1) k))))]
      (loop [i0 (int 0)
             total (long 0)]
        (if (>= i0 n0)
          total
          (recur
            (inc i0)
            (+ total (.invokePrim subtotal (aget s0 i0) s1))))))))
;;----------------------------------------------------------------
(let [^IntersectsCallSite g (IntersectsCallSite/make)]
  (defn callsite ^long [^objects s0 ^objects s1]
    (let [n0 (alength s0)
          n1 (alength s1)
          ^clojure.lang.IFn$OOL subtotal 
          (fn subtotal ^long [^Object s0i ^objects s1]
            (loop [i1 (int 0)
                   k (long 0)]
              (cond (>= i1 n1) (long k)
                    (.intersects g s0i (aget s1 i1)) 
                    (recur (inc i1) (inc k))
                    :else (recur (inc i1) k))))]
      (loop [i0 (int 0)
             total (long 0)]
        (if (>= i0 n0)
          total
          (recur
            (inc i0)
            (+ total (.invokePrim subtotal (aget s0 i0) s1))))))))
;;----------------------------------------------------------------
(defn protocol ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^Object s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (protocol/intersects? s0i (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
;;----------------------------------------------------------------
(defn multi ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^Object s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (multi/intersects? s0i (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
;;----------------------------------------------------------------
(defn mymulti ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^Object s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (mymulti/intersects? s0i (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
;;----------------------------------------------------------------
(defn dyna ^long [^objects s0 ^objects s1]
  (let [n0 (alength s0)
        n1 (alength s1)
        ^clojure.lang.IFn$OOL subtotal 
        (fn subtotal ^long [^Object s0i ^objects s1]
          (loop [i1 (int 0)
                 k (long 0)]
            (cond (>= i1 n1) (long k)
                  (dyna/intersects? s0i (aget s1 i1)) 
                  (recur (inc i1) (inc k))
                  :else (recur (inc i1) k))))]
    (loop [i0 (int 0)
           total (long 0)]
      (if (>= i0 n0)
        total
        (recur
          (inc i0)
          (+ total (.invokePrim subtotal (aget s0 i0) s1)))))))
;;----------------------------------------------------------------
(let [^Class asm (IntersectsAsm/load)
      _(println "loaded class:" asm)
      intersects? (.newInstance asm)]
  (defn bytecodes ^long [^objects s0 ^objects s1]
    (let [n0 (alength s0)
          n1 (alength s1)
          ^clojure.lang.IFn$OOL subtotal 
          (fn subtotal ^long [^Object s0i ^objects s1]
            (loop [i1 (int 0)
                   k (long 0)]
              (cond (>= i1 n1) (long k)
                    (intersects? s0i (aget s1 i1)) 
                    (recur (inc i1) (inc k))
                    :else (recur (inc i1) k))))]
      (loop [i0 (int 0)
             total (long 0)]
        (if (>= i0 n0)
          total
          (recur
            (inc i0)
            (+ total (.invokePrim subtotal (aget s0 i0) s1))))))))
;;----------------------------------------------------------------

