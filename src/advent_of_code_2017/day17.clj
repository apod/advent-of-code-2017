(ns advent-of-code-2017.day17)

(defn insert-at [coll n elem]
  (let [[start end] (split-at n coll)]
    (concat start [elem] end)))

(defn spinlock [steps iterations]
  (loop [coll [0]
         pos 0 i 1]
    (cond
      (> i iterations) coll
      :else (let [new-pos (inc (rem (+ pos steps) (count coll)))]
              (recur (insert-at coll new-pos i) new-pos (inc i))))))

(defn bufferless-spinlock [steps iterations index-of-interest]
  (loop [last-insert nil
         pos 0 i 1]
    (if (> i iterations)
      last-insert
      (recur (if (= (inc (rem (+ pos steps) i)) index-of-interest)
               i last-insert)
             (inc (rem (+ pos steps) i))
             (inc i)))))

(comment
  ;; First star
  (let [input 394
        spincoll (spinlock input 2017)]
    (nth spincoll (inc (.indexOf spincoll 2017))))
  ;; Second star
  (bufferless-spinlock 394 50000000 1)
  )
