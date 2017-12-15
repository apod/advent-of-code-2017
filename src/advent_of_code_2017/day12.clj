(ns advent-of-code-2017.day12
  (:require [clojure.string :as str]))

(defn parse-node [s]
  (let [[node _ & connections] (read-string (str \[ s \]))]
    [node connections]))

(defn connections [graph node]
  (loop [visited #{}
        [current & nodes] [node]]
    (cond
      (nil? current) visited
      (contains? visited current) (recur visited nodes)
      :else (recur (conj visited current) (into nodes (get graph current))))))

(defn groups [graph]
  (loop [the-groups #{}
         [node & nodes] (keys graph)]
    (cond
      (nil? node) the-groups
      (some #(contains? % node) the-groups) (recur the-groups nodes)
      :else (recur (conj the-groups (connections graph node)) nodes))))

(comment
  (let [input (str/split-lines (slurp (clojure.java.io/resource "day12/input.txt")))
        graph (into {} (map parse-node input))]
    ;; First star
    (count (connections graph 0))
    ;; Second star
    (count (groups graph)))
  )
