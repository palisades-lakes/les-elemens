(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.scripts.topos.topics
  
  {:doc "Topics in category and topos theory, dependencies."
   :author "palisades dot lakes at gmail dot com"
   :version "2020-09-07"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]
            [clojure.pprint :as pp]))
;; clj src\scripts\clojure\palisades\lakes\elements\scripts\topos\topics.clj
;;----------------------------------------------------------------
;; topic and list of rank in various cat theory intros:

(def -topics-
  {:Adámek-et-al
   [:category
    :functor
    :duality
    :monepisic
    :categories-of-categories
    :small-category
    :quasicategory
    :subcategory
    :concrete-category
    :functor
    :natural-transformation
    :injective-object
    :source-sink
    :co-limit
    :co-completeness
    :pullback-pushout
    :adjoint-functor
    :monad
    :cartesian-closed-category]
   :Asperti-and-Longo
   [:category
    :diagram
    :monepisic
    :subobject
    :initial-terminal-object
    :co-product
    :exponential
    :equalizer
    :pullback-pushout
    :partial-morphism
    :complete-object
    :subobject-classifier
    :topos
    :functor
    :natural-transformation
    :cartesian-closed-category
    :yoneda
    :presheaf
    :monoidal-closed-category
    :monad
    :linear-logic
    :universal-arrow
    :adjunction
    :monad
    :co-limit
    :indexed-category]
   :Awodey
   [:category
    :functor
    :free-category
    :monepisic
    :initial-terminal-object
    :generalized-element
    :co-product
    :hom-set
    :finitely-presented-category
    :subobject
    :pullback-pushout
    :co-limit
    :exponential
    :cartesian-closed-category
    :natural-transformation
    :monoidal-category
    :equivalence
    :yoneda
    :topos
    :adjoint
    :monad]
   :Barr-Wells
   [:category
    :monepisic
    :functor
    :action
    :equivalence
    :quotient-category
    :diagram
    :natural-transformation
    :yoneda
    :linear-sketch
    :2-category
    :product-sum
    :cartesian-closed-category
    :co-limit
    :equalizer
    :pullback-pushout
    :co-cone
    :fibration
    :wreath-product
    :adjoint
    :scott-domain
    :topos
    :presheaf
    :monoidal-category]
   :Geroch
   [:category
    :monepisic
    :diagram
    :commute
    :co-product
    :functor
    :free-construction
    :homology-functor]
   :Goldblatt
   [:category
    :composition
    :monepisic
    :initial-terminal-object
    :duality
    :co-product
    :co-equalizer
    :co-limit
    :pullback-pushout
    :completeness
    :exponential
    :subobject
    :classifier
    :topos
    :bundle
    :sheaf
    :monoid-action
    :power-object
    :functor
    :natural-transformation
    :adjunction]
   :Hillman
   [:category
    :diagram
    :commute
    :subcategory
    :skeleton
    :monepisic
    :duality
    :subobject
    :initial-terminal-object
    :element
    :co-product
    :universal-property
    :co-equalizer
    :pullback-pushout
    :co-cone
    :co-limit
    :functor
    :natural-transformation
    :slice-category
    :yoneda
    :adjoint
    :exponential
    :topos]
   :Lawvere-and-Schanuel
   [:category
    :functor
    :subcategory
    :monepisic
    :idempotent
    :universal-property
    :initial-terminal-object
    :co-product
    :points-of-an-object
    :distributive-and-linear-category
    :universal-construction
    :arrow-object
    :exponential
    :contravariant-parts-functor
    :subobject
    :topos
    :co-discrete-object
    :monoid]
   :Leinster
   [:category
    :duality
    :functor
    :presheaf
    :faithful-functor
    :subcategory
    :natural-transformation
    :isomorphism
    :equivalence
    :adjoint
    :initial-terminal-object
    :co-unit
    :representable
    :co-limit
    :co-product
    :pullback-pushout
    :equalizer
    :diagram
    :co-cone
    :monepisic
    :cartesian-closed-category]
   :MacLane
   [:category
    :functor
    :natural-transformation
    :monepisic
    :zero
    :hom-set
    :duality
    :contravariance
    :comma-category
    :free-category
    :universal-arrow
    :yoneda
    :co-product
    :co-limit
    :representable-functor
    :adjoint
    :adjoint-functor
    :cartesian-closed-category
    :monad
    :split-co-equalizer
    :monoidal-category
    :action
    :loop-and-suspension
    :co-kernel
    :additive-category
    :abelian-category
    :co-end
    :kan-extension
    :nerve
    :bicategory]
   :Perrone
   [:category
    :monepisic
    :functor
    :natural-transformation
    :universal-property
    :yoneda
    :co-limit
    :adjunction
    :co-unit
    :universal-arrow
    :adjoint-functor
    :co-monad]
   :Riehl
   [:category
    :functor
    :natural-transformation
    :universal-property
    :representable
    :yoneda
    :co-limit
    :adjunction
    :monad
    :kan-extension]
   :DSpivak
   [:category
    :diagram
    :olog
    :co-product
    :co-limit
    :functor
    :natural-transformation
    :adjoint-functor
    :monad
    :operad]
   :vanOosten
   [:category
    :functor
    :monepisic
    :initial-terminal-object
    :natural-transformation
    :yoneda
    :category-equivalence
    :co-cone
    :co-limit
    :equalizer
    :pullback-pushout
    :co-product
    :regular-category
    :subobject
    :adjunction
    :adjoint-functor
    :co-completeness
    :monad
    :cartesian-closed-category]})
;;----------------------------------------------------------------
(defn- inner [sums [topic weight]]
  (assoc! 
    sums 
    topic 
    (+ (double weight) (double (get sums topic 0.0)))))
(defn- outer [sums [source topics]] 
  (let [n (count topics)]
    (reduce 
      inner 
      sums 
      (zipmap topics (map #(/ (double %) (double n)) (range n 0 -1))))))
(defn importance [all-topics]
  (reverse
    (sort-by 
      (fn ranker [[topic weight]] weight) 
      (persistent!
        (reduce outer (transient {}) all-topics)))))
;;----------------------------------------------------------------
(defn summary [topics]
  (println "authors: " (count topics))
  (let [onion (sort (reduce into #{} (vals topics)))
        ranks (importance topics)]
    (println "topics: " (count onion))
    (println "topics: " (count ranks))
    ;;(pp/pprint onion)
    (pp/pprint ranks)))
;;----------------------------------------------------------------
(summary -topics-)
;;----------------------------------------------------------------
