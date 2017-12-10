(ns advent-of-code-2017.day10
  (:require [clojure.string :as str]))

(defn reverse-portion [coll start length]
  (let [size (count coll)
        ;; Get the elements to be reversed while cycling
        to-reverse (take length (drop start (map-indexed vector (cycle coll))))
        ;; Reverse indexes while retaining elements
        reverse-positions (map vector (reverse (map first to-reverse)) (map second to-reverse))]
    (reduce (fn [coll [pos e]]
              (assoc coll (mod pos size) e)) coll reverse-positions)))

(defn knot-hash [range lengths rounds]
  (loop [coll (vec range)
         [length & remaining] lengths
         pos 0 skip 0
         round rounds]
    (cond
      (zero? round) coll
      (nil? length) (do
                      (println (dec round))
                      (recur coll lengths pos skip (dec round)))
      :else (recur (reverse-portion coll pos length) remaining
                   (+ pos length skip) (inc skip) round))))

(defn sparse-hash [input]
  (let [rounds 64
        suffix [17 31 73 47 23]
        ascii (into (mapv int (str/trim input)) suffix)]
    (knot-hash (range 256) ascii rounds)))

(defn dense-hash [hash]
  (map #(reduce bit-xor %) (partition 16 hash)))

(defn hex-string [hash]
  (str/join (map #(format "%02x" %) hash)))

(comment
  (let [input (slurp (clojure.java.io/resource "day10/input.txt"))
        lengths (read-string (str \[ input \]))]
    ;; First star
    (reduce * (take 2 (knot-hash (range 256) lengths 1)))
    ;; Second star
    (hex-string (dense-hash (sparse-hash input))))
  )
