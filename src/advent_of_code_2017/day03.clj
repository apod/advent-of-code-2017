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

(defn action-by-position [[x y :as position]]
  (cond
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
    :else move))

(defn walk-to [walker from-n to-n]
  (loop [{:keys [position] :as walker} walker
         n from-n]
    (if (= n to-n)
      walker
      (let [action (action-by-position position)]
        (recur (action walker) (inc n))))))

(defn distance-from [n]
  (manhattan-distance (:position (walk-to (spiral-walker) 1 n))))

;; Second star

;; Yay finally got to use Joy of Clojure neighbors fn
(defn neighbors [current-position]
  (let [deltas [[-1  1]   [0  1] [ 1  1]
                [-1  0] #_[0  0] [ 1  0]
                [-1 -1]   [0 -1] [ 1 -1]]]
    (map #(map + current-position %) deltas)))

(defn walk-summing-neighbors-until [walker from-n pred]
  (loop [{:keys [position] :as walker} walker
         n from-n
         filled {[0 0] 1}]
    (if (pred n)
      n
      (let [next-walker ((action-by-position position) walker)
            current-position (:position next-walker)
            next-n (reduce (fn [acc pos]
                             (+ acc (get filled pos 0))) 0 (neighbors current-position))]
        (recur next-walker next-n (assoc filled current-position next-n))))))

(comment
  (let [input 325489]
    ;; First star
    (distance-from input)
    ;; Second star
    (walk-summing-neighbors-until (spiral-walker) 1 #(> % input)))
  )

