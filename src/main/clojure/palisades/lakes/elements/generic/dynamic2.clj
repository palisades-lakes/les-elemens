(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.generic.dynamic2
  
  {:doc "Fork of defmulti/defmethod for performance experiments."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-06-02"
   :version "2017-06-06"}
  (:refer-clojure :exclude [defmethod defmulti get-method methods 
                            prefer-method prefers
                            remove-all-methods remove-method]))
;;----------------------------------------------------------------

(defmacro defdynafn2

  "Creates a new dynamic function.
  The docstring and attr-map are optional.
  Options are key-value pairs"

  {:arglists '([name docstring? attr-map? & options])
   :added "1.0"}

  [mm-name & options]
  (let [docstring   (if (string? (first options))
                      (first options)
                      nil)
        options     (if (string? (first options))
                      (next options)
                      options)
        m           (if (map? (first options))
                      (first options)
                      {})
        options     (if (map? (first options))
                      (next options)
                      options)
        m           (if docstring
                      (assoc m :doc docstring)
                      m)
        m           (if (meta mm-name)
                      (conj (meta mm-name) m)
                      m)]
    (let [options (apply hash-map options)]
      #_(check-valid-options options :default :hierarchy)
      `(let [v# (def ~mm-name)]
         (when-not (and (.hasRoot v#) 
                        (instance? palisades.lakes.elements.java.generic.DynamicFn2 
                                   (deref v#)))
           (def ~(with-meta mm-name m)
                (palisades.lakes.elements.java.generic.DynamicFn2. 
                  ~(name mm-name))))))))

;;----------------------------------------------------------------
;; TODO: better method name

(defmacro defmethod
  "Creates and installs a new method of the dynamic function 
   with the given signature."
  {:added "1.0"}
  ([dynafn [c0 c1] & fn-tail]
  `(.addMethod 
     ~(with-meta dynafn {:tag 'palisades.lakes.elements.java.generic.DynamicFn2}) 
     ~(with-meta c0 {:tag 'Class})
     ~(with-meta c1 {:tag 'Class})
     (fn ~dynafn ~@fn-tail))))

#_(defn remove-all-methods
  "Removes all of the methods of the dynamic function."
  {:added "1.2"
   :static true} 
 [^palisades.lakes.elements.java.generic.DynamicFn2 dynafn]
 (.reset dynafn))

(defn remove-method
  "Removes the method of the dynamic function with the 2 class
   signature."
  {:added "1.0"
   :static true}
 [^palisades.lakes.elements.java.generic.DynamicFn2 dynafn ^Class c0 ^Class c1] 
 (.removeMethod dynafn c0 c1))

(defn prefer-method
  "Causes the dynamic function to prefer matches of 
   <code>c00 c01</code> over <code>c10 c11</code>."
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.DynamicFn2 dynafn 
   ^Class c00 ^Class c01  
   ^Class c10 ^Class c11]
  (.preferMethod dynafn c00 c01 c10 c11))

;; TODO: return something more useful in the general case
#_(defn methods
  "Given a dynamic function, returns a map of 
   dispatch values -> dispatch fns"
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.DynamicFn2 dynafn] 
   (.getMethodTable dynafn))

(defn get-method
  "Given a dynamic function and a dispatch value, returns the 
   matching method, if any."
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.DynamicFn2 dynafn ^Class c0 ^Class c1] 
  (.getMethod dynafn c0 c1))

;; TODO: return something more useful in the general case
#_(defn prefers
  "Given a dynamic function, returns a map of 
   preferred value -> set of other values"
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.DynamicFn2 dynafn] 
   (.getPreferTable dynafn))
;;----------------------------------------------------------------
