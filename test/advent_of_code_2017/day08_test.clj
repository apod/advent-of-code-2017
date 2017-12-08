(ns advent-of-code-2017.day08-test
  (:require [advent-of-code-2017.day08
             :refer
             [lookup max-register-value parse-instruction]]
            [clojure.test :refer :all]))

(deftest lookup-test
  (is (= (lookup {'b 5} 'b) 5))
  (is (= (lookup {} 'b) 0))
  (is (= (lookup {} 5) 5)))


(deftest max-register-value-given-examples
  (let [instructions (map parse-instruction ["b inc 5 if a > 1"
                                             "a inc 1 if b < 5"
                                             "c dec -10 if a >= 1"
                                             "c inc -20 if c == 10"])]
    (is (= (max-register-value instructions) 1))))

