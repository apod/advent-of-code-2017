(ns advent-of-code-2017.day13-test
  (:require [advent-of-code-2017.day13 :refer [trip-severity perfect-delay]]
            [clojure.test :refer :all]))

(deftest given-examples
  (is (= (trip-severity {0 3
                         1 2
                         4 4
                         6 4} 0) 24))
  (is (= (perfect-delay {0 3
                         1 2
                         4 4
                         6 4}) 10)))

