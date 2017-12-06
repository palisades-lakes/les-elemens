(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.scripts.numbers.sum.algorithms
  
  "Benchmarks for sums of double[]."
  
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
  (str "algorithms." 
       (mc/fn-name generator) 
       "." n
       "." (java.time.LocalDate/now) "." ext))
;;----------------------------------------------------------------
(time
  (doseq [generator [#_mc/random-doubles-nonnegative 
                     #_mc/random-doubles 
                     #_mc/random-doubles-minus-mean 
                     mc/random-doubles-zero-sum]]
    (doseq [n (take 1 (bench/data-sizes))]
      (println (mc/fn-name generator) 
               n 
               (.toString (java.time.LocalDateTime/now)))
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
                      (defs/data-file *ns* (fname generator n "tsv")))
                    records)
                  records))
              []
              (for [f [#_mc/sum-zhu-hayes-exact
                       mc/sum-zhu-hayes-branch
                       mc/sum-zhu-hayes-nobranch
                       #_mc/sum-ifast
                       #_mc/sum-naive 
                       #_mc/sum-naive-accumulator
                       mc/sum-pairwise 
                       #_mc/sum-kahan 
                       #_mc/sum-kahan-accumulator]]
                (bench/bench f identity data-map))))))))))
;;----------------------------------------------------------------
