(ns advent-of-code-2017.day18
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(defn parse-instruction [s]
  (read-string (str \( s \))))

(defn lookup [env reg-or-num]
  (if (symbol? reg-or-num)
    (get env reg-or-num 0)
    reg-or-num))

(defn process-instruction [env instruction snd-fn rcv-fn]
  (let [[inst x y] instruction]
    (case inst
      snd (snd-fn env x)
      set [:update-env (assoc env x (lookup env y))]
      add [:update-env (update env x (fnil + 0) (lookup env y))]
      mul [:update-env (update env x (fnil * 0) (lookup env y))]
      mod [:update-env (update env x (fnil rem 0) (lookup env y))]
      rcv (rcv-fn env x)
      jgz (if (pos? (lookup env x))
            [:update-pos (lookup env y)]
            [:update-pos 1]))))

(defn run [instructions env pos process-fn]
  (loop [env env
         pos pos]
    (let [[action x] (process-fn env (nth instructions pos))]
      (case action
        :recover x
        :wait [:wait [env pos]]
        :update-env (recur x (inc pos))
        :update-pos (recur env (+ pos x))))))

(defn duet [instructions]
  (let [queue-0 (atom (clojure.lang.PersistentQueue/EMPTY))
        queue-1 (atom (clojure.lang.PersistentQueue/EMPTY))
        snd-fn (fn [env x]
                 (let [snd-q (if (zero? (get env :program))
                               queue-0 queue-1)]
                   (swap! snd-q conj (lookup env x))
                   [:update-env (update env :send-messages (fnil inc 0))]))
        rcv-fn (fn [env x]
                 (let [rcv-q (if (zero? (get env :program))
                               queue-1 queue-0)]
                   (if (seq @rcv-q)
                     (let [queue @rcv-q]
                       (swap! rcv-q pop)
                       [:update-env (assoc env x (peek queue))])
                     [:wait])))
        process-fn (fn [env instruction]
                     (process-instruction env instruction snd-fn rcv-fn))
        program-fn (fn [env pos]
                     (run instructions env pos process-fn))]
    (loop [[p0-state p0-args] (program-fn {'p 0 :program 0} 0)
           [p1-state p1-args] (program-fn {'p 1 :program 1} 0)]
      (cond
        (and (every? #(= :wait %) [p0-state p1-state])
             (every? empty? [@queue-0 @queue-1])) [(first p0-args) (first p1-args)]
        (seq @queue-1) (recur (apply program-fn p0-args) [p1-state p1-args])
        :else (recur [p0-state p0-args] (apply program-fn p1-args))))))

(comment
  (def instructions (->> (slurp (io/resource "day18/input.txt"))
                         (str/split-lines)
                         (map parse-instruction)))
  ;; First star
  (let [snd-fn (fn [env x]
                 [:update-env (assoc env :sound (lookup env x))])
        rcv-fn (fn [env x]
                 (if (pos? (lookup env x))
                   [:recover (:sound env)]
                   [:update-pos 1]))
        process-fn (fn [env instruction]
                     (process-instruction env instruction snd-fn rcv-fn))]
    (run instructions {'p 0} 0 process-fn))
  ;; Second star
  (get-in (duet instructions) [1 :send-messages])
  )
