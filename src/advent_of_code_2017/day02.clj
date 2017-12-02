(ns advent-of-code-2017.day02
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn parse-input [s]
  (let [rows (str/split-lines s)]
    (map (fn [row-s]
           (read-string (str \[ row-s \]))) rows)))

(defn row-difference [row]
  (reduce - (apply (juxt max min) row)))

(defn checksum [rows]
  (reduce + (map row-difference rows)))

(comment
  (let [spreadsheet (parse-input (slurp (io/resource "day02/input.txt")))]
    (checksum spreadsheet))
  )

