(ns day1.solution
  (:require [day_1.input :as input]))

(defn part-1-solution
  [input]
  (reduce + input))

(comment
  (part-1-solution (input/get-input)))

(defn part-2-solution
  ([input]
   (part-2-solution input input 0 #{}))

  ([input freqs curr results]
   (cond
     (contains? results curr) curr
     (empty? freqs) (recur input input curr results)
     :else (recur
            input
            (rest freqs)
            (+ curr (first freqs))
            (conj results curr)))))

(comment
  (part-2-solution (input/get-input)))