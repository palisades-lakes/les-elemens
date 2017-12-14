(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.generic.multi
  
  {:doc "Fork of defmulti/defmethod for performance experiments."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-06-02"
   :version "2017-06-03"}
  (:refer-clojure :exclude [defmulti defmethod remove-all-methods
                            remove-method prefer-method methods
                            get-method prefers]))
;;----------------------------------------------------------------
(defn ^:private check-valid-options
  "Throws an exception if the given option map contains keys not 
   listed as valid, else returns nil."
  [options & valid-keys]
  (when (seq (apply disj 
                    (apply hash-set (keys options)) 
                    valid-keys))
    (throw
      (IllegalArgumentException.
        ^String
        (apply str "Only these options are valid: "
          (first valid-keys)
          (map #(str ", " %) (rest valid-keys)))))))

;;----------------------------------------------------------------

(defmacro defmulti
  "Creates a new multimethod with the associated dispatch 
   function.
  The docstring and attr-map are optional.

  Options are key-value pairs and may be one of:

  :default

  The default dispatch value, defaults to :default"
  {:arglists '([name docstring? attr-map? dispatch-fn & options])
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
        dispatch-fn (first options)
        options     (next options)
        m           (if docstring
                      (assoc m :doc docstring)
                      m)
        m           (if (meta mm-name)
                      (conj (meta mm-name) m)
                      m)]
    (when (= (count options) 1)
      (throw 
        (Exception. 
          "The syntax for defmulti has changed. 
           Example: 
           (defmulti name dispatch-fn :default dispatch-value)")))
    (let [options   (apply hash-map options)
          default   (get options :default :default)
          ]
      (check-valid-options options :default :hierarchy)
      `(let [v# (def ~mm-name)]
         (when-not (and (.hasRoot v#) 
                        (instance? palisades.lakes.elements.java.generic.MultiFn 
                                   (deref v#)))
           (def ~(with-meta mm-name m)
                (new palisades.lakes.elements.java.generic.MultiFn 
                     ~(name mm-name) 
                     ~dispatch-fn 
                     ~default)))))))

;;----------------------------------------------------------------

(defmacro defmethod
  "Creates and installs a new method of multimethod associated 
   with dispatch-value. "
  {:added "1.0"}
  [multifn dispatch-val & fn-tail]
  `(. ~(with-meta multifn {:tag 'palisades.lakes.elements.java.generic.MultiFn}) 
     addMethod ~dispatch-val (fn ~@fn-tail)))

(defn remove-all-methods
  "Removes all of the methods of multimethod."
  {:added "1.2"
   :static true} 
 [^palisades.lakes.elements.java.generic.MultiFn multifn]
 (.reset multifn))

(defn remove-method
  "Removes the method of multimethod associated with 
   dispatch-value."
  {:added "1.0"
   :static true}
 [^palisades.lakes.elements.java.generic.MultiFn multifn dispatch-val]
 (. multifn removeMethod dispatch-val))

(defn prefer-method
  "Causes the multimethod to prefer matches of dispatch-val-x over
   dispatch-val-y when there is a conflict"
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.MultiFn multifn 
   dispatch-val-x dispatch-val-y]
  (. multifn preferMethod dispatch-val-x dispatch-val-y))

(defn methods
  "Given a multimethod, returns a map of 
   dispatch values -> dispatch fns"
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.MultiFn multifn] 
  (.getMethodTable multifn))

(defn get-method
  "Given a multimethod and a dispatch value, returns the dispatch 
   fn that would apply to that value, or nil if none apply and no 
   default"
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.MultiFn multifn dispatch-val] 
  (.getMethod multifn dispatch-val))

(defn prefers
  "Given a multimethod, returns a map of 
   preferred value -> set of other values"
  {:added "1.0"
   :static true}
  [^palisades.lakes.elements.java.generic.MultiFn multifn] 
  (.getPreferTable multifn))
;;----------------------------------------------------------------
