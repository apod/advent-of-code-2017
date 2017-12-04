(ns advent-of-code-2017.day04-test
  (:require [advent-of-code-2017.day04 :refer [valid-passphrase?]]
            [clojure.test :refer :all]))

(deftest valid-passphrase?-given-examples
  (is (= (valid-passphrase? "aa bb cc dd ee") true))
  (is (= (valid-passphrase? "aa bb cc dd aa") false))
  (is (= (valid-passphrase? "aa bb cc dd aaa") true))
  )


