(ns advent-of-code-2017.day09)

(defn parse-stream [input]
  (letfn [(cancel [[_ & remaining] state]
            #(garbage remaining state))
          (garbage [[c & remaining] state]
            (if (nil? c) state
                (case c
                  \> #(group remaining state)
                  \! #(cancel remaining state)
                  #(garbage remaining (update state :garbage inc)))))
          (group [[c & remaining] state]
            (if (nil? c) state
                (case c
                  \, #(group remaining state)
                  \{ #(group remaining (update state :level inc))
                  \} #(group remaining (-> state
                                           (update :score (partial + (:level state)))
                                           (update :level dec)))
                  \< #(garbage remaining state)
                  [:unhandled c state])))]
    (trampoline group input {:level 0 :score 0 :garbage 0})))

(comment
  (let [input (str/trim (slurp (clojure.java.io/resource "day09/input.txt")))]
    ;; First star
    (:score (parse-stream input))
    ;; Second star
    (:garbage (parse-stream input)))
  )

