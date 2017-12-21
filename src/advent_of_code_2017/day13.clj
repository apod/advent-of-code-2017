(ns advent-of-code-2017.day13
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn caught? [depth range]
  (zero? (rem depth (* 2 (dec range)))))

(defn trip-severity [dr-map delay]
  (let [max-depth (apply max (keys dr-map))]
    (reduce (fn [severity picosecond]
              (let [srange (get dr-map picosecond)]
                (cond
                  (nil? srange) severity
                  (caught? (+ picosecond delay) srange) (+ severity (* (+ picosecond delay) srange))
                  :else severity))) 0 (range 0 (inc max-depth)))))

(defn perfect-delay [dr-map]
  (some (fn [delay]
          (when (zero? (trip-severity dr-map delay))
            delay)) (range)))

(comment
  (let [input (str/replace (slurp (io/resource "day13/input.txt")) #":" " ")
        dr-map (read-string (str \{ input \}))]
    ;; First star
    (trip-severity dr-map 0)
    ;; Second star
    (perfect-delay dr-map))
  )
