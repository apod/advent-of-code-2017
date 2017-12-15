(ns advent-of-code-2017.day15)

(defn last-16-bits [n]
  ;; mask (dec (bit-shift-left 1 16))
  (bit-and n 65535))

(defn generator [factor]
  (fn [previous]
    (rem (* previous factor) 2147483647)))

(defn generator-v2 [factor multiple-of]
  (fn [previous]
    (loop [n (rem (* previous factor) 2147483647)]
      (if (zero? (rem n multiple-of))
        n
        (recur (rem (* n factor) 2147483647))))))

(defn pairs [[gen-a a] [gen-b b] checks]
  (loop [a a b b
         res 0
         n checks]
    (cond
      (zero? n) res
      ;; mask (dec (bit-shift-left 1 16))
      (= (bit-and a 65535) (bit-and b 65535)) (recur (gen-a a) (gen-b b) (inc res) (dec n))
      :else (recur (gen-a a) (gen-b b) res (dec n)))))

(comment
  (let [a-init 883
        b-init 879]
    ;; First star
    (pairs [(generator 16807) a-init] [(generator 48271) b-init] 40000000)
    ;; Second star
    (pairs [(generator-v2 16807 4) a-init] [(generator-v2 48271 8) b-init] 5000000))
  )
