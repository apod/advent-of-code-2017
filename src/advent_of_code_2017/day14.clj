(ns advent-of-code-2017.day14
  (:require [advent-of-code-2017.day10 :as kh]
            [clojure
             [pprint :refer [cl-format]]
             [string :as str]]
            [clojure.set :as set]))

(def knot-hash (comp kh/dense-hash kh/sparse-hash))

(defn bit-knot-hash [s]
  (str/join (map #(cl-format nil "~8,'0b" %) (knot-hash s))))

(defn square-grid [input size]
  (str/join (map #(bit-knot-hash (str input \- %)) (range size))))

(defn used [grid]
  (reduce (fn [acc c]
            (if (= c \1) (inc acc) acc)) 0 grid))

(defn used-positions [grid size]
  (reduce (fn [acc i]
            (if (= (nth grid i) \1)
              (conj acc [(rem i size) (quot i size)])
              acc))
          #{} (range (* size size))))

(defn neighbors [positions [x y]]
  (let [deltas [[(inc x) y] [(dec x) y] [x (inc y)] [x (dec y)]]]
    (reduce (fn [acc d]
              (if-let [pos (positions d)]
                (conj acc pos)
                acc)) [] deltas)))

(defn region [used-positions pos]
  (loop [region #{}
         [n & ns] [pos]]
    (cond
      (nil? n) region
      (contains? region n) (recur region ns)
      :else (recur (conj region n) (into ns (neighbors used-positions n))))))

(defn regions [used-positions]
  (loop [positions used-positions
         regions #{}]
    (if (seq positions)
      (let [rg (region positions (first positions))]
        (recur (set/difference positions rg) (conj regions rg)))
      regions)))

(comment
  (def the-grid (square-grid "hxtvlmkl" 128))
  ;; First star
  (used the-grid)
  ;; Second star
  (count (regions (used-positions the-grid 128)))
  )
