(ns advent-of-code-2017.day08-test
  (:require [advent-of-code-2017.day08
             :refer
             [evaluate
              evaluate-history
              lookup
              max-register-value
              max-register-value-ever
              parse-instruction]]
            [clojure.test :refer :all]))

(def example-instructions (map parse-instruction ["b inc 5 if a > 1"
                                                  "a inc 1 if b < 5"
                                                  "c dec -10 if a >= 1"
                                                  "c inc -20 if c == 10"]))

(deftest lookup-test
  (is (= (lookup {'b 5} 'b) 5))
  (is (= (lookup {} 'b) 0))
  (is (= (lookup {} 5) 5)))

(deftest evaluate-test
  (is (= (evaluate example-instructions) {'a 1 'c -10})))

(deftest max-register-value-given-examples
  (is (= (max-register-value example-instructions) 1)))

(deftest evaluate-history-test
  (is (= (evaluate-history example-instructions) [{} {} {'a 1} {'a 1 'c 10} {'a 1 'c -10}])))

(deftest max-register-value-ever-given-examples
  (is (= (max-register-value-ever example-instructions) 10)))
