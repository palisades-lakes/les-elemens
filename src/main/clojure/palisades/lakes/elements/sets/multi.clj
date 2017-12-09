(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.sets.multi
  
  {:doc "defmultis for sets."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-20"
   :version "2017-06-06"}
  
  (:refer-clojure :exclude [contains? empty?])
  (:import [java.util Collections]
           [com.google.common.collect Sets]
           [palisades.lakes.elements.java.sets Difference Intersection Set Union]
           [palisades.lakes.elements.java.numbers DoubleInterval IntegerInterval]))
;;----------------------------------------------------------------
(defmulti empty?
  "Is set empty?"
  (fn empty?-dispatch ([set] (class set))))  
(defmulti cardinality
    "How many palisades.lakes.elements?"
  (fn cardinality-dispatch ([set] (class set))))  
;;----------------------------------------------------------------
(defmulti contains?
  "Is x an element of set?"
  (fn contains?-dispatch ([set x] [ (class set) (class x)])))  
;;----------------------------------------------------------------
(defmulti intersects?
  "Do s0 and s1 intersect??"
  (fn intersects?-dispatch ([s0 s1] [(class s0) (class s1)])))  
;;----------------------------------------------------------------
(defn element? [x set] (contains? set x)) 
;;----------------------------------------------------------------
(defmulti strict-subset?
  (fn strict-subset?-dispatch [s0 s1] [(class s0) (class s1)]))
(defmulti subset?
  (fn subset?-dispatch [s0 s1] [(class s0) (class s1)]))
;;----------------------------------------------------------------
(defn superset? [s0 s1] (subset? s1 s0))
(defn strict-superset? [s0 s1] (strict-subset? s1 s0))
;;----------------------------------------------------------------
(defmulti intersects?
  (fn intersects?-dispatch [s0 s1] [(class s0) (class s1)]))
(defmulti union
  (fn union-dispatch [s0 s1] [(class s0) (class s1)]))
(defmulti intersection
  (fn intersection-dispatch [s0 s1] [(class s0) (class s1)]))
(defmulti difference
  (fn difference-dispatch [s0 s1] [(class s0) (class s1)]))
;;----------------------------------------------------------------
;; java.util.Set
;;----------------------------------------------------------------
(defmethod empty? java.util.Set [^java.util.Set s] (.isEmpty s))
(defmethod cardinality java.util.Set [^java.util.Set s] (.size s))
(defmethod contains? [java.util.Set Object] [^java.util.Set s x]
  (.contains s x))
(defmethod union [Object Object] [s0 s1] 
  (Sets/union s0 s1))
(defmethod intersection [Object Object] [s0 s1] 
  (Sets/intersection s0 s1))
(defmethod intersects? [java.util.Set java.util.Set] [s0 s1] 
  (not (Collections/disjoint s0 s1)))
(defmethod difference [Object Object] [s0 s1] 
  (Sets/difference s0 s1))
;;----------------------------------------------------------------
;; palisades.lakes.elements.java.sets.Set
;;----------------------------------------------------------------
(defmethod empty? Set [^Set set]
  (.isEmpty set))
(defmethod cardinality Set [^Set set]
  (.cardinality set))
(defmethod contains? [Set Object] [^Set set x]
  (.contains set x))
(defmethod union [Object Object] [s0 s1] 
  (Union/make s0 s1))
(defmethod intersection [Object Object] [s0 s1] 
  (Intersection/make s0 s1))
(defmethod difference [Object Object] [s0 s1] 
  (Difference/make s0 s1))
;;----------------------------------------------------------------
(defmethod intersects? 
  [DoubleInterval DoubleInterval]
  [^DoubleInterval s0 ^DoubleInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(defmethod intersects? 
  [DoubleInterval IntegerInterval]
  [^DoubleInterval s0 ^IntegerInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(defmethod intersects? 
  [IntegerInterval DoubleInterval]
  [^IntegerInterval s0 ^DoubleInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(defmethod intersects? 
  [IntegerInterval IntegerInterval]
  [^IntegerInterval s0 ^IntegerInterval s1]
  (and (> (.max s0) (.min s1))
       (> (.max s1) (.min s0))))
(defmethod intersects? 
  [DoubleInterval java.util.Set]
  [^DoubleInterval s0 ^java.util.Set s1]
  (.intersects s0 s1))
(defmethod intersects? 
  [IntegerInterval java.util.Set]
  [^IntegerInterval s0 ^java.util.Set s1]
  (.intersects s0 s1))
(defmethod intersects? 
  [java.util.Set DoubleInterval]
  [^java.util.Set s0 ^DoubleInterval s1]
  (.intersects s1 s0))
(defmethod intersects? 
  [java.util.Set IntegerInterval]
  [^java.util.Set s0 ^IntegerInterval s1]
  (.intersects s1 s0))
;;----------------------------------------------------------------
(defmulti contains-double?
  "Is x an element of the set s?"
  (fn contains?-dispatch ([s ^double x] (class s))))  
(defmethod contains-double? 
  DoubleInterval
  [^DoubleInterval s ^double x] 
  (.contains s x))#_(extend palisades.lakes.elements.java.numbers.DoubleInterval
  Set
  (empty? [this] (.isEmpty this))
  (contains? [this x] (.contains this x))
  (intersects? [this that] (.intersects this that))
  (strict-subset? [this that] (.strictSubset this that))
  (subset? [this that] (.subset this that)))
  
