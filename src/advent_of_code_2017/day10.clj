(ns advent-of-code-2017.day10)

(defn reverse-portion [coll start length]
  (let [size (count coll)
        ;; Get the elements to be reversed while cycling
        to-reverse (take length (drop start (map-indexed vector (cycle coll))))
        ;; Reverse indexes while retaining elements
        reverse-positions (map vector (reverse (map first to-reverse)) (map second to-reverse))]
    (reduce (fn [coll [pos e]]
              (assoc coll (mod pos size) e)) coll reverse-positions)))

(defn knot-hash [range lengths]
  (loop [coll (vec range)
         [length & lengths] lengths
         pos 0 skip 0]
    (if (nil? length)
      coll
      (recur (reverse-portion coll pos length)
             lengths (+ pos length skip) (inc skip)))))

(comment
  (let [input (slurp (clojure.java.io/resource "day10/input.txt"))
        lengths (read-string (str \[ input \]))]
    ;; First star
    (reduce * (take 2 (knot-hash (range 256) lengths))))
  )
