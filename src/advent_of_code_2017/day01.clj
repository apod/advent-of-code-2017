(ns advent-of-code-2017.day01
  (:require [clojure.java.io :as io]))

(defn captcha-solver [s]
  nil)

(comment
  (let [input (slurp (io/resource "day01/input.txt"))]
    (captcha-solver input)))
