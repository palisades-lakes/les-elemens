(set! *warn-on-reflection* true) 
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
(ns palisades.lakes.elements.utils
  
  {:doc 
   "General utilities, probably eventually somewhere more specific."
   :author "palisades dot lakes at gmail dot com"
   :since "2017-04-21"
   :version "2018-08-29"}
  
  (:require [clojure.string :as s]
            [clojure.java.io :as io]))
;;----------------------------------------------------------------
(set! *warn-on-reflection* false)
(set! *unchecked-math* false)
(require '[clatrix.core :as clatrix])
(require '[uncomplicate.commons.core :as ucc])
(require '[uncomplicate.clojurecl 
           [core :as ucl2c] 
           [info :as ucl2i]])
(require '[uncomplicate.neanderthal
           [core :as unc] 
           [native :as unn]
           [opencl :as uno]])
(set! *warn-on-reflection* true)
(set! *unchecked-math* :warn-on-boxed)
;;----------------------------------------------------------------
;; TODO: unit test this against a wide range of cases
;; TODO: single regx?

(defn fn-name [f] 
  (let [s (str f)
        s (first (s/split s #"\@"))
        s (last (s/split s #"\$"))
        s (first (s/split s #"__"))]
    s
    #_(first (re-find #"(?<=\$)([^@]+)(?=@)" s))))
;;----------------------------------------------------------------
(defn element-type ^Class [x]
  (let [c (class x)]
    (cond 
      (.isArray c) (.getComponentType c)
      (instance? clatrix.core.Vector x) Double/TYPE
      (instance? uncomplicate.neanderthal.internal.api.DataAccessorProvider x)
      (.entryType 
        ^uncomplicate.neanderthal.internal.api.DataAccessor
        (.data_accessor 
          ^uncomplicate.neanderthal.internal.api.DataAccessorProvider x))
      :else Object)))
;;----------------------------------------------------------------
(defn release [x]
  (when (instance? uncomplicate.commons.core.Releaseable x) 
    (ucc/release x)))
;;----------------------------------------------------------------
(defn used-ram-bytes ^double []
  (let [runtime (Runtime/getRuntime)]
    (loop [r0 (long (- (.totalMemory runtime) 
                       (.freeMemory runtime)))]
      (println "gc")
      (System/gc)
      (let [r1 (long (- (.totalMemory runtime) 
                        (.freeMemory runtime)))]
        (if (<= (Math/abs (/ (double (- r0 r1)) (+ r0 r1))) 0.05)
          (* 0.5 (+ r0 r1))
          (recur r1))))))
;;----------------------------------------------------------------
(defn write-tsv [records ^java.io.File f]
  (io/make-parents f)
  (let [ks (into #{} (mapcat #(.keySet ^java.util.Map %) records))
        ks (sort ks)]
    (with-open [w (io/writer f)]
      (binding [*out* w]
        (println (s/join "\t" (map name ks)))
        (doseq [record records]
          (println 
            (s/join "\t" (map #(or (str (get record %)) "") ks))))
        (flush)))))
;;----------------------------------------------------------------
