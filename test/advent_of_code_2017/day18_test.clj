(ns advent-of-code-2017.day18-test
  (:require [advent-of-code-2017.day18 :refer [lookup run process-instruction]]
            [clojure.test :refer :all]))

(deftest run-given-example
  (is (= (let [instructions ['(set a 1) '(add a 2) '(mul a a) '(mod a 5) '(snd a)
                             '(set a 0) '(rcv a) '(jgz a -1) '(set a 1) '(jgz a -2)]
               snd-fn (fn [env x]
                        [:update-env (assoc env :sound (lookup env x))])
               rcv-fn (fn [env x]
                        (if (pos? (lookup env x))
                          [:recover (:sound env)]
                          [:update-pos 1]))
               process-fn (fn [env instruction]
                            (process-instruction env instruction snd-fn rcv-fn))]
           (run instructions {} 0 process-fn)) 4)))
