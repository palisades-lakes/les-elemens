(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.sum.bench
  
  {:doc "Benchmarks for sums."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-04-06"
   :version "2017-05-29"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]
            [palisades.lakes.elements.scripts.defs :as defs])
  
  (:import [java.util ArrayList]
           [com.carrotsearch.sizeof RamUsageEstimator]
           [uncomplicate.neanderthal.internal.host.buffer_block
            RealBlockVector]
           [palisades.lakes.elements.java.accumulate.sum BigFractionCondition]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false)
(set! *unchecked-math* false)
(require '[clatrix.core :as clatrix])
(require '[uncomplicate.commons.core :as ucc])
(require '[uncomplicate.clojurecl 
           [core :as ucl2c] 
           [info :as ucl2i]
           [legacy :as ucl2l]])
(require '[uncomplicate.neanderthal
           [core :as unc] 
           [native :as unn]
           [opencl :as uno]])
(require '[criterium.core :as criterium])
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
;; coercions
;;----------------------------------------------------------------
(defn clatrix-vector ^clatrix.core.Vector [x] (clatrix/vector x))
;;----------------------------------------------------------------
(defn mkl-vector ;;neanderthal-real-block-vector 
  ^uncomplicate.neanderthal.internal.host.buffer_block.RealBlockVector 
  [x] 
  (unn/dv (vec x)))

(defn opencl-vector ;;neanderthal-default-cl-block-vector 
  ^uncomplicate.neanderthal.internal.opencl.clblock.CLBlockVector 
  [x] 
  (ucl2c/with-default
    (uno/with-default-engine
      (uno/clv (vec x)))))

#_(defn opencl1-vector ;;neanderthal-default1-cl-block-vector 
   ^uncomplicate.neanderthal.internal.opencl.clblock.CLBlockVector 
  [x] 
  (ucl2l/with-default-1
    (uno/with-default-engine
      (uno/clv (vec x)))))

(defn opencl1-vector ;;neanderthal-default1-cl-block-vector 
  ^uncomplicate.neanderthal.internal.opencl.clblock.CLBlockVector 
  [x] 
      (uno/clv (vec x)))

(defn opencl0-vector ;;neanderthal-gpu0-cl-block-vector 
  ^uncomplicate.neanderthal.internal.opencl.clblock.CLBlockVector
  [x] 
  (ucl2c/with-platform (first (ucl2c/platforms))
    (let [dev (first 
                (ucl2c/sort-by-cl-version (ucl2c/devices :gpu)))]
      (ucl2c/with-context (ucl2c/context [dev])
        (ucl2c/with-queue (ucl2l/command-queue-1 dev)
          (uno/with-default-engine
            (uno/clv (vec x))))))))
;;----------------------------------------------------------------
(defn data-sizes []
  (mapv #(* (long %) 1024) 
        (take 6 (iterate #(* 8 (long %)) 8))))
;;----------------------------------------------------------------
(def ^{:private true 
       :tag org.apache.commons.rng.UniformRandomProvider}
     -urp- 
  (mc/well44497b 
    (mc/read-seed 
      (io/resource "seeds/Well44497b-2017-04-05.edn"))))
;;----------------------------------------------------------------
(defn data [generator n]
  (println (mc/fn-name generator) n)
  (let [^doubles raw (generator n -urp-)
        bdc (BigFractionCondition/make)
        _ (time (.addAll bdc raw))
        truth (.sum bdc)
        condition (.doubleValue bdc)
        gname (mc/fn-name generator)]
    (println gname n truth condition)
    {:truth truth
     :condition condition
     :generator gname
     :n n
     :raw raw}))
;;----------------------------------------------------------------
(defn bench [f coerce data-map]
  (let [data (time (coerce (:raw data-map)))
        sizeof (RamUsageEstimator/sizeOf data)
        dname (.getCanonicalName (class data))
        ename (.getCanonicalName (mc/element-type data))
        fname (mc/fn-name f) 
        cname (mc/fn-name coerce)]
    (println fname cname dname)
    (try
      (let [result (criterium/benchmark (f data) {})
            value (double (first (:results result)))
            truth (double (:truth data-map))
            result (defs/simplify 
                     (assoc 
                       (merge result (dissoc data-map :raw))
                       :value value
                       :error (- truth value)
                       :relative-error (Math/abs (/ (- truth value) truth))
                       :algorithm fname
                       :representation cname
                       :data dname
                       :kB (/ sizeof 1024.0)
                       :elementtype ename))]
        (pp/pprint result)
        (println)
        (flush)
        result)
      (catch UnsupportedOperationException e
        (println "No method for" fname dname)))))
;;----------------------------------------------------------------
