(def clauses [])

(defmacro defclause [name args pred body]
  `(do
     (defn ~name ~args
       (some #(when (apply (:pred %) ~args)
                (do (apply (:body %) ~args) :t))

             clauses))

     (let [clause# {:pred (fn ~args ~pred)
                    :body (fn ~args ~body)}]

       (def clauses (conj clauses clause#)))))


(defclause f [x] (string? x)
  (println "String: " x))


(defclause f [x] (number? x)
  (println "Number: " x))


(f 5)


(f "5")
