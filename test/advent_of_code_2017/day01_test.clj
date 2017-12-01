(ns advent-of-code-2017.day01-test
  (:require [clojure.test :refer :all]
            [advent-of-code-2017.day01 :refer [captcha-solver]]))

(deftest captcha-solver-single-codes
  (is (= (captcha-solver "1") 1))
  (is (= (captcha-solver "3") 3)))

(deftest captcha-solver-cycle-codes
  (is (= (captcha-solver "2342") 2))
  (is (= (captcha-solver "23342") 5)))
  (is (= (captcha-solver "5555") 20))

(deftest captcha-solver-given-examples
  (is (= (captcha-solver "1122") 3))
  (is (= (captcha-solver "1111") 4))
  (is (= (captcha-solver "1234") 0))
  (is (= (captcha-solver "91212129") 9)))

(deftest captcha-solver-extra-star-given-examples
  (is (= (captcha-solver "1212" 2) 6))
  (is (= (captcha-solver "1221" 2) 0))
  (is (= (captcha-solver "123425" 3) 4))
  (is (= (captcha-solver "123123" 3) 12))
  (is (= (captcha-solver "12131415" 4) 4)))
