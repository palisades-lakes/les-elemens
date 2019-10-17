(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.sum.gpu
  
  
  "Benchmarks for palisades.lakes.elements.java.numbers.Sum."
  
  {:author "palisades dot lakes at gmail dot com"
   :since "2017-04-06"
   :version "2018-08-29"}
  
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]
            [palisades.lakes.elements.scripts.numbers.sum.defs :as defs])
  (:import [java.time LocalDate]))
;;----------------------------------------------------------------
;(set! *warn-on-reflection* false) ;
;(set! *unchecked-math* false)
;(require '[uncomplicate.commons.core :as ucc])
;(require '[uncomplicate.clojurecl 
;           [core :as ucl2c] 
;           [info :as ucl2i]])
;(require '[uncomplicate.neanderthal
;           [core :as unc] 
;           [native :as unn]
;           [opencl :as uno]])
;(require '[criterium.core :as criterium])
;(set! *warn-on-reflection* true)
;(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
;(defn fname [generator n ext]
;  (str "gpu." 
;       (mc/fn-name generator) 
;       "." n
;       "." (java.time.LocalDate/now) "." ext))
;;----------------------------------------------------------------
;(time
;  (let [today (LocalDate/now)]
;    (ucl2c/with-default-1
;      ;;(uno/with-default-engine
;      (uno/with-engine uno/opencl-double ucl2c/*command-queue*
;        (doseq [coerce [double-array
;                        ;;defs/mkl-vector
;                        ;;defs/opencl-vector
;                        ;;defs/opencl1-vector
;                        ;;defs/opencl0-vector
;                        ;;defs/lazy-list
;                        ;;defs/array-list
;                        ;;byte-array
;                        ;;float-array
;                        ;;int-array
;                        ;;long-array
;                        ;;short-array
;                        ;;mc/array-of-Byte 
;                        ;;mc/array-of-Double 
;                        ;;mc/array-of-Float 
;                        ;;mc/array-of-Integer
;                        ;;mc/array-of-Long 
;                        ;;mc/array-of-Short
;                        ;;mc/array-of-BigInteger 
;                        ;;mc/array-of-BigDecimal 
;                        ;;mc/array-of-BigFraction 
;                        ;;mc/array-of-Number
;                        ;;mc/array-of-Object
;                        ;;vec
;                        ;;mc/array-of-Ratio
;                        ]
;                ]
;          (println (mc/fn-name coerce))
;          (with-open [w (io/writer 
;                          (defs/log-file 
;                            *ns* (fname generator n ".txt")))]
;            (binding [*out* w]
;              (reduce
;                (fn [records record]
;                  (if record
;                    (let [records (conj records record)]
;                      (mc/write-tsv 
;                        records (defs/data-file 
;                                  *ns* (fname generator n ".tsv"))
;                        records)
;                      records))
;                  []
;                  (for [generator [#_defs/random-doubles-nonnegative 
;                                   #_defs/random-doubles 
;                                   defs/random-doubles-minus-mean 
;                                   #_defs/random-doubles-zero-sum 
;                                   #_defs/uniform-doubles]
;                        n (map #(* 1024 1024 (long %)) [#_1 #_4 16 #_64 #_256 #_1024])
;                        f [#_mc/sum-zhu-hayes-exact
;                           #_mc/sum-zhu-hayes-branch
;                           #_mc/sum-zhu-hayes-nobranch
;                           #_mc/sum-big-decimal
;                           #_mc/sum-big-fraction
;                           #_mc/sum-ifast
;                           mc/sum-naive 
;                           #_mc/sum-naive-accumulator
;                           #_mc/sum-pairwise 
;                           #_mc/sum-kahan 
;                           #_mc/sum-kahan-accumulator]]
;                    (defs/bench f coerce generator n))))))))))
;  ;;----------------------------------------------------------------
  