(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.numbers.prng
  
  {:doc "pseudo-random number generators."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-04-05"
   :version "2017-05-29"}
  
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.pprint :as pp])
  (:import [java.util Random]
           [java.io PushbackReader]
           [org.apache.commons.rng UniformRandomProvider]
           [org.apache.commons.rng.sampling.distribution 
            BoxMullerGaussianSampler 
            ContinuousSampler ContinuousUniformSampler 
            DiscreteSampler DiscreteUniformSampler]
           [org.apache.commons.rng.simple RandomSource]
           [org.apache.commons.rng.simple.internal 
            ByteArray2IntArray]
           [org.uncommons.maths.random 
            DefaultSeedGenerator RandomDotOrgSeedGenerator]
           [palisades.lakes.elements.java.numbers PRNG]))
;;----------------------------------------------------------------
(defn generate-default-seed ^ints [^long size]
  (.convert 
    (ByteArray2IntArray.)
    (.generateSeed (DefaultSeedGenerator/getInstance) 
      (* 4 (int size)))))
;;----------------------------------------------------------------
(defn generate-randomdotorg-seed ^ints [^long size]
  (.convert 
    (ByteArray2IntArray.)
    (.generateSeed (RandomDotOrgSeedGenerator.) 
      (* 4 (int size)))))
;;----------------------------------------------------------------
;; http://www.iro.umontreal.ca/~panneton/WELLRNG.html
;; http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/random/Well44497b.html
(defn well44497b 
  ^org.apache.commons.rng.UniformRandomProvider [^ints seed]
  (assert (== 1391 (int (alength seed))))
  (RandomSource/create RandomSource/WELL_44497_B seed nil))
;;----------------------------------------------------------------
(defn mersenne-twister 
  ^org.apache.commons.rng.UniformRandomProvider [^ints seed]
  (assert (== 624 (int (alength seed))))
  (RandomSource/create RandomSource/MT seed nil))
;;----------------------------------------------------------------
(defn gaussian-generator 
  ^clojure.lang.IFn$D [^double mean 
                       ^double std 
                       ^UniformRandomProvider urp]
  (let [^ContinuousSampler g (BoxMullerGaussianSampler. 
                               urp mean std)]
    (fn gaussian-generator ^double [] (.sample g))))
;;----------------------------------------------------------------
(defn uniform-generator 
  ^clojure.lang.IFn$D [^double umin
                       ^double umax
                       ^UniformRandomProvider urp]
  (let [^ContinuousSampler g (ContinuousUniformSampler. 
                               urp umin umax)]
    (fn uniform-generator ^double [] (.sample g))))
;;----------------------------------------------------------------
(defn uniform-long-generator 
  ^clojure.lang.IFn$L [^long umin
                       ^long umax
                       ^UniformRandomProvider urp]
  (let [^DiscreteSampler g (DiscreteUniformSampler. 
                               urp umin umax)]
    (fn uniform-long-generator ^long [] (.sample g))))
;;----------------------------------------------------------------
(defn gaussians 
  (^doubles [^long n ^double mu ^double sigma 
             ^UniformRandomProvider urp]
    (assert (<= 0.0 sigma))
    (let [g (gaussian-generator mu sigma urp)
          a (double-array n)]
      (dotimes [i n] (aset-double a i (g)))
      a))
  (^doubles [^long n ^UniformRandomProvider urp]
    (gaussians n 0.0 1.0 urp)))
;;----------------------------------------------------------------
;; TODO: assert upper exclusive?
(defn uniform-doubles 
  (^doubles [^long n ^double lower ^double upper
             ^UniformRandomProvider urp]
    (assert (<= lower upper))
    (let [^ContinuousSampler g (ContinuousUniformSampler.
                                 urp lower upper)
          a (double-array n)]
      (dotimes [i n] (aset-double a i (.sample g)))
      a)))
;;----------------------------------------------------------------
(defn uniform-ints
  (^ints [^long n ^long lower ^long upper
          ^UniformRandomProvider urp]
    (assert (<= lower upper))
    (let [^DiscreteSampler g (DiscreteUniformSampler. 
                               urp (int lower) (int upper))
          a (int-array n)]
      (dotimes [i n] (aset-double a i (.sample g)))
      a)))
;;----------------------------------------------------------------
(defn write-seed [^ints seed f]
  (with-open [w (io/writer f)]
    (binding [*out* w]
      (pp/pprint (into [] seed)))))
;;----------------------------------------------------------------
(defn read-seed ^ints [f]
  (with-open [r (PushbackReader. (io/reader f))]
    (int-array (edn/read r))))
;;----------------------------------------------------------------
;; test data generators for sums
(defn random-doubles-nonnegative 
  (^doubles [^long n 
             ^long delta 
             ^UniformRandomProvider urp]
    (PRNG/randomDoublesNonNegative n delta urp))
  (^doubles [^long n ^UniformRandomProvider urp]
    (PRNG/randomDoublesNonNegative n urp)))

(defn random-doubles 
  (^doubles [^long n 
             ^long delta 
             ^UniformRandomProvider urp]
    (PRNG/randomDoubles n delta urp))
  (^doubles [^long n ^UniformRandomProvider urp]
    (PRNG/randomDoubles n urp)))

(defn random-doubles-minus-mean 
  (^doubles [^long n 
             ^long delta 
             ^UniformRandomProvider urp]
    (PRNG/randomDoublesMinusMean n delta urp))
  (^doubles [^long n ^UniformRandomProvider urp]
    (PRNG/randomDoublesMinusMean n urp)))

(defn random-doubles-zero-sum 
  (^doubles [^long n 
             ^long delta 
             ^UniformRandomProvider urp]
    (PRNG/randomDoublesZeroSum n delta urp))
  (^doubles [^long n ^UniformRandomProvider urp]
    (PRNG/randomDoublesZeroSum n urp)))
;;----------------------------------------------------------------
