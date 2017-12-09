(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.test.numbers.numbers
  
  {:doc "Unit tests for palisades.lakes.elements.numbers.numbers"
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-11"
   :version "2017-04-20"}
  
  (:require [clojure.test :as test]
            [palisades.lakes.elements.api :as mc])
  (:import [java.math BigDecimal BigInteger]
           [org.apache.commons.math3.fraction BigFraction]
           [clojure.lang Numbers Ratio]))
;; mvn clojure:test -Dtest=palisades.lakes.elements.test.numbers.numbers
;;----------------------------------------------------------------
(def integers [1 2 3 4])
(def dfloats [1.0 
              (/ 1.0 2.0)
              (/ 1.0 3.0)
              (/ 1.0 4.0)
              (/ 1.0 Math/PI)
              (/ 1.0 Math/E)
              Math/PI
              Math/E])
(def decimals [(BigDecimal. 1.0) 
               (BigDecimal. (/ 1.0 2.0))
               (BigDecimal. (/ 1.0 3.0)) 
               (BigDecimal. (/ 1.0 4.0))])
(def decimals (mapv mc/to-BigDecimal dfloats))
(def ratios [(Ratio. (BigInteger/valueOf (long 1)) 
                     (BigInteger/valueOf (long 1)))
             (Ratio. (BigInteger/valueOf (long 1)) 
                     (BigInteger/valueOf (long 2)))
             (Ratio. (BigInteger/valueOf (long 1)) 
                     (BigInteger/valueOf (long 3)))
             (Ratio. (BigInteger/valueOf (long 1)) 
                     (BigInteger/valueOf (long 4)))])
(def fractions [(BigFraction. (BigInteger/valueOf (long 1)) 
                              (BigInteger/valueOf (long 1)))
                (BigFraction. (BigInteger/valueOf (long 1)) 
                              (BigInteger/valueOf (long 2)))
                (BigFraction. (BigInteger/valueOf (long 1)) 
                              (BigInteger/valueOf (long 3)))
                (BigFraction. (BigInteger/valueOf (long 1)) 
                              (BigInteger/valueOf (long 4)))])
;;----------------------------------------------------------------
(test/deftest exacts
  (doseq [^double x dfloats]
    (test/is (== x (.doubleValue (mc/to-BigDecimal x)))))
  (doseq [^double x dfloats]
    (test/is (== x (.doubleValue (mc/to-BigDecimal x)))))
  (doseq [^double x dfloats]
    (let [x (double x)]
      #_(println x (Math/ulp x) (- x (double (rationalize x))))
      (test/is (<= (Math/abs (- x (double (mc/to-Double (mc/to-BigFraction x)))))
                   (Math/ulp x)))))
  ;; fails because (double (rationalize (double _))) isn't identity
  (doseq [^double x dfloats]
    (let [x (double x)]
      #_(println 
         x
        (.doubleValue (Double/valueOf x))
        (.doubleValue (mc/to-BigDecimal x))
        (.doubleValue (mc/to-BigFraction x)))
      (test/is (<= (Math/abs (- x (double (mc/to-BigFraction x))))
                   (Math/ulp x)))
      (test/is (== x
                   (.doubleValue (Double/valueOf x))
                   (.doubleValue (mc/to-BigDecimal x))
                   (.doubleValue (mc/to-BigFraction x))))))
  ;; fails because (double (rationalize (double _))) isn't identity
  #_(doseq [^double x dfloats]
      (let [x (double x)]
        (test/is (<= (Math/abs (- x (double (rationalize x))))
                     (Math/ulp x)))
        (test/is (== x
                     (double (rationalize x))
                     (.doubleValue (Double/valueOf x))
                     (.doubleValue (mc/to-BigDecimal x))
                     (.doubleValue (mc/to-Ratio x))
                     (.doubleValue ^Number (rationalize x))))))
  (doseq [x decimals]
    (test/is (== x (mc/to-BigDecimal (mc/to-Ratio x)))))
  ;; fails because not all ratios can be converted to BigDecimal
  #_(doseq [x ratios]
      (test/is (== x (rationalize (mc/to-BigDecimal x))))))
;;----------------------------------------------------------------
