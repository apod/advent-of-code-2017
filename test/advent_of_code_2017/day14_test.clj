(ns advent-of-code-2017.day14-test
  (:require [advent-of-code-2017.day14 :refer [used regions used-positions]]
            [clojure.test :refer :all]))

(deftest used-test
  (is (= (used "10100000110000100000000101110000") 9)))

(deftest used-positions-test
  (is (= (used-positions "0010101000011111" 4) #{[2 0]
                                                 [0 1] [2 1]
                                                 [3 2]
                                                 [0 3] [1 3] [2 3] [3 3]})))

(deftest regions-test
  (is (= (regions (used-positions "0010101000011111" 4))
         #{#{[2 0] [2 1]} #{[0 1]} #{[3 2] [0 3] [1 3] [2 3] [3 3]}})))
