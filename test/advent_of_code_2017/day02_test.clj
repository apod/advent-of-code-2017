(ns advent-of-code-2017.day02-test
  (:require [advent-of-code-2017.day02 :refer [checksum parse-input row-difference]]
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

(deftest checksum-given-examples
  (is (= (checksum [[5 1 9 5]
                    [7 5 3]
                    [2 4 6 8]]) 18)))
