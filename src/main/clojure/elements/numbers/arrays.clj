(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.numbers.arrays
  
  {:doc "Utilities for arrays of numbers."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-07"
   :version "2017-04-12"}
  
  (:require [elements.numbers.numbers :as nn])
            
  (:import [java.math BigDecimal]
           [org.apache.commons.math3.fraction BigFraction]
           [clojure.lang Numbers Ratio]))
;;----------------------------------------------------------------
;; TODO: replace this by a small number of 'generic' functions,
;; with 'methods' generated at runtime, as needed.
;;----------------------------------------------------------------
;; object array type predicates
;;----------------------------------------------------------------
(def array-of-Object-type 
  (let [a (make-array Object 0)] (class a)))
(defn array-of-Object? [x] (instance? array-of-Object-type x))
;;----------------------------------------------------------------
(def array-of-Number-type 
  (let [a (make-array Number 0)] (class a)))
(defn array-of-Number? [x] (instance? array-of-Number-type x))
;;----------------------------------------------------------------
(def array-of-Byte-type 
  (let [a (make-array Byte 0)] (class a)))
(defn array-of-Byte? [x] (instance? array-of-Byte-type x))
;;----------------------------------------------------------------
(def array-of-Double-type 
  (let [a (make-array Double 0)] (class a)))
(defn array-of-Double? [x] (instance? array-of-Double-type x))
;;----------------------------------------------------------------
(def array-of-Float-type 
  (let [a (make-array Float 0)] (class a)))
(defn array-of-Float? [x] (instance? array-of-Float-type x))
;;----------------------------------------------------------------
(def array-of-Integer-type 
  (let [a (make-array Integer 0)] (class a)))
(defn array-of-Integer? [x] (instance? array-of-Integer-type x))
;;----------------------------------------------------------------
(def array-of-Long-type 
  (let [a (make-array Long 0)] (class a)))
(defn array-of-Long? [x] (instance? array-of-Long-type x))
;;----------------------------------------------------------------
(def array-of-Short-type 
  (let [a (make-array Short 0)] (class a)))
(defn array-of-Short? [x] (instance? array-of-Short-type x))
;;----------------------------------------------------------------
(def array-of-BigInteger-type 
  (let [a (make-array BigInteger 0)] (class a)))
(defn array-of-BigInteger? [x] (instance? array-of-BigInteger-type x))
;;----------------------------------------------------------------
(def array-of-BigDecimal-type 
  (let [a (make-array BigDecimal 0)] (class a)))
(defn array-of-BigDecimal? [x] (instance? array-of-BigDecimal-type x))
;;----------------------------------------------------------------
(def array-of-BigFraction-type 
  (let [a (make-array BigFraction 0)] (class a)))
(defn array-of-BigFraction? [x] (instance? array-of-BigFraction-type x))
;;----------------------------------------------------------------
(def array-of-Ratio-type 
  (let [a (make-array Ratio 0)] (class a)))
