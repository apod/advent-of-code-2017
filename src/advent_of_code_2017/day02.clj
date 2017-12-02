(ns advent-of-code-2017.day02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input [s]
  (let [rows (str/split-lines s)]
    (map (fn [row-s]
           (read-string (str \[ row-s \]))) rows)))

(defn row-difference [row]
  (reduce - (apply (juxt max min) row)))

(defn row-division [row]
  (let [divisible (for [a row b row
                        :when (and (not= a b) (= (mod a b) 0))]
                    [a b])]
    (apply quot (first divisible))))

(defn checksum [rows f]
  (reduce + (map f rows)))

(comment
  (let [spreadsheet (parse-input (slurp (io/resource "day02/input.txt")))]
    ;; First star
    (checksum spreadsheet row-difference)
    ;; Second star
    (checksum spreadsheet row-division))
  )

