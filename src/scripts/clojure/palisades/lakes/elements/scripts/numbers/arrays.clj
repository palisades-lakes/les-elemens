(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.numbers.arrays
  
  {:doc "Memory consumption measurement."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-03-31"
   :version "2017-04-20"}
  
  (:require [clojure.set :as set]
            [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]
            [palisades.lakes.elements.api :as mc]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false) ;
(set! *unchecked-math* false)
(require '[criterium.core :as criterium])
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
