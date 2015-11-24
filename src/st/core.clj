(ns st.core
  (:require [clojure.walk :as w]
            [clojure.repl :as r]))


(def m1 {:a 1 :j {:b 2 :c {:e 3 :f {:g 4 :h {:i 5}}}}})


(defn mypre [x]
  (println x (if (= x :i) "    It is :i !!!" ""))
  (if (= x :i)
    (throw (Exception. "Don't like :i")))
  (w/walk mypre identity x)
  x)


(defn do-it [m]
  (try
    (w/walk mypre identity m)
    (catch Exception e
      (r/pst e 40)
      e)))


(defn throwme [x]
  (throw (Exception. "Thrown")))


(defn mapvit []
  (mapv throwme [1 2 3]))


(comment

(require '[st.core :as c])
(c/do-it c/m1)

(c/mapvit)
(def e1 *e)
e1

)
