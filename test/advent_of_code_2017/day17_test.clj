(ns advent-of-code-2017.day17-test
  (:require [advent-of-code-2017.day17 :refer [insert-at spinlock bufferless-spinlock]]
            [clojure.test :refer :all]))

(deftest insert-at-test
  (is (= (insert-at [] 0 1)) [1])
  (is (= (insert-at [0] 0 1)) [1 0])
  (is (= (insert-at [0 1] 1 2)) [0 2 1]))

(deftest spinlock-examples
  (is (= (spinlock 3 0) [0]))
  (is (= (spinlock 3 1) [0 1]))
  (is (= (spinlock 3 3) [0 2 3 1]))
  (is (= (spinlock 3 8) [0 5 7 2 4 3 8 6 1]))
  (is (= (spinlock 3 9) [0 9 5 7 2 4 3 8 6 1])))

(deftest bufferless-spinlock-examples
  (is (= (bufferless-spinlock 3 1 1) 1))
  (is (= (bufferless-spinlock 3 3 1) 2))
  (is (= (bufferless-spinlock 3 8 1) 5))
  (is (= (bufferless-spinlock 3 9 1) 9)))
