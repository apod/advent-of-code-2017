(ns advent-of-code-2017.day05)

(defn steps [coll update-fn]
  (let [max-pos (count coll)]
    (loop [pos 0 coll coll steps 0]
      (if (or (neg? pos) (>= pos max-pos))
        steps
        (let [current (nth coll pos)
              f (update-fn current)]
          (recur (+ pos current)
                 (update coll pos f)
                 (inc steps)))))))

(comment
  (let [input (read-string (str \[ (slurp (clojure.java.io/resource "day05/input.txt")) \]))]
    ;; First star
    (steps input (constantly inc))
    ;; Second star
    (steps input #(if (>= % 3) dec inc)))
  )
