(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.sets.protocol
  
  {:doc "protocol for sets."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-22"
   :version "2017-05-22"}
  
  (:refer-clojure :exclude [contains? empty?])
  (:require [clojure.set :as cljset])
  (:import [elements.java.numbers DoubleInterval]))
;;----------------------------------------------------------------
(defprotocol Set
  
  "Protocol for object representing sets of various kinds, not
   necessarily finite."
  
  (empty?
    [this] 
    "Does this set have any elements?")
  
  (cardinality 
    [this] 
    "How many elements does this set have?")
  
  (contains? 
    [this x] 
    "Is x an element of this set?")
  
  (contains-double? 
    [this ^double x] 
    "Is x an element of this set?")
  
  (intersects? [this that])
  
  (strict-subset? 
    [this that]
    "Is this a strict subset of <code>that</code>?
     That is, <code>set</code> contains something in 
     <code>this</code>.")
   
  (subset? 
    [this that]
    "Is this a subset of <code>set</code>?")
  
  (intersection [this that])
  (union [this that])
  (difference [this that]))
;;----------------------------------------------------------------
(extend-type elements.java.sets.Set
  Set
  (empty? [this] (.isEmpty this))
  (contains? [this x] (.contains this x))
  (contains-double? [this ^double x] (.contains this (Double/valueOf x)))
  (intersects? [this that] (.intersects this that))
  (strict-subset? [this that] (.strictSubsetOf this that))
  (subset? [this that] (.subsetOf this that)))
;;----------------------------------------------------------------
(extend-type elements.java.numbers.DoubleInterval
  Set
  (empty? [this] (.isEmpty this))
  (contains? [this x] (.contains this x))
  (contains-double? [^elements.java.numbers.DoubleInterval this 
                     ^double x] 
    (.contains this x))
  (intersects? [this that] (.intersects this that))
  (strict-subset? [this that] (.strictSubsetOf this that))
  (subset? [this that] (.subsetOf this that)))
;;----------------------------------------------------------------
