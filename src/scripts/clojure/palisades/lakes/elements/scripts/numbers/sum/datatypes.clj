(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.sum.datatypes
  
  "Benchmarks for simple sums with various data representations."
  
  {:author "palisades dot lakes at gmail dot com"
   :since "2017-04-06"
   :version "2017-05-29"}
  
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]
            [palisades.lakes.elements.scripts.defs :as defs]
            [palisades.lakes.elements.scripts.numbers.sum.bench :as bench]))
;;----------------------------------------------------------------
(defn fname [generator n ext]
  (str "datatypes." 
       (mc/fn-name generator) 
       "." n
       "." (java.time.LocalDate/now) "." ext))
;;----------------------------------------------------------------
(time
  (doseq [generator [#_mc/random-doubles-nonnegative 
                     #_mc/random-doubles 
                     mc/random-doubles-minus-mean 
                     #_mc/random-doubles-zero-sum 
                     #_mc/uniform-doubles]]
    (doseq [n (take 1 (bench/data-sizes))]
      (println (mc/fn-name generator) n)
      (time
        (with-open [w (io/writer 
                        (defs/log-file
                          *ns* (fname generator n "txt")))]
          (binding [*out* w]
            (let [data-map (bench/data generator n)]
              (reduce
                (fn [records record]
                  (if record
                    (let [records (conj records record)]
                      (mc/write-tsv 
                        records 
                        (defs/data-file
                          *ns* (fname generator n "tsv")))
                      records)
                    records))
                []
                (for [coerce [double-array
                              mc/array-of-Double 
;                              bench/mkl-vector
;                              byte-array
;                              float-array
;                              int-array
;                              long-array
;                              short-array
;                              defs/lazy-list
;                              defs/array-list
;                              mc/array-of-Byte 
;                              mc/array-of-Float 
;                              mc/array-of-Integer
;                              mc/array-of-Long 
;                              mc/array-of-Short
;                              mc/array-of-BigInteger 
;                              mc/array-of-BigDecimal 
;                              mc/array-of-BigFraction 
;                              mc/array-of-Number
;                              mc/array-of-Object
;                              vec
;                              mc/array-of-Ratio
]
                      f [#_mc/sum-naive 
                         mc/sum-pairwise 
                         #_mc/sum-kahan]]
                  (bench/bench f coerce data-map))))))))))
;;----------------------------------------------------------------
