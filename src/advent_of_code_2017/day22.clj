(ns advent-of-code-2017.day22
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn middle [m]
  (let [rows (count m)
        cols (count (first m))]
    [(quot rows 2) (quot cols 2)]))

(defn parse-map [input]
  (let [split (str/split-lines input)
        [cx cy] (middle split)
        ;; Generate the relative positions based on the middle pos
        r-pos (for [y (range cy (dec (- cy)) -1)
                    x (range (- cx) (inc cx))]
                [x y])]
    (zipmap r-pos (str/replace input #"\s+" ""))))

(defn turn-left [dir]
  (case dir
    :up :left
    :right :up
    :down :right
    :left :down))

(defn turn-right [dir]
  (case dir
    :up :right
    :right :down
    :down :left
    :left :up))

(defn move [[x y] dir]
  (case dir
    :up    [x (inc y)]
    :down  [x (dec y)]
    :left  [(dec x) y]
    :right [(inc x) y]))

(defn infect [m bursts]
  (loop [m m dir :up
         pos [0 0] infections 0
         burst bursts]
    (let [current (get m pos \.)
          clean? (= current \.)
          new-dir ((if clean? turn-left turn-right) dir)]
      (if (zero? burst)
        infections
        (recur (update m pos (constantly (if clean? \# \.)))
               new-dir
               (move pos new-dir)
               (if clean? (inc infections) infections)
               (dec burst))))))

(comment
  (let [input (slurp (io/resource "day22/input.txt"))]
    ;; First star
    (infect (parse-map input) 10000))
  )
