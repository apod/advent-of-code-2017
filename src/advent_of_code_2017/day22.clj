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

(defn flip [dir]
  (case dir
    :up :down
    :right :left
    :down :up
    :left :right))

(defn move [[x y] dir]
  (case dir
    :up    [x (inc y)]
    :down  [x (dec y)]
    :left  [(dec x) y]
    :right [(inc x) y]))

(defn basic-rules [state dir]
  (case state
    \. [\# (turn-left dir)]
    \# [\. (turn-right dir)]))

(defn evolved-rules [state dir]
  (case state
    \. [\w (turn-left dir)]
    \w [\# dir]
    \# [\f (turn-right dir)]
    \f [\. (flip dir)]))

(defn infect [m bursts rules-fn]
  (loop [m m dir :up
         pos [0 0] infections 0
         burst bursts]
    (let [current (get m pos \.)
          [new-state new-dir] (rules-fn current dir)]
      (if (zero? burst)
        infections
        (recur (assoc m pos new-state)
               new-dir
               (move pos new-dir)
               (if (= new-state \#) (inc infections) infections)
               (dec burst))))))

(comment
  (let [input (slurp (io/resource "day22/input.txt"))]
    ;; First star
    (infect (parse-map input) 10e3 basic-rules)
    ;; Second star
    (infect (parse-map input) 10e6 evolved-rules))
  )
