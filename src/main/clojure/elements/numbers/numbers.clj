(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.numbers.numbers
  
  {:doc "Utilities for numbers."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-07"
   :version "2017-04-12"}
  
  (:import [java.math BigDecimal BigInteger]
           [org.apache.commons.math3.fraction BigFraction]
           [clojure.lang Numbers Ratio]))
;;----------------------------------------------------------------
;; coercions
;;----------------------------------------------------------------
(defn to-Byte ^Byte [^Number x] 
  (cond (instance? Byte x) x
        :else (Byte/valueOf (.byteValue x))))
;;----------------------------------------------------------------
(defn to-Double ^Double [^Number x] 
  (cond (instance? Double x) x
        :else (Double/valueOf (.doubleValue x))))
;;----------------------------------------------------------------
(defn to-Float ^Float [^Number x] 
  (cond (instance? Float x) x
        :else (Float/valueOf (.floatValue x))))
;;----------------------------------------------------------------
(defn to-Integer ^Integer [^Number x] 
  (cond (instance? Integer x) x
        :else (Integer/valueOf (.intValue x))))
;;----------------------------------------------------------------
(defn to-Long ^Long [^Number x] 
  (cond (instance? Long x) x
        :else (Long/valueOf (.longValue x))))
;;----------------------------------------------------------------
(defn to-Short ^Short [^Number x] 
  (cond (instance? Long x) x
        :else (Short/valueOf (.shortValue x))))
;;----------------------------------------------------------------
(defn to-BigInteger ^java.math.BigInteger [^Number x] 
  (cond (instance? BigInteger x) x
        :else (BigInteger/valueOf (.longValue x))))
;;----------------------------------------------------------------
;; preserve exact double value using BigDecimal. rather than
;; BigDecimal/valueOf, which give the value of the String rep

(defn to-BigDecimal ^java.math.BigDecimal [x] 
  (cond (instance? BigDecimal x) x
        (instance? BigInteger x) (BigDecimal. ^BigInteger x)
        (instance? BigFraction x) (.bigDecimalValue ^BigFraction x)
        (instance? Ratio x) (.decimalValue ^Ratio x)
        (instance? Double x) (BigDecimal. (.doubleValue ^Double x))
        (instance? Float x) (BigDecimal. (.doubleValue ^Float x))
        :else (BigDecimal. (BigInteger/valueOf (.longValue ^Number x)))))
;;----------------------------------------------------------------
(defn to-BigFraction 
  ^org.apache.commons.math3.fraction.BigFraction [x]
  (cond (instance? BigFraction x) x
        (instance? Ratio x) (BigFraction. 
                              (.numerator ^Ratio x)
                              (.denominator ^Ratio x))
        (instance? BigInteger x) (BigFraction. ^BigInteger x)
        (instance? BigDecimal x) 
        (let [^BigDecimal x x
              scale (int (.scale x))]
          (if (>= scale (int 0))
            (BigFraction. (.unscaledValue x) 
                          (.pow BigInteger/TEN scale))
            (BigFraction. (.multiply 
                            (.unscaledValue x) 
                            (.pow BigInteger/TEN (- scale))))))
        (instance? Double x) (BigFraction. 
                               (.doubleValue ^Double x))
        (instance? Float x) (BigFraction. 
                              (.doubleValue ^Float x))
        (instance? Long x) (BigFraction. 
                             (.longValue ^Long x))
        (instance? Integer x) (BigFraction. 
                                (.intValue ^Long x))
        :else (throw
                (UnsupportedOperationException. 
                  (.getSimpleName (class x))))))
;;----------------------------------------------------------------
(defn to-Ratio ^clojure.lang.Ratio [x]
  (cond (instance? Ratio x) x
        (instance? BigFraction x) (Ratio. 
                                    (.getNumerator ^BigFraction x)
                                    (.getDenominator ^BigFraction x))
        (instance? BigInteger x) (Numbers/toRatio x)
        (instance? BigDecimal x) (Numbers/toRatio x)
        :else (Numbers/toRatio (to-BigDecimal x))))
;;----------------------------------------------------------------
