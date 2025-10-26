# Prolog-Mini-PL-Interperter
Mini Programing Language Interpreter, built as an assignment in my Principals of Programing Languages class. This code should implement the following grammer: 

LETTER = "a" | "b" | "c" | "d" | "e" | "f" | "g" | "h" | "i" | "j" | "k" | "l" | "m" | "n" | "o" | "p" | "q" | "r" | "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z"; <br>
WORD = LETTER, {LETTER}; <br>
DIGIT = "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9" | "0"; <br>
NUMBER = DIGIT, {DIGIT}; <br>
COND_EXP = (NUMBER | ID), ("<=" | "<" | "=" | ">=" | ">"), (NUMBER | ID); <br>
BOOLEAN = "True" | "False" | COND_EXP; <br>
PRGM = "(", STMTS, ")" ; <br>
STMTS = STMT, {STMT} ; <br>
STMT = ASSN | COND | LOOP | PRNT ; <br>
ASSN = "(", ID, " = ", NUMBER, ")" ; <br>
ID = WORD; <br>
COND = "(if ", "(", BOOLEAN, ")", "(", STMTS, ") else (", STMTS, "))"; <br> 
LOOP = "(for ((", ID, " = ", NUMBER, ") (", COND_EXP, ")) (", STMTS, "))"; <br>
PRNT = "(print ", NUMBER | ID, " )"; <br>
INC = "(", ID, "++)"; <br>

Below are a couple sample programs

Sample 1 (Conditional Statement):

    (
      (x = 10)
      (y = 0)
      (if (x < y)
        (
          (print y)
        ) else (
          (print x)
        )
      )
    )

Sample 2 (For Loop):

    (
      (a = 5)
      (for ((i = 10) (i < a)) (
        (print i)
      ))
    
      (print a)
    )
