(set! *warn-on-reflection* true) 
(set! *unchecked-math* false)
(require 'potemkin)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns elements.api
  
  {:doc "Public API for elements."
   :author "mcdonald dot john dot alan at gmail dot com"
   :since "2017-04-06"
   :version "2017-05-29"}
  
  (:require [elements.utils]
            [elements.numbers.arrays]
            [elements.numbers.numbers]
            [elements.numbers.prng]
            [elements.numbers.sum]
            [elements.sets.multi]
            [elements.sets.protocol]))
;;----------------------------------------------------------------
(potemkin/import-vars 
  [elements.utils
   fn-name
   element-type
   release
   write-tsv])
;;----------------------------------------------------------------
(potemkin/import-vars 
  [elements.sets.multi
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
  [elements.numbers.arrays
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
   
  [elements.numbers.numbers 
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
   
  [elements.numbers.prng
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
(potemkin/import-fn elements.numbers.sum/naive-accumulator
                    sum-naive-accumulator)
(potemkin/import-fn elements.numbers.sum/kahan-accumulator
                    sum-kahan-accumulator)
(potemkin/import-fn elements.numbers.sum/big-decimal
                    sum-big-decimal)
(potemkin/import-fn elements.numbers.sum/big-fraction
                    sum-big-fraction)
(potemkin/import-fn elements.numbers.sum/ratio
                    sum-ratio)
(potemkin/import-fn elements.numbers.sum/aprational
                    sum-aprational)
(potemkin/import-fn elements.numbers.sum/zhu-hayes-branch
                    sum-zhu-hayes-branch)
(potemkin/import-fn elements.numbers.sum/zhu-hayes-nobranch
                    sum-zhu-hayes-nobranch)
;;----------------------------------------------------------------
(potemkin/import-fn elements.numbers.sum/reduce+ sum-reduce)
(potemkin/import-fn elements.numbers.sum/areduce+ sum-areduce)
(potemkin/import-fn elements.numbers.sum/loop+ sum-loop)

(potemkin/import-fn elements.numbers.sum/doubleAccumulator
                    sum-DoubleAccumulator)
(potemkin/import-fn elements.numbers.sum/doubleAdder
                    sum-DoubleAdder)
(potemkin/import-fn elements.numbers.sum/doubleSummaryStatistics
                    sum-DoubleSummaryStatistics)
(potemkin/import-fn elements.numbers.sum/stats
                    sum-Stats)
(potemkin/import-fn elements.numbers.sum/descriptiveStatistics
                    sum-DescriptiveStatistics)
(potemkin/import-fn elements.numbers.sum/math3Sum
                    sum-math3)
(potemkin/import-fn elements.numbers.sum/summaryStatistics
                    sum-SummaryStatistics)
(potemkin/import-fn elements.numbers.sum/synchronizedSummaryStatistics
                    sum-SynchronizedSummaryStatistics)

(potemkin/import-fn elements.numbers.sum/ifast sum-ifast)
(potemkin/import-fn elements.numbers.sum/zhu-hayes-exact 
                    sum-zhu-hayes-exact)
(potemkin/import-fn elements.numbers.sum/naive sum-naive)
(potemkin/import-fn elements.numbers.sum/pairwise sum-pairwise)
(potemkin/import-fn elements.numbers.sum/kahan sum-kahan)
;;----------------------------------------------------------------
