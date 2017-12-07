(ns advent-of-code-2017.day07-test
  (:require [advent-of-code-2017.day07 :refer [bottom-program parse-node]]
            [clojure.test :refer :all]))

(deftest parse-node-test
  (is (= (parse-node "pbga (66)")
         {:name "pbga" :weight 66}))
  (is (= (parse-node "fwft (72) -> ktlj, cntj, xhth")
         {:name "fwft" :weight 72 :children ["ktlj" "cntj" "xhth"]})))

(deftest bottom-program-given-examples
  (is (= (bottom-program ["pbga (66)"
                          "xhth (57)"
                          "ebii (61)"
                          "havc (66)"
                          "ktlj (57)"
                          "fwft (72) -> ktlj, cntj, xhth"
                          "qoyq (66)"
                          "padx (45) -> pbga, havc, qoyq"
                          "tknk (41) -> ugml, padx, fwft"
                          "jptl (61)"
                          "ugml (68) -> gyxo, ebii, jptl"
                          "gyxo (61)"
                          "cntj (57)"]) "tknk")))
