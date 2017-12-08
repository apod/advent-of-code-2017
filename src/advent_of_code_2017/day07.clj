(ns advent-of-code-2017.day07
  (:require [clojure.string :as str]))

(defn parse-program [s]
  (let [[node children] (str/split s #" -> ")
        [_ name weight] (re-matches #"([a-z]+) \((\d+)\)" node)]
    (cond-> {:name name
             :weight (read-string weight)}
      children (assoc :children (str/split children #", ")))))

(defn root-program [programs]
  (let [with-children (filter :children programs)
        children (set (mapcat :children with-children))]
    (some (fn [{:keys [name] :as program}]
            (when (not (contains? children name))
              program)) with-children)))

(defn weakest-link [programs]
  (let [root (root-program programs)
        by-name (into {} (map (fn [program]
                                [(:name program) program]) programs))]
    (letfn [(calculate-weight [program-name]
              (let [{:keys [children weight]} (get by-name program-name)]
                (if children
                  (reduce + weight (map calculate-weight children))
                  weight)))
            (expand-children [program-name]
              (let [{:keys [children]} (get by-name program-name)]
                (when children
                  (into [program-name] (mapcat expand-children children)))))]
      (some (fn [program-name]
              (let [{:keys [name weight children] :as program} (get by-name program-name)]
                (when children
                  (when-let [weights (map calculate-weight children)]
                    (when (apply not= weights)
                      (let [unbalanced (some #(when (= (val %) 1) (key %)) (frequencies weights))
                            unbalanced-program (get by-name (nth children (.indexOf weights unbalanced)))
                            difference (reduce - (apply (juxt max min) weights) )]
                        (assoc unbalanced-program
                               :new-weight (- (:weight unbalanced-program) difference))))))))
            ;; Expand children and get them in reverse order to find the inner most unbalance
            (reverse (expand-children (:name root)))))))

(comment

  (let [input (str/split-lines
               (slurp (clojure.java.io/resource "day07/input.txt")))
        programs (map parse-program input)]
    ;; First star
    (:name (root-program programs))
    ;; Second star
    (:new-weight (weakest-link programs)))
  )
