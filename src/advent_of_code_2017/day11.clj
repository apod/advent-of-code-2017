(ns advent-of-code-2017.day11)

(def abs #(Math/abs %))

(defn distance [coords]
  (apply max (map abs coords)))

(defn move-in-cube-coords [[x y z] side]
  (case side
    "n"  [     x  (inc y) (dec z)]
    "ne" [(inc x)      y  (dec z)]
    "se" [(inc x) (dec y)      z ]
    "s"  [     x  (dec y) (inc z)]
    "sw" [(dec x)      y  (inc z)]
    "nw" [(dec x) (inc y)      z ]))

(defn steps-away [path]
  (distance (reduce move-in-cube-coords [0 0 0] path)))

(defn furthest-steps-away [path]
  (apply max (map cube-distance (reductions move-in-cube-coords [0 0 0] path))))

(comment
  (let [input (clojure.string/trim (slurp (clojure.java.io/resource "day11/input.txt")))
        path (clojure.string/split input #",")]
    ;; First star
    (steps-away path)
    ;; Second star
    (furthest-steps-away path))
  )
