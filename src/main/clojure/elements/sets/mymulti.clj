(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.sets.mymulti
  
  {:doc "defmultis for sets."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-20"
   :version "2017-06-02"}
  
  (:refer-clojure :exclude [contains? empty?])
  (:require [elements.generic.multi :as mgm])
  (:import [java.util Collections]
           [com.google.common.collect Sets]
           [elements.java.generic Signatures]
           [elements.java.sets Difference Intersection Set Union]
           [elements.java.numbers DoubleInterval IntegerInterval]))
;;----------------------------------------------------------------
(mgm/defmulti empty?
  "Is set empty?"
  (fn empty?-dispatch ([set] (class set))))  
(mgm/defmulti cardinality
    "How many elements?"
  (fn cardinality-dispatch ([set] (class set))))  
;;----------------------------------------------------------------
(mgm/defmulti contains?
  "Is x an element of set?"
  (fn contains?-dispatch [set x] 
    (Signatures/get (class set) (class x)))) 
;;----------------------------------------------------------------
(defn element? [x set] (contains? set x)) 
;;----------------------------------------------------------------
(mgm/defmulti strict-subset?
  (fn strict-subset?-dispatch [s0 s1] 
    (Signatures/get (class s0) (class s1))))
(mgm/defmulti subset?
  (fn subset?-dispatch [s0 s1] 
    (Signatures/get (class s0) (class s1))))
;;----------------------------------------------------------------
(defn superset? [s0 s1] (subset? s1 s0))
(defn strict-superset? [s0 s1] (strict-subset? s1 s0))
;;----------------------------------------------------------------
(mgm/defmulti intersects?
  (fn intersects?-dispatch [s0 s1] 
    (Signatures/get (class s0) (class s1))))
(mgm/defmulti union
  (fn union-dispatch [s0 s1] 
    (Signatures/get (class s0) (class s1))))
(mgm/defmulti intersection
  (fn intersection-dispatch [s0 s1] 
    (Signatures/get (class s0) (class s1))))
(mgm/defmulti difference
  (fn difference-dispatch [s0 s1] 
    (Signatures/get (class s0) (class s1))))
;;----------------------------------------------------------------
;; java.util.Set
;;----------------------------------------------------------------
(mgm/defmethod empty? 
  java.util.Set 
  [^java.util.Set s] 
  (.isEmpty s))
(mgm/defmethod cardinality 
  java.util.Set 
  [^java.util.Set s] 
  (.size s))
(mgm/defmethod contains? 
  (Signatures/get java.util.Set Object) 
  [^java.util.Set s x]
  (.contains s x))
(mgm/defmethod union 
  (Signatures/get Object Object) 
  [s0 s1] 
  (Sets/union s0 s1))
(mgm/defmethod intersection 
  (Signatures/get Object Object) 
  [s0 s1] 
  (Sets/intersection s0 s1))
(mgm/defmethod intersects? 
  (Signatures/get java.util.Set java.util.Set) 
  [^java.util.Set s0 ^java.util.Set s1] 
  (not (Collections/disjoint s0 s1)))
(mgm/defmethod difference 
  (Signatures/get Object Object) 
  [s0 s1] 
  (Sets/difference s0 s1))
;;----------------------------------------------------------------
;; elements.java.sets.Set
;;----------------------------------------------------------------
(mgm/defmethod empty? Set [^Set set]
  (.isEmpty set))
(mgm/defmethod cardinality Set [^Set set]
  (.cardinality set))
(mgm/defmethod contains? (Signatures/get Set Object) [^Set set x]
  (.contains set x))
(mgm/defmethod union (Signatures/get Object Object) [s0 s1] 
  (Union/make s0 s1))
(mgm/defmethod intersection (Signatures/get Object Object) [s0 s1] 
  (Intersection/make s0 s1))
(mgm/defmethod difference (Signatures/get Object Object) [s0 s1] 
  (Difference/make s0 s1))
;;----------------------------------------------------------------
(mgm/defmethod intersects? 
  (Signatures/get DoubleInterval DoubleInterval)
  [^DoubleInterval s0 ^DoubleInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(mgm/defmethod intersects? 
  (Signatures/get DoubleInterval IntegerInterval)
  [^DoubleInterval s0 ^IntegerInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(mgm/defmethod intersects? 
  (Signatures/get IntegerInterval DoubleInterval)
  [^IntegerInterval s0 ^DoubleInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(mgm/defmethod intersects? 
  (Signatures/get IntegerInterval IntegerInterval)
  [^IntegerInterval s0 ^IntegerInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(mgm/defmethod intersects? 
  (Signatures/get DoubleInterval java.util.Set)
  [^DoubleInterval s0 ^java.util.Set s1]
  (.intersects s0 s1))
(mgm/defmethod intersects? 
  (Signatures/get IntegerInterval java.util.Set)
  [^IntegerInterval s0 ^java.util.Set s1]
  (.intersects s0 s1))
(mgm/defmethod intersects? 
  (Signatures/get java.util.Set DoubleInterval)
  [^java.util.Set s0 ^DoubleInterval s1]
  (.intersects s1 s0))
(mgm/defmethod intersects? 
  (Signatures/get java.util.Set IntegerInterval)
  [^java.util.Set s0 ^IntegerInterval s1]
  (.intersects s1 s0))
;;----------------------------------------------------------------
(mgm/defmulti contains-double?
  "Is x an element of the set s?"
  (fn contains?-dispatch ([s ^double x] (class s))))  
(mgm/defmethod contains-double? 
  DoubleInterval
  [^DoubleInterval s ^double x] 
  (.contains s x))#_(extend elements.java.numbers.DoubleInterval
  Set
  (empty? [this] (.isEmpty this))
  (contains? [this x] (.contains this x))
  (intersects? [this that] (.intersects this that))
  (strict-subset? [this that] (.strictSubset this that))
  (subset? [this that] (.subset this that)))
  
