(ns advent-of-code-2017.day03-test
  (:require [advent-of-code-2017.day03 :refer [walk-to spiral-walker
                                               move turn distance-from]]
            [clojure.test :refer :all]))

(deftest move-test
  (is (= (:position (move (spiral-walker [0 0] :right))) [ 1  0]))
  (is (= (:position (move (spiral-walker [0 0] :up)))    [ 0  1]))
  (is (= (:position (move (spiral-walker [0 0] :down)))  [ 0 -1]))
  (is (= (:position (move (spiral-walker [0 0] :left)))  [-1  0])))

(deftest turn-test
  (is (= (:direction (turn (spiral-walker [0 0] :right))) :up))
  (is (= (:direction (turn (spiral-walker [0 0] :up)))    :left))
  (is (= (:direction (turn (spiral-walker [0 0] :down)))  :right))
  (is (= (:direction (turn (spiral-walker [0 0] :left)))  :down)))

(deftest move-and-turn-test
  (is (= (-> (spiral-walker [0 0] :right)
             move turn move turn move move turn)
         {:position [-1 1] :direction :down}))
  (is (= (-> (spiral-walker [-1 1] :down)
             turn move move turn turn turn move turn turn turn move turn turn)
         {:position [0 0] :direction :right})))

(deftest walk-to-test
  (is (= (:position (walk-to (spiral-walker) 1 1)) [0 0]))
  (is (= (:position (walk-to (spiral-walker) 1 3)) [1 1]))
  (is (= (:position (walk-to (spiral-walker) 1 5)) [-1 1]))
  (is (= (:position (walk-to (spiral-walker) 1 7)) [-1 -1]))
  (is (= (:position (walk-to (spiral-walker) 1 8)) [0 -1]))
  (is (= (:position (walk-to (spiral-walker) 1 9)) [1 -1]))
  (is (= (:position (walk-to (spiral-walker) 1 10)) [2 -1]))
  (is (= (:position (walk-to (spiral-walker) 1 13)) [2 2]))
  (is (= (:position (walk-to (spiral-walker) 1 18)) [-2 1])))

(deftest distance-from-given-examples
  (is (= (distance-from 1) 0))
  (is (= (distance-from 12) 3))
  (is (= (distance-from 23) 2))
  (is (= (distance-from 1024) 31)))

