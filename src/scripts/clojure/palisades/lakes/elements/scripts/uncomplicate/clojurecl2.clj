(require '[clojure.pprint :as pp])
(require '[uncomplicate.commons.core :as ucc])
(require '[uncomplicate.clojurecl 
           [core :as ucl2c] 
           [info :as ucl2i]])
(require '[uncomplicate.neanderthal
           [core :as unc] 
           [opencl :as uno]])
;;----------------------------------------------------------------
(ucl2c/with-default
  (uno/with-default-engine
    (ucc/with-release [x (uno/clv 1 -2 5)]
      (unc/sum x))))

(ucl2c/with-default
  (uno/with-default-engine
    (let [x (uno/clv 1 -2 5)]
      (println (unc/sum x))
      (ucc/release x))))

(def x
  (ucl2c/with-default
    (uno/with-default-engine
      (uno/clv 1 -2 5))))

(ucl2c/with-default
  (uno/with-default-engine
(unc/sum x)))
;;----------------------------------------------------------------
