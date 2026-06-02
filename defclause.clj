(def clauses [])


(defn defclause [name args pred body]
  (let [clause {:pred (eval `(fn ~args ~pred))
                :body (eval `(fn ~args ~body))}]

    (def clauses (conj clauses clause))))


(defclause 'f '[x] '(string? x) '(println "String: " x))

(defclause 'f '[x] '(number? x) '(println "Number: " x))


(defn f [x]
  (some #(when (apply (:pred %) [x]) (apply (:body %) [x]))
        clauses))


(f 5)

