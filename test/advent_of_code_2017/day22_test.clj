(ns advent-of-code-2017.day22-test
  (:require [advent-of-code-2017.day22 :refer [middle parse-map basic-rules evolved-rules infect]]
            [clojure.test :refer :all]
            [clojure.string :as str]))

(def example-input "..#\n#..\n...\n")

(deftest middle-test
  (is (= (middle (str/split-lines example-input)) [1 1])))

(deftest parse-map-test
  (is (= (parse-map example-input) {[-1  1] \.
                                    [-1  0] \#
                                    [-1 -1] \.
                                    [ 0  1] \.
                                    [ 0  0] \.
                                    [ 0 -1] \.
                                    [ 1  1] \#
                                    [ 1  0] \.
                                    [ 1 -1] \.})))

(deftest infect-given-examples
  (let [m (parse-map example-input)]
    (is (= (infect m 7 basic-rules) 5))
    (is (= (infect m 70 basic-rules) 41))
    (is (= (infect m 10000 basic-rules) 5587))
    (is (= (infect m 100 evolved-rules)) 26)
    (is (= (infect m 10e6 evolved-rules)) 2511944)))
