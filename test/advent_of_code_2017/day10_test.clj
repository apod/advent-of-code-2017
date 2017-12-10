(ns advent-of-code-2017.day10-test
  (:require [advent-of-code-2017.day10
             :refer
             [dense-hash hex-string knot-hash reverse-portion sparse-hash]]
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
  (is (= (knot-hash (range 5) [3 4 1 5] 1) [3 4 2 1 0])))

(deftest hex-string-given-examples
  (is (= (hex-string (dense-hash (sparse-hash ""))) "a2582a3a0e66e6e86e3812dcb672a272"))
  (is (= (hex-string (dense-hash (sparse-hash "AoC 2017"))) "33efeb34ea91902bb2f59c9920caa6cd"))
  (is (= (hex-string (dense-hash (sparse-hash "1,2,3"))) "3efbe78a8d82f29979031a4aa0b16a9d"))
  (is (= (hex-string (dense-hash (sparse-hash "1,2,4"))) "63960835bcdc130f0b66d7ff4f6a5a8e")))
