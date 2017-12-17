(ns advent-of-code-2017.day16-test
  (:require [advent-of-code-2017.day16
             :refer
             [dance exchange parse-move partner spin]]
            [clojure.test :refer :all]))

(deftest move-examples
  (is (= (spin (vec "abcde") 3) [\c \d \e \a \b]))
  (is (= (exchange (vec "abcde") 0 3) [\d \b \c \a \e]))
  (is (= (partner (vec "abcde") \c \e) [\a \b \e \d \c])))

(deftest dance-examples
  (is (= (dance "abcde" (map parse-move ["s1" "x3/4" "pe/b"])) "baedc")))
