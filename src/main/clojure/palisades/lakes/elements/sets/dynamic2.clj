(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.sets.dynamic2
  
  {:doc "defmultis for sets."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-06-05"
   :version "2017-06-05"}
  
  (:refer-clojure :exclude [contains? empty?])
  (:require [palisades.lakes.elements.generic.dynamic2 :as dyna2])
  (:import [java.util Collections]
           [com.google.common.collect Sets]
           [palisades.lakes.elements.java.numbers DoubleInterval IntegerInterval]))
;;----------------------------------------------------------------
(dyna2/defdynafn2 intersects?)
;;----------------------------------------------------------------
;; java.util.Set
;;----------------------------------------------------------------
(dyna2/defmethod intersects? 
  [java.util.Set java.util.Set]
  [^java.util.Set s0 ^java.util.Set s1] 
  (not (Collections/disjoint s0 s1)))
;;----------------------------------------------------------------
;; palisades.lakes.elements.java.sets.Set
;;----------------------------------------------------------------
(dyna2/defmethod intersects? 
  [DoubleInterval DoubleInterval]
  [^DoubleInterval s0 ^DoubleInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(dyna2/defmethod intersects? 
  [DoubleInterval IntegerInterval]
  [^DoubleInterval s0 ^IntegerInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(dyna2/defmethod intersects? 
  [IntegerInterval DoubleInterval]
  [^IntegerInterval s0 ^DoubleInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(dyna2/defmethod intersects? 
  [IntegerInterval IntegerInterval]
  [^IntegerInterval s0 ^IntegerInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(dyna2/defmethod intersects? 
  [DoubleInterval java.util.Set]
  [^DoubleInterval s0 ^java.util.Set s1]
  (.intersects s0 s1))
(dyna2/defmethod intersects? 
  [IntegerInterval java.util.Set]
  [^IntegerInterval s0 ^java.util.Set s1]
  (.intersects s0 s1))
(dyna2/defmethod intersects? 
  [java.util.Set DoubleInterval]
  [^java.util.Set s0 ^DoubleInterval s1]
  (.intersects s1 s0))
(dyna2/defmethod intersects? 
  [java.util.Set IntegerInterval]
  [^java.util.Set s0 ^IntegerInterval s1]
  (.intersects s1 s0))
;;----------------------------------------------------------------
