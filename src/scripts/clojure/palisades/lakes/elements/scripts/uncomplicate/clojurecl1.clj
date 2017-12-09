(require '[clojure.pprint :as pp])
(require '[uncomplicate.commons.core :as ucc])
(require '[uncomplicate.clojurecl 
           [core :as ucl2c] 
           [info :as ucl2i]
           [legacy :as ucl2l]])
(require '[uncomplicate.neanderthal
           [core :as unc] 
           [opencl :as uno]])
(require '[uncomplicate.neanderthal.internal.api :as api])
;;----------------------------------------------------------------
(def x (ucl2c/with-default
         (uno/with-default-engine 
           (uno/clv 1 -2 5))))
    
(pp/pprint (ucl2i/info (.ctx (api/engine x))))

(let [e (api/engine x)]
  (ucl2c/with-context (.ctx e)
    (ucl2c/with-queue (.queue e)
      (unc/sum x))))
(ucc/release x)
;;----------------------------------------------------------------
(def x (ucl2l/with-default-1
         (uno/with-default-engine 
           (uno/clv 1 -2 5))))
(unc/sum x)
(ucc/release x)
;;----------------------------------------------------------------
(ucl2l/with-default-1
  (uno/with-default-engine
    (pp/pprint uno/*opencl-factory*)
    (ucc/with-release [x (uno/clv 1 -2 5)]
      (unc/sum x))))
;;----------------------------------------------------------------
(ucl2c/with-platform (first (ucl2c/platforms))
  (let [dev (first (ucl2c/sort-by-cl-version (ucl2c/devices :gpu)))]
    (pp/pprint (ucl2i/info dev))
    (pp/pprint (ucl2c/context [dev]))
    (ucl2c/with-context (ucl2c/context [dev])
      (ucl2c/with-queue (ucl2l/command-queue-1 dev)
        (uno/with-default-engine
          (pp/pprint uno/*opencl-factory*)
          (ucc/with-release [x (uno/clv 1 -2 5)]
            (unc/sum x)))))))
;;----------------------------------------------------------------
