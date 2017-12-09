(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.seeds
  
  {:doc "Generate independent seeds for palisades.lakes.elements.numbers.prng."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-05"
   :version "2017-05-29"}
  
  (:require [clojure.java.io :as io]
            [palisades.lakes.elements.api :as mc])
  (:import java.time.LocalDate))
;;----------------------------------------------------------------
;; TODO: write to test/script resources?
;;----------------------------------------------------------------
;; for Well44497b
(mc/write-seed (mc/generate-randomdotorg-seed 1391)
               (io/file "src" "main" "resources" "seeds" 
                        (str "Well44497b-" (LocalDate/now) ".edn")))
;;----------------------------------------------------------------
;; for Mersenne Twister
(mc/write-seed (mc/generate-randomdotorg-seed 624)
               (io/file  "src" "main" "resources" "seeds" 
                         (str "MT-" (LocalDate/now) ".edn")))
;;----------------------------------------------------------------

