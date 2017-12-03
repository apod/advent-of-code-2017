(ns advent-of-code-2017.day03-test
  (:require [advent-of-code-2017.day03 :refer [steps]]
            [clojure.test :refer :all]))

(deftest steps-givel-examples
  (is (= (steps 1) 0))
  (is (= (steps 12) 3))
  (is (= (steps 23) 2))
  (is (= (steps 1024) 31)))

