(ns advent-of-code-2017.day09-test
  (:require [advent-of-code-2017.day09 :refer [parse-stream]]
            [clojure.test :refer :all]))

(deftest parse-stream-given-examples
  (is (= (:score (parse-stream "{}")) 1))
  (is (= (:score (parse-stream "{{{}}}")) 6))
  (is (= (:score (parse-stream "{{},{}}")) 5))
  (is (= (:score (parse-stream "{{{},{},{{}}}}")) 16))
  (is (= (:score (parse-stream "{<a>,<a>,<a>,<a>}")) 1))
  (is (= (:score (parse-stream "{{<ab>},{<ab>},{<ab>},{<ab>}}")) 9))
  (is (= (:score (parse-stream "{{<!!>},{<!!>},{<!!>},{<!!>}}")) 9))
  (is (= (:score (parse-stream "{{<a!>},{<a!>},{<a!>},{<ab>}}")) 3)))



