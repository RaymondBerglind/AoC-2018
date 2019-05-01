(ns day2.solution
  (:require [day_2.input :as input]
            [clojure.test :refer :all]))

; Part 1
(defn contains-n-occurences-of-any?
  {:test (fn []
           (is (= (contains-n-occurences-of-any? [1 2 3 4 4] 2) true))
           (is (= (contains-n-occurences-of-any? "abbbbcd" 4) true)))}
  [coll n]
  (some #(= % n) (vals (frequencies coll))))

(defn els-with-n-occurences-of-any
  [input n]
  (count (filter
          #(contains-n-occurences-of-any? % n) input)))

(defn part-1-solution
  [input]
  (* (els-with-n-occurences-of-any input 2)
     (els-with-n-occurences-of-any input 3)))

; Part 2
(defn diff-vec
  [coll-1 coll-2]
  (mapv = coll-1 coll-2))

(defn diffs-by-n?
  {:test (fn []
           (is (= (diffs-by-n? "abcd" "aBcd" 1) true))
           (is (= (diffs-by-n? [1 2 3 4 5 6] [1 2 7 8 5 6] 2) true))
           (is (= (diffs-by-n? [0 2 3 4 5 6] [1 2 7 8 5 6] 3) true))
           (is (= (diffs-by-n? [1 2 7 8 5 6] [1 2 7 8 5 6] 3) false))
           (is (= (diffs-by-n? "abcd" "abcd" 1) false))
           (is (= (diffs-by-n? [] [] 1) false))
           (is (= (diffs-by-n? [] [:a :b] 3) false))
           (is (thrown? java.lang.AssertionError (diffs-by-n? [] [] 0) true)))}
  [coll-1 coll-2 n]
  {:pre [(pos? n)]}
  (-> (diff-vec coll-1 coll-2)
      (frequencies)
      (get false)
      (= n)))

(defn part-2-solution
  [input]
  (loop [curr (first input)
         ids (rest input)]
    (as-> (first (filter #(diffs-by-n? curr % 1) ids)) v
      (if (some? v)
        (apply str
               (filter #(char? %)
                       (map
                        #(and %1 %2)
                        (diff-vec curr v)
                        curr)))
        (recur
         (first ids)
         (rest ids))))))

(comment
  (part-1-solution (input/get-input))
  (part-2-solution (input/get-input)))