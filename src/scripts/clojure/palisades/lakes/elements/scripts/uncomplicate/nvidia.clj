(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.uncomplicate.nvidia
  
  {:doc "Hello world for neanderthal/nvidia."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-24"
   :version "2017-05-29"}
  
  (:require [clojure.pprint :as pp]
            [palisades.lakes.elements.scripts.defs :as defs]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false) ;
(set! *unchecked-math* false)
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
(def u (into [] (defs/uniform-doubles (* 32 1024 1024))))
;;----------------------------------------------------------------
#_(println :default (count u))
#_(ucl2c/with-default
  (uno/with-default-engine
    (ucc/with-release [x (uno/clv u)]
      (pp/pprint 
        (defs/simplify
          (time
            (criterium/benchmark (unc/sum x) {})))))))
;;----------------------------------------------------------------
(println :default-1 (count u))
(ucl2l/with-default-1
  (uno/with-default-engine
    (ucc/with-release [x (uno/clv u)]
      (pp/pprint 
        (defs/simplify
          (time
            (criterium/benchmark (unc/sum x) {})))))))
;;----------------------------------------------------------------
(println :platform (count u))
(ucl2c/with-platform (first (ucl2c/platforms))
  (let [dev (first (ucl2c/sort-by-cl-version (ucl2c/devices :gpu)))]
    (ucl2c/with-context (ucl2c/context [dev])
      (ucl2c/with-queue (ucl2l/command-queue-1 dev)
        (uno/with-default-engine
          (ucc/with-release [x (uno/clv u)]
            (pp/pprint 
              (defs/simplify
                (time
                  (criterium/benchmark (unc/sum x) {}))))))))))

;;----------------------------------------------------------------
