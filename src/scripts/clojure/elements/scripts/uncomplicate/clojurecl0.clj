(require '[uncomplicate.clojurecl 
           [core :as cl2c] 
           [info :as cl2i]])
(require '[clojure.pprint :as pp])
;;----------------------------------------------------------------
(pp/pprint
  (map cl2i/info (cl2c/platforms)))
(pp/pprint
  (map cl2i/info (cl2c/devices (first (cl2c/platforms)))))
(pp/pprint
  (map cl2i/info (cl2c/devices (second (cl2c/platforms)))))
;;----------------------------------------------------------------
