(ns advent-of-code-2017.day15-test
  (:require [advent-of-code-2017.day15 :refer [pairs generator generator-v2]]
            [clojure.test :refer :all]))

(deftest generator-test
  (let [gen-a (generator 16807)
        gen-b (generator 48271)]
    (is (= (take 5 (drop 1 (iterate gen-a 65)))
           [1092455 1181022009 245556042 1744312007 1352636452]))
    (is (= (take 5 (drop 1 (iterate gen-b 8921)))
           [430625591 1233683848 1431495498 137874439 285222916]))))

(deftest generator-v2-test
  (let [gen-a (generator-v2 16807 4)
        gen-b (generator-v2 48271 8)]
    (is (= (take 5 (drop 1 (iterate gen-a 65)))
           [1352636452 1992081072 530830436 1980017072 740335192]))
    (is (= (take 5 (drop 1 (iterate gen-b 8921)))
           [1233683848 862516352 1159784568 1616057672 412269392]))))

(deftest pairs-given-examples
  (is (= (pairs [(generator 16807) 65] [(generator 48271) 8921] 5) 1))
  (is (= (pairs [(generator-v2 16807 4) 65] [(generator-v2 48271 8) 8921] 1057) 1)))