(defn array-of-Ratio? [x] (instance? array-of-Ratio-type x))
;;----------------------------------------------------------------
;; primitive arrays
;;----------------------------------------------------------------
(def boolean-array-type (let [a (boolean-array 0)] (class a)))
(defn boolean-array? [x] (instance? boolean-array-type x))
(def byte-array-type (let [a (byte-array 0)] (class a)))
(defn byte-array? [x] (instance? byte-array-type x))
(def char-array-type (let [a (char-array 0)] (class a)))
(defn char-array? [x] (instance? char-array-type x))
(def double-array-type (let [a (double-array 0)] (class a)))
(defn double-array? [x] (instance? double-array-type x))
(def float-array-type (let [a (float-array 0)] (class a)))
(defn float-array? [x] (instance? float-array-type x))
(def int-array-type (let [a (int-array 0)] (class a)))
(defn int-array? [x] (instance? int-array-type x))
(def long-array-type (let [a (long-array 0)] (class a)))
(defn long-array? [x] (instance? long-array-type x))
(def short-array-type (let [a (short-array 0)] (class a)))
(defn short-array? [x] (instance? short-array-type x))
;;----------------------------------------------------------------
;; TODO: move to array namespace
;;----------------------------------------------------------------
(defn array? [x] (.isArray (class x)))
(defn size ^long [x] 
  ;; any array of any class instances matches the first clause.
  (cond (array-of-Object? x) (alength ^"[Ljava.lang.Object;" x)
        (boolean-array? x) (alength (booleans x))
        (byte-array? x) (alength (bytes x))
        (char-array? x) (alength (chars x))
        (double-array? x) (alength (doubles x))
        (float-array? x) (alength (floats x))
        (long-array? x) (alength (longs x))
        (int-array? x) (alength (ints x))
        (short-array? x) (alength (shorts x))
        (instance? java.util.Collection x) 
        (.size ^java.util.Collection x)
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x)))))) 
;;----------------------------------------------------------------
;; coercions
;; TODO: handle more cases
;; TODO: generate code as needed from templates
;;----------------------------------------------------------------
(defn array-of-Object ^"[Ljava.lang.Object;" [x]
  (cond (array-of-Object? x) x
        
        (number? x) (make-array Object (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Object (int n))]
          (dotimes [i n] (aset y i (Double/valueOf (aget x i))))
          y)
        
        (coll? x) (into-array Object x)
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Number ^"[Ljava.lang.Number;" [x]
  (cond (array-of-Number? x) x
        
        (number? x) (make-array Number (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Number (int n))]
          (dotimes [i n] (aset y i (Double/valueOf (aget x i))))
          y)
        
        (coll? x) (into-array Number (map num x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Byte ^"[Ljava.lang.Byte;" [x]
  (cond (array-of-Byte? x) x
        
        (number? x) (make-array Byte (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Byte (int n))]
          (dotimes [i n] (aset y i (nn/to-Byte (aget x i))))
          y)
        
        (coll? x) (into-array Byte (map nn/to-Byte x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Double ^"[Ljava.lang.Double;" [x]
  (cond (array-of-Double? x) x
        
        (number? x) (make-array Double (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Double (int n))]
          (dotimes [i n] (aset y i (nn/to-Double (aget x i))))
          y)
        
        (coll? x) (into-array Double (map nn/to-Double x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Float ^"[Ljava.lang.Float;" [x]
  (cond (array-of-Float? x) x
        
        (number? x) (make-array Float (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Float (int n))]
          (dotimes [i n] (aset y i (nn/to-Float (aget x i))))
          y)
        
        (coll? x) (into-array Float (map nn/to-Float x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Integer ^"[Ljava.lang.Integer;" [x]
  (cond (array-of-Integer? x) x
        
        (number? x) (make-array Integer (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Integer (int n))]
          (dotimes [i n] (aset y i (nn/to-Integer (aget x i))))
          y)
        
        (coll? x) (into-array Integer (map nn/to-Integer x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Long ^"[Ljava.lang.Long;" [x]
  (cond (array-of-Long? x) x
        
        (number? x) (make-array Long (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Long (int n))]
          (dotimes [i n] (aset y i (nn/to-Long (aget x i))))
          y)
        
        (coll? x) (into-array Long (map nn/to-Long x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Short ^"[Ljava.lang.Short;" [x]
  (cond (array-of-Short? x) x
        
        (number? x) (make-array Short (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Short (int n))]
          (dotimes [i n] (aset y i (nn/to-Short (aget x i))))
          y)
        
        (coll? x) (into-array Short (map nn/to-Short x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-BigInteger ^"[Ljava.math.BigInteger;" [x]
  (cond (array-of-BigInteger? x) x
        
        (number? x) (make-array BigInteger (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array BigInteger (int n))]
          (dotimes [i n] (aset y i (nn/to-BigInteger (aget x i))))
          y)
        
        (coll? x) (into-array BigInteger (map nn/to-BigInteger x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-BigDecimal ^"[Ljava.math.BigDecimal;" [x]
  (cond (array-of-BigDecimal? x) x
        
        (number? x) (make-array BigDecimal (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array BigDecimal (int n))]
          (dotimes [i n] (aset y i (nn/to-BigDecimal (aget x i))))
          y)
        
        (coll? x) (into-array BigDecimal (map nn/to-BigDecimal x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-BigFraction 
  ^"[Lorg.apache.commons.math3.fraction.BigFraction;" [x]
  (cond (array-of-BigFraction? x) x
        
        (number? x) (make-array BigFraction (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array BigFraction (int n))]
          (dotimes [i n] (aset y i (nn/to-BigFraction (aget x i))))
          y)
        
        (coll? x) (into-array BigFraction (map nn/to-BigFraction x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Ratio ^"[Lclojure.lang.Ratio;" [x]
  (cond (array-of-Ratio? x) x
        
        (number? x) (make-array Ratio (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Ratio (int n))]
          (dotimes [i n] (aset y i (nn/to-Ratio (aget x i))))
          y)
        
        (coll? x) (into-array Ratio (map nn/to-Ratio x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Number ^"[Ljava.lang.Number;" [x]
  (cond (array-of-Number? x) x
        
        (number? x) (make-array Number (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Number (int n))]
          (dotimes [i n] (aset y i (num (aget x i))))
          y)
        
        (coll? x) (into-array Number (map num x))
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn array-of-Object ^"[Ljava.lang.Object;" [x]
  (cond (array-of-Object? x) x
        
        (number? x) (make-array Object (int x))
        
        (double-array? x) 
        (let [x (doubles x)
              n (alength x)
              ^objects y (make-array Object (int n))]
          (dotimes [i n] (aset y i (num (aget x i))))
          y)
        
        (coll? x) (into-array Object x)
        
        :else
        (throw (UnsupportedOperationException. 
                 (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
