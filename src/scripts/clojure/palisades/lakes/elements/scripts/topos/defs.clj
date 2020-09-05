(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.topos.topics
  
  {:doc "Topics in category and topos theory, dependencies."
   :author "palisades dot lakes at gmail dot com"
   :version "2020-09-05"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))
;;----------------------------------------------------------------
;; topic and list of rank in various cat theory intros:

(def topics 
  {:adamek
   [:category
    :functor
    :duality
    :monepisic
    :categories_of_categories
    :small_category
    :quasicategory
    :subcategory
    :concrete_category
    :functor
    :natural_transformation
    :injective_object
    :source_sink
    :co_limit
    :co_completeness
    :pullback_pushout
    :adjoint_functor
    :monad
    :cartesian_closed_category]
   })

;;----------------------------------------------------------------
