(ns advent-of-code-2017.day01
  (:require [clojure.java.io :as io]))

(defn parse-digit [c]
  (Character/getNumericValue c))

(defn captcha-solver
  ([s] (captcha-solver s 1))
  ([s step]
   (let [cycled (cycle s)]
     (reduce (fn [acc [i current]]
               (let [next (nth cycled (+ i step))]
                 (if (= next current)
                   (+ acc (Character/getNumericValue current))
                   acc))) 0 (keep-indexed vector s)))))

(comment
  (let [input (clojure.string/trim-newline
               (slurp (io/resource "day01/input.txt")))]
    ;; First star
    (captcha-solver input)
    ;; Second star
    (captcha-solver input (quot (count input) 2)))
  )
