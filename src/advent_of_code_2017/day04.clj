(ns advent-of-code-2017.day04
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn valid-passphrase? [passphrase]
  (every? #(= (val %) 1)
          (frequencies (str/split passphrase #"\s+"))))

(comment
  (let [input (str/split-lines (slurp (io/resource "day04/input.txt")))]
    ;; First star
    (count (filter true? (map valid-passphrase? input)))
    )
  )
