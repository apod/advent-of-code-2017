(ns advent-of-code-2017.day10-test
  (:require [advent-of-code-2017.day10 :refer [reverse-portion knot-hash]]
            [clojure.test :refer :all]))

(deftest reverse-portion-test
  (let [test-vec [0 1 2 3 4]]
    (is (= (reverse-portion [0 1 2 3 4] 0 3) [2 1 0 3 4]))
    (is (= (reverse-portion [2 1 0 3 4] 3 4) [4 3 0 1 2]))
    (is (= (reverse-portion [4 3 0 1 2] 4 1) [4 3 0 1 2]))
    (is (= (reverse-portion [4 3 0 1 2] 1 5) [3 4 2 1 0]))))

(deftest knot-hash-given-examples
  (is (= (knot-hash (range 5) [3] 1) [2 1 0 3 4]))
  (is (= (knot-hash (range 5) [3 4] 1) [4 3 0 1 2]))
  (is (= (knot-hash (range 5) [3 4 1] 1) [4 3 0 1 2]))
  (is (= (knot-hash (range 5) [3 4 1 5] 1) [3 4 2 1 0]))
  )
