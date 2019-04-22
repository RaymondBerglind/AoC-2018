(ns day2.solution
  (:require [day_2.input :as input]
            [clojure.test :refer :all]))

(defn contains-n-occurences-of-any?
  {:test (fn []
           (is (= (contains-n-occurences-of-any? [1 2 3 4 4] 2) true))
           (is (= (contains-n-occurences-of-any? "abbbbcd" 4) true)))}
  [coll n]
  (some #(= % n) (vals (frequencies coll))))

(defn part-1-solution
  [input]
  (* (count (filter
             #(contains-n-occurences-of-any? % 2) input))
     (count (filter
             #(contains-n-occurences-of-any? % 3) input))))

(comment
  (part-1-solution (input/get-input)))