(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.generic.intersects
  
  "Benchmarks for dispatch of (intersects? set0 set1)."
  
  {:author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-29"
   :version "2017-06-07"}
  
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]
            [palisades.lakes.elements.scripts.defs :as defs]
            [palisades.lakes.elements.scripts.generic.bench :as bench])
  
  (:import [palisades.lakes.elements.java.sets Intersects]))
;;----------------------------------------------------------------
(defn fname [sets0 n0 sets1 n1 ext]
  (str "intersects" 
       "." (mc/fn-name sets0) 
       "." n0
       "." (mc/fn-name sets1) 
       "." n1
       "." (java.time.LocalDate/now) "." ext))
;;----------------------------------------------------------------
(time
  (let [double-intervals (bench/double-intervals -1.0e6 1.0e6)
        integer-intervals (bench/integer-intervals -1000000 1000000)
        random-intervals (bench/random-intervals -1.0e6 1.0e6)]
    (doseq [n [1024]];;(take 1 (bench/data-sizes))]
      (doseq [sets0 [random-intervals double-intervals integer-intervals]]
        (doseq [sets1 [random-intervals double-intervals integer-intervals]]
          (println (mc/fn-name sets0) n 
                   (mc/fn-name sets1) n 
                   (.toString (java.time.LocalDateTime/now)))
          (time
            (let [logf (defs/log-file
                         *ns* (fname sets0 n sets1 n "txt"))]
              (io/make-parents logf)
              (with-open [w (io/writer logf)]
                (binding [*out* w]
                  (let [data-map 
                        (merge 
                          (bench/generate-sets sets0 n 0) 
                          (bench/generate-sets sets1 n 1))]
                    (reduce
                      (fn [records record]
                        (if record
                          (let [records (conj records record)]
                            (mc/write-tsv 
                              records 
                              (defs/data-file 
                                *ns* (fname sets0 n sets1 n "tsv")))
                            records)
                          records))
                      []
                      (for [f [#_bench/nolookup
                               #_bench/inline
                               bench/manual
                               bench/bytecodes
                               bench/dyna
                               #_bench/handle
                               #_bench/method
                               #_bench/callsite
                               #_bench/multi]]
                        (bench/bench f data-map)))))))))))))
;;----------------------------------------------------------------
