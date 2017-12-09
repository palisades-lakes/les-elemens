(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.defs
  
  {:doc "Utilities for benchmarking scripts."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-06"
   :version "2017-05-29"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [palisades.lakes.elements.api :as mc]))
;;----------------------------------------------------------------
(defn ^java.io.File ns-file [prefix for-ns fname]
  (let [f (apply io/file 
                 prefix 
                 (concat
                   (drop 2 (s/split (str for-ns) #"\."))
                   [fname]))]
    (io/make-parents f)
    f))
(defn ^java.io.File log-file [for-ns fname]
  (ns-file "logs" for-ns fname))
(defn ^java.io.File data-file [for-ns fname]
  (ns-file "data" for-ns fname))
;;----------------------------------------------------------------
;; coercions
;;----------------------------------------------------------------
(defn array-list ^java.util.ArrayList [x] 
  (java.util.ArrayList. ^java.util.List (vec x)))
(defn lazy-list [x] (map identity (vec x)))
;;----------------------------------------------------------------
;; criterium output
;;----------------------------------------------------------------
(defn simplify [record]
  (let [record (dissoc record
                       :runtime-details :os-details
                       :samples :results
                       :overhead :final-gc-time
                       :sample-count :execution-count :tail-quantile
                       :warmup-time :outlier-variance :outliers
                       :options :sample-mean :sample-variance
                       :warmup-executions
                       :variance)]
    (assoc (dissoc record :mean) 
           :msec (first (:mean record))
           ;;:variance (first (:variance record))
           :lower-q (first (:lower-q record))
           :upper-q (first (:upper-q record)))))
;;----------------------------------------------------------------
