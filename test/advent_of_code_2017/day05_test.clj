(ns advent-of-code-2017.day05-test
  (:require [advent-of-code-2017.day05 :refer [steps]]
            [clojure.test :refer :all]))

(deftest steps-given-examples
  (is (= (steps [0 3 0 1 -3] (constantly inc)) 5))
  (is (= (steps [0 3 0 1 -3] #(if (>= % 3) dec inc)) 10)))
