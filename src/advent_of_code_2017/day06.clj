(ns advent-of-code-2017.day06)

(defn max-bank [banks]
  (let [[h & t] (map-indexed vector banks)]
    (reduce (fn [acc bank]
              (if (> (second bank) (second acc)) bank acc))
            h t)))

(defn spread [banks]
  (let [[pos block] (max-bank banks)
        next (fn [p]
               (if (< (inc p) (count banks))
                 (inc p)
                 0))]
    (loop [remaining block
           banks (assoc banks pos 0)
           pos (next pos)]
      (if (zero? remaining)
        banks
        (recur (dec remaining) (update banks pos inc) (next pos))))))

(defn debugger [banks]
  (loop [seen {banks 1}
         spread-banks (spread banks)
         cnt 1]
    (if (contains? seen spread-banks)
      {:cycles cnt
       :cycles-from-last-seen (- cnt (get seen spread-banks))}
      (recur (assoc seen spread-banks cnt) (spread spread-banks) (inc cnt)))))

(comment
  (let [input (read-string (str \[ (slurp (clojure.java.io/resource "day06/input.txt")) \]))]
    ;; First star
    (:cycles (debugger input))
    ;; Second star
    (:cycles-from-last-seen (debugger input)))
  )
