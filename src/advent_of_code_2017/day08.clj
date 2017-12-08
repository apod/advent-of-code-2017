(ns advent-of-code-2017.day08
  (:require [clojure.string :as str]))

(defn parse-instruction [s]
  (let [symbol->fn {'inc + 'dec -
                    '< < '> >
                    '<= <= '>= >=
                    '== = '!= not=}
        [r f a _ b pred c] (read-string (str \( s \)))]
    {:register r
     :f #((get symbol->fn f) % a)
     :pred (get symbol->fn pred)
     :pred-args [b c]}))

(defn lookup [env var-or-num]
  (if (symbol? var-or-num)
    (get env var-or-num 0)
    var-or-num))

(defn evaluate [instructions]
  (reduce (fn [env {:keys [register f pred f-args pred-args]}]
            (let [pred-args (map (partial lookup env) pred-args)]
              (if (apply pred pred-args)
                (update env register (fnil f 0))
                env)))
          {} instructions))

(defn max-register-value [instructions]
  (apply max (vals (evaluate instructions))))

(comment
  (let [input (str/split-lines
               (slurp (clojure.java.io/resource "day08/input.txt")))
        instructions (map parse-instruction input)]
    ;; First star
    (max-register-value instructions))
  )
