(ns advent-of-code-2017.day11-test
  (:require [advent-of-code-2017.day11 :refer [steps-away]]
            [clojure.test :refer :all]))

(deftest steps-away-given-examples
  (is (= (steps-away ["ne" "ne" "ne"]) 3))
  (is (= (steps-away ["ne" "ne" "sw" "sw"]) 0))
  (is (= (steps-away ["ne" "ne" "s" "s"]) 2))
  (is (= (steps-away ["se" "sw" "se" "sw" "sw"]) 3)))
