(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.scripts.numbers.intervals.contains
  
  "Benchmarks for dispatch of (contains interval number)."
  
  {:author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-26"
   :version "2017-05-29"}
  
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [elements.api :as mc]
            [elements.scripts.defs :as defs]
            [elements.scripts.numbers.intervals.bench :as bench]))
;;----------------------------------------------------------------
(defn fname [sets nsets elements nelements ext]
  (str "dispatch" 
       "." (mc/fn-name sets) 
       "." nsets
       "." (mc/fn-name elements) 
       "." nelements
       "." (java.time.LocalDate/now) "." ext))
;;----------------------------------------------------------------
(time
  (doseq [sets [(bench/uniform-interval-generator -1.0 1.0)]]
    (doseq [elements [(bench/uniform-double-generator -1.0 1.0)]]
      (doseq [n (take 1 (bench/data-sizes))]
        (println (mc/fn-name sets) n 
                 (mc/fn-name elements) n 
                 (.toString (java.time.LocalDateTime/now)))
        (time
          (let [logf (defs/log-file  
                       *ns* (fname sets n elements n "txt"))]
            (io/make-parents logf)
            (with-open [w (io/writer logf)]
              (binding [*out* w]
                (let [data-map 
                      (merge 
                        (bench/generate-double-intervals sets n) 
                        (bench/generate-doubles elements n))]
                  (reduce
                    (fn [records record]
                      (if record
                        (let [records (conj records record)]
                          (mc/write-tsv 
                            records 
                            (defs/data-file  
                              *ns* (fname sets n elements n "tsv")))
                          records)
                        records))
                    []
                    (for [f [bench/generic bench/static]]
                      (bench/bench f data-map identity))))))))))))
;;----------------------------------------------------------------
