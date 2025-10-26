(ns clojure-interpreter.core)

; Parse operations into tokens
(defn logic_parse [expr]
  (case expr
    = =
    < <
    > >
    <= <=
    >= >=
  )
)

; Parse boolean statements into true or false
(defn bool_parse [expr, env]
  (if (list? expr)
    (do ; Conditional statement
       (if (number? (first expr))
         (def firstnum (first expr)) ; Save the number if it's a number
         (def firstnum (env (keyword (first expr)))) ; Else it's a variable, look up its value in env
         )
       (if (number? (last expr))
         (def secondnum (last expr))
         (def secondnum (env (keyword (last expr))))
         )
       ((logic_parse (second expr)) firstnum secondnum) ; Returns true or false
    )
    (case expr ; Boolean
      True true
      False false
      (println "Error, boolean cannot be evaluated")
    )
  )
)

(declare exec_block)

; Recursive function started by exec_for
(defn rec_for [stop loop_env body env]
  (if (bool_parse stop, loop_env)
    ; Run the body, increment the loop env, recurse
    (rec_for stop (update-in loop_env (keys loop_env) inc) body (exec_block body env))
    env ; Else exit the loop, return the environment
    )
  )

; Run for loop
(defn exec_for [expr env]
  (def header (second expr)) ; List of loop variable declaration and loop conditional
  (def loop_env (hash-map (keyword (first (first header))) (last (first header)))) ; Env containing the loop variable
  (def stop (second header)) ; Loop conditional, stop if false
  (def body (last expr)) ; Code to be run each loop

  (if (bool_parse stop, loop_env)
    (rec_for stop loop_env body env) ; Recursive function to run the loop
    (env) ; Exit without running the loop
  )
)

; Execute each expression and return an updated env
(defn exec_ln [expr, env]
  (case (first expr)
    if (merge env (if (bool_parse (second expr) env) (exec_block (nth expr 2) env) (exec_block (last expr) env)))
    for (merge env (exec_for expr env))
    print (do (println (if (env (keyword (second expr))) (env (keyword (second expr))) (second expr))) env)
    (case (second expr)
      = (merge env (hash-map (keyword (first expr)) (last expr)))
      ++ (merge env (hash-map (keyword (first expr)) (inc (env (keyword (first expr))))))
      (do (println "Error, symbol not recognised") env)
    )
  )
)

; Execute a block of code
(defn exec_block [block env]
  (if (next block) ; Next returns nil if there is no more items, nil is false
    (exec_block (rest block) (merge env (exec_ln (first block) env)))
    (merge env (exec_ln (first block) env))
  )
)

(defn miniPL-interpret [expr]
  (exec_block expr {})
)
