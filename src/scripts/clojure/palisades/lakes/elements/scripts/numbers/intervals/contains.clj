(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.intervals.contains
  
  "Benchmarks for dispatch of (contains interval number)."
  
  {:author "palisades dot lakes at gmail dot com"
   :since "2017-05-26"
   :version "2017-05-29"}
  
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]
            [palisades.lakes.elements.scripts.defs :as defs]
            [palisades.lakes.elements.scripts.numbers.intervals.bench :as bench]))
;;----------------------------------------------------------------
(defn fname [sets nsets palisades.lakes.elements nelements ext]
  (str "dispatch" 
       "." (mc/fn-name sets) 
       "." nsets
       "." (mc/fn-name palisades.lakes.elements) 
       "." nelements
       "." (java.time.LocalDate/now) "." ext))
;;----------------------------------------------------------------
(time
  (doseq [sets [(bench/uniform-interval-generator -1.0 1.0)]]
    (doseq [palisades.lakes.elements [(bench/uniform-double-generator -1.0 1.0)]]
      (doseq [n (take 1 (bench/data-sizes))]
        (println (mc/fn-name sets) n 
                 (mc/fn-name palisades.lakes.elements) n 
                 (.toString (java.time.LocalDateTime/now)))
        (time
          (let [logf (defs/log-file  
                       *ns* (fname sets n palisades.lakes.elements n "txt"))]
            (io/make-parents logf)
            (with-open [w (io/writer logf)]
              (binding [*out* w]
                (let [data-map 
                      (merge 
                        (bench/generate-double-intervals sets n) 
                        (bench/generate-doubles palisades.lakes.elements n))]
                  (reduce
                    (fn [records record]
                      (if record
                        (let [records (conj records record)]
                          (mc/write-tsv 
                            records 
                            (defs/data-file  
                              *ns* (fname sets n palisades.lakes.elements n "tsv")))
                          records)
                        records))
                    []
                    (for [f [bench/generic bench/static]]
                      (bench/bench f data-map identity))))))))))))
;;----------------------------------------------------------------
