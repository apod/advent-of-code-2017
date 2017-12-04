(ns advent-of-code-2017.day04-test
  (:require [advent-of-code-2017.day04 :refer [valid-passphrase?
                                               valid-passphrase-no-anagrams?]]
            [clojure.test :refer :all]))

(deftest valid-passphrase?-given-examples
  (is (= (valid-passphrase? "aa bb cc dd ee") true))
  (is (= (valid-passphrase? "aa bb cc dd aa") false))
  (is (= (valid-passphrase? "aa bb cc dd aaa") true)))

(deftest valid-passphrase-no-anagrams?-given-examples
  (is (= (valid-passphrase-no-anagrams? "abcde fghij") true))
  (is (= (valid-passphrase-no-anagrams? "abcde xyz ecdab") false))
  (is (= (valid-passphrase-no-anagrams? "a ab abc abd abf abj") true))
  (is (= (valid-passphrase-no-anagrams? "iiii oiii ooii oooi oooo") true))
  (is (= (valid-passphrase-no-anagrams? "oiii ioii iioi iiio") false)))


