(ns advent-of-code-2017.day12-test
  (:require [advent-of-code-2017.day12 :refer [connections groups]]
            [clojure.test :refer :all]))

(deftest connections-given-examples
  (is (= (count (connections {0 [2]
                              1 [1]
                              2 [0 3 4]
                              3 [2 4]
                              4 [2 3 6]
                              5 [6]
                              6 [4 5]} 0)) 6)))

(deftest groups-given-examples
  (is (= (count (groups {0 [2]
                         1 [1]
                         2 [0 3 4]
                         3 [2 4]
                         4 [2 3 6]
                         5 [6]
                         6 [4 5]})) 2)))
