(ns advent-of-code-2017.day07-test
  (:require [advent-of-code-2017.day07
             :refer
             [parse-program root-program weakest-link]]
            [clojure.test :refer :all]))

(def example-programs [{:name "pbga" :weight 66}
                       {:name "xhth" :weight 57}
                       {:name "ebii" :weight 61}
                       {:name "havc" :weight 66}
                       {:name "ktlj" :weight 57}
                       {:name "fwft" :weight 72 :children ["ktlj" "cntj" "xhth"]}
                       {:name "qoyq" :weight 66}
                       {:name "padx" :weight 45 :children ["pbga" "havc" "qoyq"]}
                       {:name "tknk" :weight 41 :children ["ugml" "padx" "fwft"]}
                       {:name "jptl" :weight 61}
                       {:name "ugml" :weight 68 :children ["gyxo" "ebii" "jptl"]}
                       {:name "gyxo" :weight 61}
                       {:name "cntj" :weight 57}])

(deftest parse-program-test
  (is (= (parse-program "pbga (66)")
         {:name "pbga" :weight 66}))
  (is (= (parse-program "fwft (72) -> ktlj, cntj, xhth")
         {:name "fwft" :weight 72 :children ["ktlj" "cntj" "xhth"]})))

(deftest root-program-given-examples
  (is (= (root-program example-programs)
         {:name "tknk" :weight 41 :children ["ugml" "padx" "fwft"]})))

(deftest weakest-link-given-examples
  (is (= (:new-weight (weakest-link example-programs))
         60)))
