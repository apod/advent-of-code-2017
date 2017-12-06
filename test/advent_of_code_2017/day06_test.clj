(ns advent-of-code-2017.day06-test
  (:require [advent-of-code-2017.day06 :refer [spread max-bank debugger]]
            [clojure.test :refer :all]))

(deftest max-bank-test
  (is (= (max-bank [0 2 7 0]) [2 7]))
  (is (= (max-bank [3 2 3 0]) [0 3])))

(deftest spread-test
  (is (= (spread [0 2 7 0]) [2 4 1 2]))
  (is (= (spread [2 4 1 2]) [3 1 2 3]))
  (is (= (spread [3 1 2 3]) [0 2 3 4]))
  (is (= (spread [0 2 3 4]) [1 3 4 1]))
  (is (= (spread [1 3 4 1]) [2 4 1 2])))

(deftest debugger-given-examples
  (is (= (:cycles (debugger [0 2 7 0])) 5))
  (is (= (:cycles-from-last-seen (debugger [0 2 7 0])) 4)))
