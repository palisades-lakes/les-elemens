(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.numbers.sum
  
  {:doc "Wrapper for elements.java.numbers.sum.Kahan, etc."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-07"
   :version "2017-11-07"}
  
  (:require [elements.numbers.arrays :as a])
  
  (:import [java.math BigDecimal]
           [org.apache.commons.math3.fraction BigFraction]
           [clojure.lang Numbers Ratio]
           [elements.java.numbers.sum IFastSum Kahan Naive Pairwise 
            Summers ZhuHayesOnlineExactSum]
           [elements.java.accumulate.sum AprationalSum BigDecimalSum
            BigFractionSum KahanSum NaiveSum RatioSum 
            ZhuHayesOnlineExactBranch 
            ZhuHayesOnlineExactNoBranch]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false) ;
(set! *unchecked-math* false)
(require '[clatrix.core :as clatrix])
(require '[uncomplicate.neanderthal.internal.device.clblock 
           :as clblock])
(import '[uncomplicate.neanderthal.internal.device.clblock 
          CLBlockVector])
(require '[uncomplicate.neanderthal
           [core :as unc] 
           [native :as unn]
           [opencl :as uno]])
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
;; accumulators/online
;;----------------------------------------------------------------
(defn naive-accumulator ^double [x]
  (cond (a/double-array? x) 
        (NaiveSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn kahan-accumulator ^double [x]
  (cond (a/double-array? x) 
        (KahanSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn zhu-hayes-branch ^double [x]
  (cond (a/double-array? x) 
        (ZhuHayesOnlineExactBranch/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn zhu-hayes-nobranch ^double [x]
  (cond (a/double-array? x) 
        (ZhuHayesOnlineExactBranch/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn big-decimal ^double [x]
  (cond (a/double-array? x) 
        (BigDecimalSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn big-fraction ^double [x]
  (cond (a/double-array? x) 
        (BigFractionSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn ratio ^double [x]
  (cond (a/double-array? x) 
        (RatioSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn aprational ^double [x]
  (cond (a/double-array? x) 
        (AprationalSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
;; offline/ iterate/recursive over all the adta
;;----------------------------------------------------------------
(defn reduce+ ^double [x] (reduce + (seq x)))
;;----------------------------------------------------------------
(defn areduce+ ^double [^doubles x] 
  (areduce x xi s (double 0.0) (+ s xi)))
;;----------------------------------------------------------------
(defn loop+ ^double [^doubles x] 
  (let [n (int (alength x))]
    (loop [s (double 0.0)
           i (int 0)]
      (if (< i n) 
        (recur (+ s (aget x i)) (inc i))
        s))))
;;----------------------------------------------------------------
(defn doubleAccumulator ^double [^doubles z] 
  (Summers/doubleAccumulator z))
;;----------------------------------------------------------------
(defn doubleAdder ^double [^doubles z] 
  (Summers/doubleAdder z))
;;----------------------------------------------------------------
(defn doubleSummaryStatistics ^double [^doubles z] 
  (Summers/doubleSummaryStatistics z))
;;----------------------------------------------------------------
(defn stats ^double [^doubles z] 
  (Summers/stats z))
;;----------------------------------------------------------------
(defn statsAccumulator ^double [^doubles z] 
  (Summers/statsAccumulator z))
;;----------------------------------------------------------------
(defn descriptiveStatistics ^double [^doubles z] 
  (Summers/descriptiveStatistics z))
;;----------------------------------------------------------------
(defn statUtils ^double [^doubles z] 
  (Summers/statUtils z))
;;----------------------------------------------------------------
(defn math3Sum ^double [^doubles z] 
  (Summers/math3Sum z))
;;----------------------------------------------------------------
(defn summaryStatistics ^double [^doubles z] 
  (Summers/summaryStatistics z))
;;----------------------------------------------------------------
(defn synchronizedSummaryStatistics ^double [^doubles z] 
  (Summers/synchronizedSummaryStatistics z))
;;----------------------------------------------------------------
(defn ifast ^double [x]
  (cond (a/double-array? x) 
        (IFastSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn zhu-hayes-exact ^double [x]
  (cond (a/double-array? x) 
        (ZhuHayesOnlineExactSum/sum (doubles x))
        :else
        (throw 
          (UnsupportedOperationException. 
            (print-str "can't handle" (class x))))))
;;----------------------------------------------------------------
(defn naive 
  
  (^double [x ^long start ^long end]
    (cond (a/double-array? x) 
          (Naive/sum (doubles x) (int start) (int end))
          (a/float-array? x) 
          (Naive/sum (floats x) (int start) (int end))
          (a/byte-array? x) 
          (Naive/sum (bytes x) (int start) (int end))
          (a/short-array? x) 
          (Naive/sum (shorts x) (int start) (int end))
          (a/int-array? x) 
          (Naive/sum (ints x) (int start) (int end))
          (a/long-array? x) 
          (Naive/sum (longs x) (int start) (int end))
          (a/array-of-Double? x) 
          (Naive/sum (a/array-of-Double x) (int start) (int end))
          (a/array-of-Float? x) 
          (Naive/sum (a/array-of-Float x) (int start) (int end))
          (a/array-of-Byte? x) 
          (Naive/sum (a/array-of-Byte x) (int start) (int end))
          (a/array-of-Short? x) 
          (Naive/sum (a/array-of-Short x) (int start) (int end))
          (a/array-of-Integer? x) 
          (Naive/sum (a/array-of-Integer x) (int start) (int end))
          (a/array-of-Long? x) 
          (Naive/sum (a/array-of-Long x) (int start) (int end))
          (a/array-of-BigInteger? x) 
          (Naive/sum (a/array-of-BigInteger x) (int start) (int end))
          (a/array-of-BigDecimal? x) 
          (Naive/sum (a/array-of-BigDecimal x) (int start) (int end))
          (a/array-of-BigFraction? x) 
          (Naive/sum (a/array-of-BigFraction x) (int start) (int end))
          (a/array-of-Ratio? x) 
          (Naive/sum (a/array-of-Ratio x) (int start) (int end))
          (a/array-of-Number? x) 
          (Naive/sum (a/array-of-Number x) (int start) (int end))
          (a/array-of-Object? x) 
          (Naive/sum (a/array-of-Object x) (int start) (int end))
          
          (instance? clojure.lang.LazySeq x) 
          (throw (UnsupportedOperationException. 
                   (print-str "can't handle" (class x))))
          
          (instance? java.util.List x) 
          (Naive/sum ^java.util.List x (int start) (int end))
          
          :else
          (throw 
            (UnsupportedOperationException. 
              (print-str "can't handle" (class x))))))
  
  (^double [x]
    (cond
      (instance? clatrix.core.Vector x) (clatrix/sum x)
      (instance? uncomplicate.neanderthal.internal.host.buffer_block.RealBlockVector x) (unc/sum x)
      (instance? CLBlockVector x) (unc/sum x)
      (instance? clojure.lang.LazySeq x) 
      (Naive/sum ^clojure.lang.ISeq x)
      :else 
      (naive x (int 0) (int (a/size x))))))
;;----------------------------------------------------------------
(defn pairwise 
  
  (^double [x ^long start ^long end]
    (cond (a/double-array? x) 
          (Pairwise/sum (doubles x) (int start) (int end))
          (a/float-array? x) 
          (Pairwise/sum (floats x) (int start) (int end))
          (a/byte-array? x) 
          (Pairwise/sum (bytes x) (int start) (int end))
          (a/short-array? x) 
          (Pairwise/sum (shorts x) (int start) (int end))
          (a/int-array? x) 
          (Pairwise/sum (ints x) (int start) (int end))
          (a/long-array? x) 
          (Pairwise/sum (longs x) (int start) (int end))
          (a/array-of-Double? x) 
          (Pairwise/sum (a/array-of-Double x) (int start) (int end))
          (a/array-of-Float? x) 
          (Pairwise/sum (a/array-of-Float x) (int start) (int end))
          (a/array-of-Byte? x) 
          (Pairwise/sum (a/array-of-Byte x) (int start) (int end))
          (a/array-of-Short? x) 
          (Pairwise/sum (a/array-of-Short x) (int start) (int end))
          (a/array-of-Integer? x) 
          (Pairwise/sum (a/array-of-Integer x) (int start) (int end))
          (a/array-of-Long? x) 
          (Pairwise/sum (a/array-of-Long x) (int start) (int end))
          (a/array-of-BigInteger? x) 
          (Pairwise/sum (a/array-of-BigInteger x) (int start) (int end))
          (a/array-of-BigDecimal? x) 
          (Pairwise/sum (a/array-of-BigDecimal x) (int start) (int end))
          (a/array-of-BigFraction? x) 
          (Pairwise/sum (a/array-of-BigFraction x) (int start) (int end))
          (a/array-of-Ratio? x) 
          (Pairwise/sum (a/array-of-Ratio x) (int start) (int end))
          (a/array-of-Number? x) 
          (Pairwise/sum (a/array-of-Number x) (int start) (int end))
          (a/array-of-Object? x) 
          (Pairwise/sum (a/array-of-Object x) (int start) (int end))
          
          (instance? clojure.lang.LazySeq x) 
          (throw 
            (UnsupportedOperationException. 
              (print-str "can't handle" (class x))))
          
          (instance? java.util.List x) 
          (Pairwise/sum ^java.util.List x (int start) (int end))
          
          :else
          (throw (UnsupportedOperationException. 
                   (print-str "can't handle" (class x))))))
  
  (^double [x] (pairwise x (int 0) (int (a/size x)))))
;;----------------------------------------------------------------
(defn kahan 
  
  (^double [x ^long start ^long end]
    (cond (a/double-array? x) 
          (Kahan/sum (doubles x) (int start) (int end))
          (a/float-array? x) 
          (Kahan/sum (floats x) (int start) (int end))
          (a/array-of-Double? x) 
          (Kahan/sum (a/array-of-Double x) (int start) (int end))
          (a/array-of-Float? x) 
          (Kahan/sum (a/array-of-Float x) (int start) (int end))
          :else
          (throw 
            (UnsupportedOperationException. 
              (print-str "can't handle" (class x))))))
  
  (^double [x] (kahan x (int 0) (int (a/size x)))))
;;----------------------------------------------------------------
