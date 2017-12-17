(ns advent-of-code-2017.day16
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn spin [programs n]
  (let [cnt (count programs)
        offset (- cnt n)]
    (into (subvec programs offset) (subvec programs 0 offset))))

(defn exchange [programs pos-a pos-b]
  (let [a (nth programs pos-a)
        b (nth programs pos-b)]
    (-> programs (assoc pos-a b) (assoc pos-b a))))

(defn partner [programs a b]
  (let [pos-a (.indexOf programs a)
        pos-b (.indexOf programs b)]
    (-> programs (assoc pos-a b) (assoc pos-b a))))

(defn parse-move [s]
  (case (first s)
    \x {:move exchange
        :args (map read-string (str/split (subs s 1) #"/"))}
    \p {:move partner
        :args (map #(.charAt % 0) (str/split (subs s 1) #"/"))}
    \s {:move spin
        :args [(read-string (subs s 1))]}))

(defn dance [programs moves]
  (str/join (reduce (fn [acc {:keys [move args]}]
                      (apply move acc args)) (vec programs) moves)))

(defn repeat-cycle [programs moves]
  (let [dance #(dance % moves)]
    (loop [n 1
           ps (dance programs)]
      (if (= ps programs)
        n
        (recur (inc n) (dance ps))))))

(comment
  (let [input (str/split (str/trim (slurp (io/resource "day16/input.txt"))) #",")
        programs "abcdefghijklmnop"
        moves (map parse-move input)]
    ;; First star
    (dance programs moves)
    ;; Second star
    (let [cycle-n (repeat-cycle programs moves)]
      (nth (iterate #(dance % moves) programs) (rem 1e9 cycle-n))))
  )
