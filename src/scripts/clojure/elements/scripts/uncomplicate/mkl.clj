(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.scripts.uncomplicate.mkl
  
  {:doc "Hello world for neanderthal/mkl."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-24"
   :version "2017-04-25"}
  
  (:require [clojure.pprint :as pp]
            [elements.scripts.defs :as defs]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false) ;
(set! *unchecked-math* false)
(require '[uncomplicate.neanderthal
           [core :as unc] 
           [native :as unn]])
(require '[criterium.core :as criterium])
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(def x (unn/dv (into [] (defs/uniform-doubles (* 32 1024 1024)))))
(println (unc/dim x))
(pp/pprint 
  (defs/simplify
    (time
      (criterium/benchmark (unc/sum x) {}))))
;;----------------------------------------------------------------
