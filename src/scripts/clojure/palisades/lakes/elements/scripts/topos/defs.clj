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
• Adámek et al: [2]
1. category
2. functor
3. duality
4. monepisic
5. categories of categories
6. small category
7. quasicategory
8. subcategory
9. concrete category
10. functor
11. natural transformation
12. injective object
13. source/sink
14. (co)limit
15. (co)completeness
16. pullback/pushout
17. adjoint functor
18. monad
19. cartesian closed category
• Asperti and Longo: [3]
1. category
2. diagram
3. monepisic
4. subobject
5. initial/terminal object
6. (co)product
17
Key ideas in category/topos theory
7. exponential
8. equalizer
9. pullback
10. partial morphism
11. complete object
12. subobject classifier
13. topos
14. functor
15. natural transformation
16. cartesian closed category
17. Yoneda
18. presheaf
19. monoidal closed category
20. monad
21. linear logic
22. universal arrow
23. adjunction
24. monad
25. (co)limit
26. indexed category
• Awodey: [4]
1. category
2. isomorphism
3. free category
4. monepisic
5. initial/terminal objects
6. generalized elements
7. products
8. hom-sets
9. finitely presented category
10. subobject
11. pullback
12. (co)limit
13. exponential
14. cartesian closed
15. Category category
16. natural transformation
17. monoidal category
18
Key ideas in category/topos theory
18. equivalence
19. Yoneda
20. topos
21. adjoint
22. monad
• Barr-Wells: [6]
1. category
2. monepisic
3. diagram
4. natural transformation
5. Yoneda
6. (linear) sketch
7. 2-category
8. product/sum
9. cartesian closed category
10. (co)limit
11. equalizer
12. pullback
13. (co)cone
14. fibration
15. wreath product
16. adjoint
17. Scott domain
18. topos
19. (pre)sheave
20. monoidal category
• Geroch:[21]
1. category
2. monepisic
3. commuting diagram
4. (co)product
5. functor
6. free construction
7. homology functor
• Goldblatt: [22]
1. category
2. composition
3. monepisic arrows
19
Key ideas in category/topos theory
4. initial/terminal objects
5. duality
6. (co)products
7. (co)equalizers
8. (co)limits
9. pullback/pushout
10. completeness
11. exponentiation
12. subobjects
13. classifiers
14. topos
15. bundles/sheaves
16. monoid action
17. power object
• Hillman: [27]
1. category
2. diagram, commute
3. subcategory
4. skeleton
5. monepisic
6. duality
7. subobject
8. initial/final object
9. element
10. (co)product
11. universal mapping property
12. (co)equalizer
13. pullback/pushout
14. (co)cone
15. (co)limit
16. functor
17. natural transformation
18. slice category
19. Yoneda
20. adjoint
21. exponential
22. topos
• Lawvere and Schanuel: [32]
20
Key ideas in category/topos theory
1. category
2. functor
3. subcategory
4. monepisic
5. idempotent
6. universal mapping property
7. terminal/ initial object
8. (co)products
9. points of an object
10. distributive and linear category
11. universal construction
12. map/arrow object
13. exponentiation
14. contravariant parts functor
15. subobject
16. topos
17. (co)discrete object
18. monoid
• Leinster: [33]
1. category
2. duality
3. (contra/co-variant) functor
4. presheaf
5. faithful functor
6. subcategory
7. natural transformation, isomorphism
8. equivalence
9. adjoint
10. initial/terminal object
11. (co)unit
12. representable
13. (co)limit
14. (co)product
15. pullback/pushout
16. equalizer
17. diagram
18. (co)cone
19. monepisic
21
Key ideas in category/topos theory
20. cartesian closed category
• MacLane: [34]
1. category
2. functor
3. natural transformation
4. monepisic
5. zero
6. hom-set
7. duality
8. contravariance
9. comma category
10. free category
11. universal arrow
12. Yoneda
13. (co)product
14. (co)limit
15. representable functor
16. adjoint, adjoint functor
17. cartesian closed category
18. monad
19. split (co)equalizer
20. monoidal category
21. action
22. loop and suspension
23. (co)kernel
24. additive category
25. abelian category
26. (co)end
27. Kan extension
28. nerve
29. bicategory
• Perrone: [39]
1. category
2. monepisic
3. functor
4. natural transformation
5. universal property
6. Yoneda
22
Key ideas in category/topos theory
7. (co)limit
8. adjunction
9. (co)unit
10. universal arrow
11. adjoint functor
12. (co)monad
• Riehl: [41]
1. category
2. functor
3. natural transformation
4. universal property
5. representability
6. Yoneda
7. (co)limit
8. adjunction
9. monad
10. Kan extension
• D. Spivak: [44]
1. category
2. diagram
3. olog
4. (co)product
5. (co)limit
6. category
7. functor
8. natural transformation
9. adjoint functor
10. monad
11. operad
• vanOosten:[47]
1. category
2. functor
3. monepisic
4. initial/terminal object
5. natural transformation
6. Yoneda
7. category equivalence
8. (co)cone
23
Key ideas in category/topos theory
9. (co)limit
10. equalizer
11. pullback/pushout
12. (co)product
13. regular category
14. subobject
15. adjunction
16. adjoint functor
17. (co)completeness
18. monad
19. cartesian closed category
Things
;;----------------------------------------------------------------
