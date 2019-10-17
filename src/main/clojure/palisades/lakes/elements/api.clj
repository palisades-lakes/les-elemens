(set! *warn-on-reflection* true) 
(set! *unchecked-math* false)
(require 'potemkin)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.api
  
  {:doc "Public API for palisades.lakes.elements."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-04-06"
   :version "2017-05-29"}
  
  (:require [palisades.lakes.elements.utils]
            [palisades.lakes.elements.numbers.arrays]
            [palisades.lakes.elements.numbers.numbers]
            [palisades.lakes.elements.numbers.prng]
            [palisades.lakes.elements.numbers.sum]
            [palisades.lakes.elements.sets.multi]
            [palisades.lakes.elements.sets.protocol]))
;;----------------------------------------------------------------
(potemkin/import-vars 
  [palisades.lakes.elements.utils
   fn-name
   element-type
   write-tsv])
;;----------------------------------------------------------------
(potemkin/import-vars 
  [palisades.lakes.elements.sets.multi
   element?
   subset?
   superset?
   intersection
   intersects?
   union
   difference
   cardinality])
;;----------------------------------------------------------------
(potemkin/import-vars 
  [palisades.lakes.elements.numbers.arrays
   array?
   size 
   array-of-BigFraction?
   array-of-BigFraction
   array-of-BigDecimal?
   array-of-BigDecimal
   array-of-BigInteger?
   array-of-BigInteger
   array-of-Double? 
   array-of-Double 
   array-of-Float? 
   array-of-Float
   array-of-Byte? 
   array-of-Byte 
   array-of-Short? 
   array-of-Short
   array-of-Integer? 
   array-of-Integer
   array-of-Long? 
   array-of-Long
   double-array?
   float-array? 
   byte-array? 
   short-array?
   int-array? 
   long-array?
   array-of-Number? 
   array-of-Number
   array-of-Object? 
   array-of-Object
   array-of-Ratio?
   array-of-Ratio]
   
  [palisades.lakes.elements.numbers.numbers 
   to-BigFraction
   to-BigDecimal
   to-BigInteger
   to-Double 
   to-Float 
   to-Byte
   to-Short 
   to-Byte
   to-Integer
   to-Long
   to-Ratio]
   
  [palisades.lakes.elements.numbers.prng
   generate-default-seed
   generate-randomdotorg-seed
   mersenne-twister
   gaussian-generator
   gaussians
   read-seed
   random-doubles-nonnegative
   random-doubles
   random-doubles-minus-mean
   random-doubles-zero-sum
   uniform-generator
   uniform-doubles
   uniform-ints
   well44497b
   write-seed])
;;----------------------------------------------------------------
(potemkin/import-fn palisades.lakes.elements.numbers.sum/naive-accumulator
                    sum-naive-accumulator)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/kahan-accumulator
                    sum-kahan-accumulator)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/big-decimal
                    sum-big-decimal)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/big-fraction
                    sum-big-fraction)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/ratio
                    sum-ratio)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/aprational
                    sum-aprational)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/zhu-hayes-branch
                    sum-zhu-hayes-branch)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/zhu-hayes-nobranch
                    sum-zhu-hayes-nobranch)
;;----------------------------------------------------------------
(potemkin/import-fn palisades.lakes.elements.numbers.sum/reduce+ sum-reduce)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/areduce+ sum-areduce)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/loop+ sum-loop)

(potemkin/import-fn palisades.lakes.elements.numbers.sum/doubleAccumulator
                    sum-DoubleAccumulator)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/doubleAdder
                    sum-DoubleAdder)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/doubleSummaryStatistics
                    sum-DoubleSummaryStatistics)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/stats
                    sum-Stats)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/descriptiveStatistics
                    sum-DescriptiveStatistics)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/math3Sum
                    sum-math3)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/summaryStatistics
                    sum-SummaryStatistics)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/synchronizedSummaryStatistics
                    sum-SynchronizedSummaryStatistics)

(potemkin/import-fn palisades.lakes.elements.numbers.sum/ifast sum-ifast)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/zhu-hayes-exact 
                    sum-zhu-hayes-exact)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/naive sum-naive)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/pairwise sum-pairwise)
(potemkin/import-fn palisades.lakes.elements.numbers.sum/kahan sum-kahan)
;;----------------------------------------------------------------
