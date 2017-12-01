(ns advent-of-code-2017.day01
  (:require [clojure.java.io :as io]))

(defn captcha-solver [s]
  (let [cycled (str s (subs s 0 1))
        only-same (filter #(apply = %) (partition 2 1 cycled))]
    (reduce (fn [acc [c _]]
              (+ acc (Character/getNumericValue c))) 0 only-same)))

(comment

  (let [input (clojure.string/trim-newline
               (slurp (io/resource "day01/input.txt")))]
    (captcha-solver input))
  )
