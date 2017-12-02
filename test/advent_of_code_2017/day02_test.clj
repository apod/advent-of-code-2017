(ns advent-of-code-2017.day02-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2017.day02 :refer [row-difference checksum]]))

(deftest row-difference-given-examples
  (is (= (row-difference [5 1 9 5]) 8))
  (is (= (row-difference [7 5 3]) 4))
  (is (= (row-difference [2 4 6 8]) 6)))

(deftest checksum-given-examples
  (is (= (checksum [[5 1 9 5]
                    [7 5 3]
                    [2 4 6 8]]) 18)))
