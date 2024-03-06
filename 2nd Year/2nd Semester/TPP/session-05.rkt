; Scheme does not support currying natively.
; The module racjet/function should be imported.

(require mzlib/compat racket/function)



;;;------------------------------------------------------------------------------------------------------------------------------------
; Example of use of currying

(define ll-numbers '((1 3 -10) (5 -8 0 17 21) (8 3)))

; Given the list ll-numbers, whose elements are lists of integer numbers,
; obtained a new list of lists that contain only the even numbers of the
; original sublists.

(display "filtering even numbers: ")

(map (lambda(l)
       (filter even? l)) ll-numbers)

; Now using currying instead of the lambda function

(display "\nfiltering even numbers (curry): ")

(map (curry filter even?) ll-numbers)

; So, curry returns a lambda function:
; (curry filter even?) is equivalent to (lambda  (l) (filter even? l))



;;;------------------------------------------------------------------------------------------------------------------------------------
; Some observations about single argument lambda functions:
; 
; (lambda(x) (f x)) is exactly the same as (f x) -> defining the lambda function does not make sense
;
; Example
;(define f (lambda(l) (length l)); the lambda definition is useless (it is the
;                                     same as directly using length)
;
; To make an alias of the length function it is sufficient (and better) to execute:
;
; (define f length)
; (f '(1 2 3 4)) ;=> 4
;
; Of course, this extends to functions with two or more arguments.
; (lambda(x y) (f x y)) is exactly the same as (f x y)

;;;--------------------------------------------------------------
; An example of bad lambda use:
; Defining using HOF the function binary-and(n1, n2), which returns
; the and of two given binary numbers (represented by a list of zeros and ones)

(define (binary-and n1 n2)
  (map (lambda(x y) (* x y)) n1 n2)) ; This is a bad use


(define (binary-and n1 n2)
  (map * n1 n2)) ; This should be the proper use

(display "\nbinary-and: ")
(binary-and '(1 1 0 0 1 0 1 0) '(0 1 0 1 1 0 1 1))  ; => (0 1 0 0 1 0 1 0)


;;;------------------------------------------------------------------------------------------------------------------------------------
; Currying
;
; It is useful for functions with two or more arguments. 
;
; For a 2-argument function, f(x, y), given the value of the first
; argument (x = a), the one-argument function that evaluates to f(a, y) is
; the lambda function returned by curry(f, a). That is,
; g = curry(f, a) => g(y) = f(a, y)
;
; g = curry(filter, even?) =>  g(y) = filter(even?, y)


; Example:

(define div-5/x (curry / 5))

; Returns the lambda function: x -> 5 / x. Note that the order is
; important. In general, curry(f, a) => x -> f(a, x) which is not the
; same as x -> f(x, a), unless f is associative (for example, the addition +)

(display "\ndiv-5/x -> 5/3: ")
(div-5/x 3)
(display "div-5/x -> 5/2: ")
(div-5/x 2.0)

; On the contrary, the lambda function x -> f(x, a) is curryr(f, a), which
; appends the arguments on the right. For example, to divide a number by a divisor:

(define div-x/5 (curryr / 5))

(display "\ndiv-x/5 -> 3/5: ")
(div-x/5 3)
(display "div-x/5 -> 2/5: ")
(div-x/5 2.0)


; The lambda equivalent to div-5/x (note this lambda function only takes one
; argument) is the following one:

(define lambda-5/x
  (lambda (x) (/ 5 x)))

(display "\nlambda-5/x -> 5/3: ")
(lambda-5/x 3)
(display "lambda-5/x -> 5/2: ")
(lambda-5/x 2.0)

; For the functions div-5/x and lambda-5/x to be completely equivalent, the
; lambda function should also support a variable number of arguments:

(define lambda-5/x
  (lambda x (apply / (append 5 x))))

(display "\ndiv-5/x -> 5 / 2 / -1 / 3: ")
(div-5/x 2 -1 3.0)



;;;------------------------------------------------------------------------------------------------------------------------------------
; Composing functions
;
; If we want to chain two functions, i.e. f(g(x)), we can do so by using a
; composition of both.
;

; Example: create a function to obtain the double of the next number

(define (double x) (* x 2))

(define (add-one x) (+ x 1))

(define double-add-one (compose double add-one))

(display "\nDouble of the next number of 4 (using compose): ")
(double-add-one 4)

; Note this is the same as:

(define double-add-one (lambda (x) (double (add-one x))))

(display "Double of the next number of 4 (using lambda): ")
(double-add-one 4)

; Lambda functions of the form x -> f(a, g(x)) do not directly correspond
; with the currying of f, but can be obtained by combining it with compose:
; compose(curry(f, a), g)

; Examples
(define data '((a (16 -3 4 0)) (b (5 8 0 11 2)) (c (8 19 9 -15))))

; Compute the sum of each number list
(display "\nsums (using compose): ")
(map (compose (curry apply +) cadr) data)

; (map (curry apply +) data) does not work because the number lists are the second elemet of each sublist

; It is the same as:
(display "sums (using lambda): ")
(map (lambda(x) (apply + (cadr x))) data)


; Get the number list of a given symbol

(display "\nnumber list of a (using compose): ")
(cadar (filter (compose (curry eq? 'a) car) data))

; Or we can directly curry the member function
(display "number list of a (currying member): ")
(cadar (filter (curry member 'a) data))



;;;------------------------------------------------------------------------------------------------------------------------------------
; FIRST BLOCK OF EXERCISES


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; maxLength(l1, l2, l3, ...)
;   Using HOF, define the funciton maxLength (of variable parameters),
;   which returns the maximum length of the lists given on its arguments.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;


(define (maxLength . lists)
  (foldl (lambda (lst acc)
           (max (length lst) acc))
         0
         lists)
  
)

(display "\nmaxLength: ")
(maxLength '(a (b c)) '(1 2 3 4) '((a b) (c d) e (f) g)) ;=> 5





;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; filter-by(f, l1, l2, ...)
;   Using HOF, define the funciton filter-by, which receives
;   mandatoroly the function f and then a variable number of lists.
;   It returns the list:
;       (filter(f, l1) filter(f,l2) ...)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

(define (filter-by f . lists)

  (map (curry filter f) lists)

)


(displayln "\nfilter-by:")
(filter-by atom? '(1 (2) 3) '(9 (2 3)) '(0 1 6))      ; => ((1 3) (9) (0 1 6))
(filter-by number? '(a (b) 3) '(d (2 e)) '(a 1 (b)))  ; => ((3) () (1))



;;;------------------------------------------------------------------------------------------------------------------------------------
; SECOND BLOCK OF EXERCISES
;
; The symbol Data (similar to the one of session 2) holds the information
; of people, but now organized in pairs - lists of two elements - (key value).
;
; You should use HOF to define the proposed functions.
;

(define
  Data
  '(((name LUIS) (sex V) (surnames GARCIA PEREZ) (studies (INFORMATICA MEDICINA)) (age 26) (is_working #t))
    ((is_working #t) (sex M) (name MARIA) (surnames LUZ DIVINA) (age 23) (studies (INFORMATICA)))
    ((name ADOLFO) (sex V) (studies (INFORMATICA)) (surnames MONTES PELADOS) (age 24) (is_working #f))
    ((name ANA) (surnames GARCIA GONZALEZ) (age 22) (sex M) (studies ()) (is_working #t))
    ((sex V) (studies ()) (name JOSE) (is_working #f) (surnames PEREZ MONTES) (age 36) )
    ((age 12) (name JOSHUA) (surnames IGLESIAS GARCIA) (sex V) (studies ()) (is_working #t))
    ((name MARUJA) (age 9) (sex M) (studies ()) (surnames FERNANDEZ GARCIA) (is_working #f))
    ((surnames PUERTAS VENTANAS) (name GUILLERMO) (is_working #f) (age 2) (sex V) (studies (ECONOMIA)))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; info(key, p)
;   Define the function info, which returns a list with the value
;   associated to the key for a given person p.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

(define (info key p)

  (cdar (filter (lambda (x) (eq? (car x) key)) p))


)


(display "\ninfo: ")
(info 'surnames (cadr Data))  ; => (LUZ DIVINA)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; search(key, data)
;   Define the function search, which returns the information
;   associated with the given key for each person in data.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;


(define (search key data)

  (map (lambda (x) (info key x)) data)

)


(display "\nsearch: ")
(search 'name Data)  ; => ((LUIS) (MARIA) (ADOLFO) (ANA) (JOSE LUIS) (JOSHUA) (MARUJA) (GUILLERMO))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; search+(l-keys, data)
;   Define the function search+, which returns the information
;   associated with each of the given keys in the list of keys
;   (l-keys) for each person in data.
;
;   Note: it may be easier to previously define a function which
;     executes the requested operation over a single person. Then
;     search+ can be defined applying this first function to each
;     person in data.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

(define (info+ keys person)

  (map (lambda (key) (info key person)) keys)
  
)


(define (search+ l-keys data)

 (map (lambda (x) (info+ l-keys x)) data)

)




(displayln "search+:")
(search+ '(name surnames) Data) ;=> ((LUIS GARCIA PEREZ) (MARIA LUZ DIVINA) (ADOLFO MONTES PELADOS)...)
(search+ '(name sex age) Data) ;=> ((LUIS V 26) (MARIA M 23) (ADOLFO V 24) (ANA M 22) (JOSE V 36)...)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; present(data, key-order)
;   Define the function present which receives a list (key-order)
;   of the desired ordering of the keys. The function should return
;   the whole bunch od data, but the pairs of information of each
;   person should be sorted accordingly to key-order.
;
;   Note: you can use the predefined function sort(l,f), which sorts
;   the elements of l by the order given by f.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

;(displayln "\npresent (pairs of key-value):")
;(present Data '(surnames name age sex studies is_working))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; present(data, key-order)
;   Redefine the present function so it does only return the
;   information, not the keys. For each person, his/her information
;   should be within one list and sorted out by the key-order.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;


;(displayln "\npresent (only values within one list):")
;(present Data '(name surnames age sex studies is_working)
;
; This should output:
;   ((LUIS GARCIA PEREZ 26 V (INFORMATICA MEDICINA) #t)
;    (MARIA LUZ DIVINA 23 M (INFORMATICA) #t)
;    (ADOLFO MONTES PELADOS 24 V (INFORMATICA) #f)
;    (ANA GARCIA GONZALEZ 22 M () #t)
;    (JOSE PEREZ MONTES 36 V () #f)
;    (JOSHUA IGLESIAS GARCIA 12 V () #t)
;    (MARUJA FERNANDEZ GARCIA 9 M () #f)
;    (GUILLERMO PUERTAS VENTANAS 2 V (ECONOMÍA) #f))



;;;------------------------------------------------------------------------------------------------------------------------------------
; COMPLEMENTARY EXERCISES


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; change-all(l, u, v)
;   Define using HOF the function change-all, which returns the
;   resulting list of changing each element u of l by v.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;


;(display "\nchange-all (not curried): ")
;(change-all '((a) b 2 (a 3) (a) a) '(a) 0)  ; => (0 b 2 (a 3) 0 a)



; Curryied version
; -------------------
; choose(ele, u, v)
;   Firstly define the function choose, which returns v if ele is
;   equal to u and u in other case. Then use the curryied version
;   of choose to redefine change-all.
;


;(display "change-all (with curried choose): ")
;(change-all '((a) b 2 (a 3) (a) a) '(a) 0)  ; => (0 b 2 (a 3) 0 a)




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; fill(x, l)
;   Recursively define the function fill. Given a list l, it returns
;   a list of all the possible sublists than can be obtained by
;   inserting x at any position of the original list l.
;
;   fill(x, ()) = ( (x) )
;   fill(x, (a b)) = ( (x a b) (a x b) (a b x) )
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;


;(display "\nfill (not curried): ")
;(fill 'x '(a (b c) d)) ; => ((x a (b c) d) (a x (b c) d) (a (b c) x d) (a (b c) d x))


;; Redefine fill in a curried manner.


;(display "fill (curried): ")
;(fill 'x '(a (b c) d)) ; => ((x a (b c) d) (a x (b c) d) (a (b c) x d) (a (b c) d x))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the symbol my-curry as a lambda function so that it behaves
; the same as the currying of a two-argument function f(x, y).
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;





; (define div-100/2/x (my-curry / 100 2))
; (display "\ndiv-100/2/x -> 100 / 2 / 25 / 2: ") => 1
; (div-100/2/x 25 2)


