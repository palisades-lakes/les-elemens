;;(set! *warn-on-reflection* true)
;;(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.scripts.numbers.bigdecimal
  
  {:doc "Test limits of Big Decimal."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-05-18"
   :version "2017-05-24"}
  
  (:require [elements.api :as mc])
  (:import [java.math BigDecimal BigInteger]
           [org.apache.commons.math3.fraction BigFraction]))
;;----------------------------------------------------------------
(def ^BigDecimal d0 (BigDecimal. (BigInteger/valueOf Long/MAX_VALUE) Integer/MAX_VALUE))
(println d0)
(println (.multiply d0 d0))
(def ^BigDecimal d1 (BigDecimal. (BigInteger/valueOf Long/MAX_VALUE) Integer/MIN_VALUE))
(println d1)
(println (.multiply d1 d1))
;;----------------------------------------------------------------
(def f1 (.toBigIntegerExact d1))