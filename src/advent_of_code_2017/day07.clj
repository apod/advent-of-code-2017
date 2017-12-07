(ns advent-of-code-2017.day07
  (:require [clojure.string :as str]))

(defn parse-node [s]
  (let [[node children] (str/split s #" -> ")
        [_ name weight] (re-matches #"([a-z]+) \((\d+)\)" node)]
    (cond-> {:name name
             :weight (read-string weight)}
      children (assoc :children (str/split children #", ")))))

(defn bottom-program [input]
  nil)

(comment
  (let [input (str/split-lines
               (slurp (clojure.java.io/resource "day07/input.txt")))]
    (bottom-program input)
    input)
  )
