(def clauses [])


(defmacro defclause [name args pred body]
  `(let [clause# {:pred (fn ~args ~pred)
                  :body (fn ~args ~body)}]

     (def clauses (conj clauses clause#))))


(defclause f [x] (string? x)
  (println "String: " x))

(defclause f [x] (number? x)
  (println "Number: " x))


(defn f [x]
  (some #(when (apply (:pred %) [x])
           (do (apply (:body %) [x]) :t))
        clauses))


(f 5)

