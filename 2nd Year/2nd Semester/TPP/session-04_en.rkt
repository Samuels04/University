(require  mzlib/compat)

;;;------------------------------------------------------------------------------------------------------------------------------------
;;; Number of arguments of a function
;;;------------------------------------------------------------------------------------------------------------------------------------

;;; Functions with three arguments
(displayln "lambda(x y z):")

((lambda (x y z)
   (writeln x)
   (writeln y)
   z) 'a 'b '(c d))

;;; Function with two arguments and the rest, z, are optional
;;; z is a list with the rest of the arguments
(displayln "lambda(x y . z):")

((lambda (x y . z)
   (writeln x)
   (writeln y)
   z) 'a 'b '(c d) '(e) 'f)

;;; Function with optional arguments, z is the list
;;; of arguments (observe that the sintax of the
;;; parameters changes, instead of (. z) it is just z without parenthesis)
(displayln "lambda z: ")

((lambda z z) 'a 'b '(c d) '(e) 'f)


;;;------------------------------------------------------------------------------------------------------------------------------------
;;; Argument validation in non-recursive functions.
;;;
;;; The first check should be the number of arguments, unless it is variable.
;;;
;;; If the function works with a specific number of arguments, the definition
;;; takes a variable number of argumentos and then the function checks
;;; all of them have been provided. Using let repetitive calculations can
;;; be avoided.
;;;------------------------------------------------------------------------------------------------------------------------------------
;;; Example: function frecuency(x, l) from Session-03
;;;


(define (frequency . args)
  (cond ([not (= (length args) 2)]
         (error "frecuency(x, l): requires two arguments"))
        ([not (list? (cadr args))]
         (error "frecuency(x, l): the second argument should be a list"))
        [else
         (length (filter (lambda(item) (equal? (car args) item)) (cadr args)))]))


; This can be done by directly defining a lambda function

(define frequency2
  (lambda args
    (cond ([not (= (length args) 2)]
           (error "frecuency(x, l): requires two arguments"))
          ([not (list? (cadr args))]
           (error "frecuency(x, l): the second argument should be a list"))
          [else
           (length (filter (lambda(item) (equal? (car args) item)) (cadr args)))])))



;;;------------------------------------------------------------------------------------------------------------------------------------
;;; Argument validation in recursive functions.
;;;
;;; As in the previous case, but now letrec can be used to check the
;;; arguments. This allows to only validate the arguments in the first call,
;;; and not in the recursive ones.
;;;------------------------------------------------------------------------------------------------------------------------------------
;;; Example: function my-append(l1, l2)
;;;

; 1. Base        : my-append((), l2) = l2
; 2. Recursion   : l1 is not () => l1 = cons(car(l1), cdr(l1))
;     Hypothesis : assume we know H = my-append(cdr(l1), l2)
;      Thesis    : my-append(l1) = cons(car(l1), H)


(define (my-append . args)
  (letrec ([f (lambda (l1 l2)          ;; letrec due to f being recursive and calling itself
                (if [null? l1]
                    l2
                    (cons (car l1) (f (cdr l1) l2))))])
    (cond ([not (= (length args) 2)]
           (error "my-append(l1,l2): requires two arguments"))
          ([not (list? (car args))]
           (error "my-append(l1,l2): the first argument should be a list"))
          ([not (list? (cadr args))]
           (error "my-append(l1,l2): the second argument should be a list"))
          (else
           (f (car args) (cadr args))))))

; This can be done by directly defining a lambda function

(define my-append2                 
  (lambda args
    (letrec ([f (lambda (l1 l2)          ;; letrec due to f being recursive and calling itself
                  (if [null? l1]
                      l2
                      (cons (car l1) (f (cdr l1) l2))))])
      (cond ([not (= (length args) 2)]
             (error "my-append(l1,l2): requires two arguments"))
            ([not (list? (car args))]
             (error "my-append(l1,l2): the first argument should be a list"))
            ([not (list? (cadr args))]
             (error "my-append(l1,l2): the second argument should be a list"))
            (else
             (f (car args) (cadr args)))))))


;;; Redefinition of and (_and) and or (_or) to take any number of arguments.


(define _or
  (lambda x
    (letrec ([f (lambda (y)
                  (cond [(null? y) #f]
                        [(car y)]                 ; This case returns (car y), so the first true value is returned. To just return #t or #f, it can be substituted by [(car y) #t]
                        [else (f (cdr y))]))])
      (f x))))

(displayln "\n_or: ")
(_or #f 'a #f 'b)  ;=> a
(_or #f #f)     ;=> #f


(define _and
  (lambda x
    (letrec ([f (lambda(y)
                  (cond [(null? y) #t]
                        [(not (car y)) #f]
                        [(null? (cdr y)) (car y)] ; If only #t or #f should be returned, then this condition should be erased
                        [else (f (cdr y))]))])
      (f x))))

(displayln "\n_and: ")
(_and #t 'a 0 ())         ;=> ()
(_and #t 'a #f '(a b c))  ;=> #f


;;;------------------------------------------------------------------------------------------------------------------------------------
;;
;; EXERCISE OF USE OF MAP FUNCTION.
;;
;; From the last session, the function Extract
;;     Extract(Data, Filter, Format) => (...)
;; was a Higher-Order-Function which received:
;; Data                      : the data to examine
;; Filter(Person)  => #t/#f  : the filter function to apply to each person
;; Format(Persona) => (...)  : the format function to return the relevant information of a person
;;
;; The Extract function returns the list of elements from Data that meet Filter
;; and formatted via Format. In general, both the Filter function and the Format
;; function will be lambda functions, but in cases where it is convenient, predefined
;; access functions can also be used.
;;
;
; Define the Extract function using HOF already defined in SCHEME
; CLUE:
;    FIRSTLY filter the data
;    SECONDLY format the already filtered data

(displayln "\nExtract (using already defined HOF):")

(define (Extract Data Filter Format)
  (cond ([not (list? Data)]) (error "Data must be a list")
        (map Format (map Filter Data)))
  )


;;;------------------------------------------------------------------------------------------------------------------------------------
;
; MORE HOF EXAMPLES
;
;

(displayln "\nnumbers: ")

(define numbers
  '((n1 (3 7 3))(n2 (3 4 9 0 1))(n3 (3 0 3 4)) (n4 (7))))

; Obtain all the numbers with more than 3 digits, directly using filter
(filter (lambda(x) (> (length (cadr x)) 3)) numbers)

; Obtain the name of all the numbers with more than 3 digits
;(Extract numbers (lambda(x) (> (length (cadr x)) 3)) car)

; o bien
(map car (filter (lambda(x) (> (length (cadr x)) 3)) numbers))



;;;------------------------------------------------------------------------------------------------------------------------------------
; EXERCISE OF USE OF union FUNCTION
;
; union(A, B) = A U B 
; Returns the union of two sets
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;
; In the second session, this function could have been implemented as:
;
; 1. Base        : the result over the empty list
;                  union((), B) = B
; 2. Recursion   : A is not an empty list; that is
;                  A = cons(car(A), cdr(A))
;     Hypothesis : assume we knoew union(cdr(A), B) = H
;      Thesis    : union(A, B) = if member(car(A), H)
;                                 then H
;                                 else cons(car(A), H)
;
; In Racket:
;
;(define (union A B)
;  (cond [(null? A) B]
;        [(member (car A) B)
;         (union (cdr A) B)]
;        [else (cons (car A)
;                    (union (cdr A) B))]))

; Exercise: provide a new version of the union(A, B) function by defining
; it with optional arguments. You should be firstly checking that the
; number of arguments is as expected and that they are valid (i.e. they
; are lists). In case they are not, the corresponding error message
; will be provided.
;
; Note: do not use SCHEME predefined HOF (such as filter, map, ...).


(define (union . args)
  (cond ((not (eq? 2 (length args))) (error "There should be at least 2 arguments")
        (not (list? (car args))) (error "The first argument is not a list")
        (not(list? (cadr args))) (error "The second argument is not a list")
        (else (cons (caar args) (union (cdar args) (cdr args)) )))
  )
)



(displayln "\nunion: ")
;(union '(c a x) 3)        ;=> error
;(union '(c a x))          ;=> error
(union '(c a x) '(a (a))) ;=> (c x a (a))


; Now that you have learnt how to implement checks on arguments and since argument validation
; is always done in the same way, it is more convenient to focus on the use of the HOF.
; Thus, from now on, you can define the requested functions under the assumption
; that the arguments to the functions are provided correctly, unless expressly stated otherwise.




;;;------------------------------------------------------------------------------------------------------------------------------------
;
; EXERCISES
;
;
; Unless stated otherwise, use Higher Order Functions (HOF) to defined the requested functions.
; Assume that the arguments are provided correctly (so no validation is needed).
; You can use SCHEME predefined HOFs.


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; union(A,B) = A U B
; Return the union of the two sets (now using SCHEME's HOFs)
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

(define (union A B)
  (append A (filter (lambda (x) (not (member x A))) B)))



(displayln "\nunion: ")
(union '(c a x) '(a (a))) ;=> (c x a (a))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Redefine the previous union function and add argument validation
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

;(displayln "\nunion: ")
;(union '(c a x) 3)       ;=> error
;(union '(c a x))         ;=> error
;(union '(c a x) '(a (a))) ;=> (c x a (a))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; subset?(A,B)
; Returns True if A is a subset of B and False otherwise.
; Note the use of ? in the name since it is a boolean function
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


(define (subset? A B)

  (null? (filter (lambda (x) (not (member x B))) A))

)

(displayln "\nsubset:")
(subset? '(c b) '(a x b d c y))    ;=> #t
(subset? '(c b) '(a x b d (c) y))  ;=> #f



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; subset-v2?(A,B)
; Returns True if A is a subset of B and False otherwise.
;
; KEY NOTE: do not use filter. You should be able to combine
; map and apply
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

(define (subset-v2? A B)

  (null? (map (lambda (x) (member x B)) A))

)

(displayln "\nsubset-v2?:")
(subset-v2? '(c b) '(a x b d c y))    ;=> #t
(subset-v2? '(c b) '(a x b d (c) y))  ;=> #f



;;;------------------------------------------------------------------------------------------------------------------------------------
;
; COMPLEMENTARY EXERCISES
;
;
;
;
; The function integers(x,y) returns the list of consecutive integers
; (a a+1 a+2 ... b-2 b-1), i.e. the integers in range [a,b), given the two integers
; a and b. The list will be empty if a>=b.
;
; You should use HOF and, if required, the function integers(x,y) to define
; the following functions:
;
; arith0-seq(r, n): returns the list (0 r 2*r ... (n-1)*r), i.e. the list of
;   the n first terms of the arithmetic sequence in which the first element
;   is 0 and the common difference is r.
;
; arith-seq(a, r, n): returns the list (a a+r a+2*r ... a+(n-1)*r), i.e. the
;   list of the n first terms of the arithmetic sequence in which the first
;   element is a and the common difference is r.
;
; arith-sum(a, r, n): returns the sum of the first n terms of the arithmetic
;   sequence that has a as the first element and r as the common difference.
;
; evens(a, b): returns the list of even numbers in the integer range [a,b].
;
; multiples(a, b, n): returns the list of the multiples of the integer number n
;   in the integer range [a,b]. If n=0 then all the integer numbers in the
;   range should be returned.

(define (integers x y)
  (if (>= x y)
      ()
      (cons x (integers (+ x 1) y))))

(displayln "\nintegers: ")
(integers -5 10) ;=> (-5 -4 -3 -2 -1 0 1 2 3 4 5 6 7 8 9)
(integers 1 6)   ;=> (1 2 3 4 5)

;;;;;;;;;;;;;;;;;;;;;


;(displayln "\narith0-seq: ")
;(arith0-seq 3 5)  ;=> (0 3 6 9 12)



;;;;;;;;;;;;;;;;;;;;;


;(displayln "\narith-seq: ")
;(arith-seq -7 3 5)   ;=> (-7 -4 -1 2 5)



;;;;;;;;;;;;;;;;;;;;;


;(displayln "\narith-sum: ")
;(arith-sum -7 3 5)   ;=> -5



;;;;;;;;;;;;;;;;;;;;;


;(displayln "\nevens: ")
;(evens -7 8)   ;=> (-6 -4 -2 0 2 4 6 8)



;;;;;;;;;;;;;;;;;;;;;


;(displayln "\nmultiples: ")
;(multiples -7 9 2)    ;=> (-6 -4 -2 0 2 4 6 8)
;(multiples -30 30 7)  ;=> (-28 -21 -14 -7 0 7 14 21 28)
;(multiples 1 10 0)    ;=> (1 2 3 4 5 6 7 8 9 10)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; EXERCISE
;
; change-if(l1, l2, f-cond)
;
; Define the recursive function change-if, which changes the values of
; a given list l1 (first parameter) by the element in the same position
; in the given list l2 (second parameter) when the condition given by
; f-cond (third parameter) is met over those two values.
;
; You should also add argument validation: the first two arguments should
; be lists of the same length, etc.
;


;(displayln "\nchange-if: ")
;(change-if '(1 2 4 8 16 32 64 128) '(2 3 4 5 6 7 8 9) <) ; => (2 3 4 8 16 32 64 128)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Redefine the previous function using HOF and name it HOF-cange-if.

 

;(displayln "\nHOF-change-if ");
;(HOF-change-if '(1 2 4 8 16 32 64 128) '(2 3 4 5 6 7 8 9) <) ; => (2 3 4 8 16 32 64 128)