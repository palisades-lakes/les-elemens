(System/loadLibrary "mkl_intel_thread")
(require '[uncomplicate.neanderthal
           [core :as unc] 
           [native :as unn]])
(def data (unn/dv [1 2 3]))
(unc/sum data)
(class data)
data
