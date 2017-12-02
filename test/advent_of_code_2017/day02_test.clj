(ns advent-of-code-2017.day02-test
  (:require [advent-of-code-2017.day02 :refer [checksum parse-input
                                               row-difference row-division]]
            [clojure.test :refer :all]))

(deftest parse-input-test
  (is (= (parse-input "5\t1\t9\t5") [[5 1 9 5]]))
  (is (= (parse-input "5\t1\t9\t5\n7\t5\t3") [[5 1 9 5]
                                              [7 5 3]]))
  (is (= (parse-input "5 1 9 5\n7 5 3") [[5 1 9 5]
                                         [7 5 3]])))

(deftest row-difference-given-examples
  (is (= (row-difference [5 1 9 5]) 8))
  (is (= (row-difference [7 5 3]) 4))
  (is (= (row-difference [2 4 6 8]) 6)))

(deftest row-division-given-examples
  (is (= (row-division [5 9 2 8]) 4))
  (is (= (row-division [9 4 7 3]) 3))
  (is (= (row-division [3 8 6 5]) 2)))

(deftest checksum-first-star-given-examples
  (is (= (checksum [[5 1 9 5]
                    [7 5 3]
                    [2 4 6 8]] row-difference) 18)))

(deftest checksum-second-star-given-examples
  (is (= (checksum [[5 9 2 8]
                    [9 4 7 3]
                    [3 8 6 5]] row-division) 9)))
