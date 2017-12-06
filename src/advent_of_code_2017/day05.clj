(ns advent-of-code-2017.day05)

(defn steps [coll update-fn]
  (loop [pos 0 coll coll steps 0]
    (if-let [current (get coll pos)]
      (recur (+ pos current)
             (update coll pos (update-fn current))
             (inc steps))
      steps)))

(comment
  (let [input (read-string (str \[ (slurp (clojure.java.io/resource "day05/input.txt")) \]))]
    ;; First star
    (steps input (constantly inc))
    ;; Second star
    (steps input #(if (>= % 3) dec inc)))
  )
