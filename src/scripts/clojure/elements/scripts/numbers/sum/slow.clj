(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.scripts.numbers.sum.slow
  
  "Benchmarks for slow methods for sums of double[]."
  
  {:author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-06"
   :version "2017-05-29"}
  
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [elements.api :as mc]
            [elements.scripts.defs :as defs]
            [elements.scripts.numbers.sum.bench :as bench]))
;;----------------------------------------------------------------
(defn fname [generator n ext]
  (str "slow." 
       (mc/fn-name generator) 
       "." n
       "." (java.time.LocalDate/now) "." ext))
;;----------------------------------------------------------------
(time
  (doseq [generator [#_mc/random-doubles-nonnegative 
                     #_mc/random-doubles 
                     #_mc/random-doubles-minus-mean 
                     mc/random-doubles-zero-sum 
                     #_mc/uniform-doubles]]
    (doseq [n (take 1 (bench/data-sizes))]
      (println (mc/fn-name generator) 
               n 
               (java.time.LocalDateTime/now))
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
                        *ns*(fname generator n "tsv")))
                    records)
                  records))
              []
              (for [f [#_mc/sum-DoubleAccumulator
                       #_mc/sum-DoubleAdder
                       #_mc/sum-DoubleSummaryStatistics
                       #_mc/sum-Stats
                       #_mc/sum-DescriptiveStatistics
                       #_mc/sum-math3
                       #_mc/sum-SummaryStatistics
                       #_mc/sum-SynchronizedSummaryStatistics
                       #_mc/sum-areduce
                       #_mc/sum-loop
                       mc/sum-reduce
                       #_mc/sum-big-decimal
                       #_mc/sum-big-fraction
                       #_mc/sum-ratio
                       #_mc/sum-aprational]]
                (bench/bench f identity data-map))))))))))
;;----------------------------------------------------------------
