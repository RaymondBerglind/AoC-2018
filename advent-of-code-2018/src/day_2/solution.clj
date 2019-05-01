(ns day2.solution
  (:require [day_2.input :as input]
            [aoc.core :as core]
            [clojure.test :refer :all]))

; Part 1

(defn els-with-n-occurences-of-any
  [input n]
  (count (filter
          #(core/contains-n-occurences-of-any? % n) input)))

(defn part-1-solution
  [input]
  (* (els-with-n-occurences-of-any input 2)
     (els-with-n-occurences-of-any input 3)))

; Part 2

(defn part-2-solution
  [input]
  (loop [curr (first input)
         ids (rest input)]
    (as-> (first (filter #(core/diffs-by-n? curr % 1) ids)) v
      (if (some? v)
        (apply str
               (filter #(char? %)
                       (map
                        #(and %1 %2)
                        (core/diff-vec curr v)
                        curr)))
        (recur
         (first ids)
         (rest ids))))))

(comment
  (part-1-solution (input/get-input))
  (part-2-solution (input/get-input)))