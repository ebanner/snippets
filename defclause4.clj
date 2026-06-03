(def clauses [])
(defmacro defclause [name args pred body]
  `(do
     (defn ~name ~args
       (loop [[clause# & rest#] clauses]
         (if (apply (:pred clause#) ~args)
           (apply (:body clause#) ~args)
           (recur rest#))))

     (let [clause# {:pred (fn ~args ~pred)
                    :body (fn ~args ~body)}]

       (def clauses (conj clauses clause#)))))


(defclause f [x] (string? x)
  (println "String: " x))

(defclause f [x] true
  (println "Not a string: " x))


(f "5")

(f 5)
