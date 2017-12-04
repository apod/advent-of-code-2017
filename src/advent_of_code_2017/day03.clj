(ns advent-of-code-2017.day03)

(def abs #(Math/abs %))

(defn manhattan-distance [[x y]]
  (+ (abs x) (abs y)))

(defn spiral-walker
  ([]
   (spiral-walker [0 0] :right))
  ([position direction]
   {:position position
    :direction direction}))

(defn move [{:keys [position direction] :as walker}]
  (->> (case direction
         :right (update position 0 inc)
         :up    (update position 1 inc)
         :left  (update position 0 dec)
         :down  (update position 1 dec))
       (assoc walker :position)))

(defn turn [{:keys [direction] :as walker}]
  (->> (case direction
         :right :up
         :up    :left
         :left  :down
         :down  :right)
       (assoc walker :direction)))

(defn walk-to [walker from-n to-n]
  (loop [{:keys [position] :as walker} walker
         n from-n]
    (if (= n to-n)
      walker
      (let [[x y] position
            action (cond
                     ;; Handle initial [0 0] position
                     (= position [0 0]) move
                     ;; Handle [1 0] position
                     (= position [1 0]) (comp move turn)
                     ;; Handle bottom right expansion to the right
                     (and (neg? y) (= (- x) y)) move
                     ;; Handle bottom right diagonal expansion turn
                     (and (neg? y) (= (- x) (dec y))) (comp move turn)
                     ;; Handle top left/right diagonal turns
                     (= (abs x) (abs y)) (comp move turn)
                     :else move)]
        (recur (action walker) (inc n))))))

(defn distance-from [n]
  (manhattan-distance (:position (walk-to (spiral-walker) 1 n))))

(comment
  (let [input 325489]
    (distance-from input))
  )
