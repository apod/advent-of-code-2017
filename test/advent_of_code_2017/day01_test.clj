(ns advent-of-code-2017.day01-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2017.day01 :refer [captcha-solver]]))

(deftest captcha-solver-examples
  (is (= (captcha-solver "1122") 0))
  (is (= (captcha-solver "1111") 4))
  (is (= (captcha-solver "1234") 0))
  (is (= (captcha-solver "91212129") 9)))
