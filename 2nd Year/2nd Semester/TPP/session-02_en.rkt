;;; *************
;;; * SESSION-02 *
;;; *************


(require mzlib/compat)         ; Import a library. In particular, it
                               ; allows us to use the function atom? which,
                               ; in other case, wouldn't be available


;;; Definition of functions, f(param1, param2, ...) sintax:
;;;
;;; (define (f param1 param2 ...) Sexp)
;;;
;;; Name of the function: f
;;; Parameters of the function: param1 param2 ...    (there may not be any)
;;; Body of the definition: any S-expression Sexp that can be evaluated
;;; Result of the call: the evaluation of the S-expression that results
;;; after substituting the parameters of the function on the S-expression Sexp
;;; by the arguments of the call.
;;;
;;; Examples:

(define (greetings) 'Hello_World!) ; definition
(greetings)                        ; call

(define (mysum a b) (+ a b))     ; definition
(mysum 3 -10)                    ; call


;;; Definition of recursive functions over lists
;;; -----------------------------------------------
;;;
;;; In the analysis by cases, the standard functional notation will be used:
;;; <name_function>(arg0, arg1, ...). For example, for f(l):
;;;
;;; 1. Base      : the result of the function on the empty list;
;;;                 f(()) ::= whatever corresponds depending on the problem
;;;
;;; 2. Recurrence: l is not the empty list; that is, l=cons(car(l), cdr(l))
;;;    Hypothesis: assume we know f(cdr(l)) = H
;;;        Thesis: obtain f(l) from the hypothesis H in combination with
;;;                 the element car(l) of the list l, which is not involved
;;;                 in the former hypothesis
;;;                 f(l) ::= properly combine car(l) and H
;;;

;;;------------------------------------------------------------------------
;;; Example: Define the function my-length(l), which returns the number of
;;; elements of a given list.
;
; 1. Base      : the result of the function for the empty list;
;                 my-length(()) ::= 0
;
; 2. Recurrence: l is not the empty list; that is, l = cons(car(l), cdr(l))
;    Hipothesis: assume we know my-length(cdr(l)) = H
;    Thesis    : my-lenhth(l) ::= H + 1
;
; On Racket:

(define (my-length l)
  (if [null? l]
      0
      (+ (my-length (cdr l)) 1)))

(displayln "\nmy-length:")
(my-length '(a (b c) d))           ; => 3
(my-length '((a b c) d (e (f g)))) ; => 3



;;;------------------------------------------------------------------------
;;; BUILD THE FOLLOWING RECURSIVE FUNCTIONS
;;;------------------------------------------------------------------------
;;; For every case, it is compulsory to make the case analysis.
;;; The base case/cases and the recurrence step should be established
;;; just as it has been done in the previous example.
;;;


;;;------------------------------------------------------------------------
;;; EXERCISE 1
;;;------------------------------------------------------------------------
;
; Define the recursive function compare(a, b) which, given two vectors
; (lists of numbers of the same length), returns the resulting list
; of comparing component by component both vectors. The comparisons are
; made as follows:
;   If a is the vector (A0 A1 ... An-1) and b is the vector (B0 B1 ... Bn-1),
;   the resulting list is (R0 R1 ... Rn-1), where each Ri (0≤i<n) is:
;                           
;                          |-1 if Ai<Bi
;                    Ri = <  0 if Ai=Bi
;                          | 1 if Ai>Bi
;                           
; Note: a single function must be defined
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

(define (compare a b)
  (if (null? a)
      '()
      (let ((head-a (car a))
            (head-b (car b))
            (rest-a (cdr a))
            (rest-b (cdr b)))
        (cons (cond ((< head-a head-b) -1)
                    ((= head-a head-b) 0)
                    ((> head-a head-b) 1))
              (compare rest-a rest-b))
      )
  )
)

; Example of use
;(displayln "compare:")
;(compare '(2.4 7 1 -4) '(3 7 -5.25 0)) ; => (-1 0 1 -1)



;;;------------------------------------------------------------------------
;;; EXERCISE 2
;;;------------------------------------------------------------------------
;
; Assuming that the elements of a list represent a set (remember: in a set
; there is no order and there are no repeated elements), define the
; function adjoin(x, A) that returns the set A + {x}
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

; Example of use
;(displayln "adjoin:")
;(adjoin '(a) '((b c) (d))) ; => ((b c) (d) (a))
;(adjoin 0 '(5 0 7 10)) ; => (5 7 10 0)

(define (adjoin x A)
  (cond ((null? A) x)
        ((eq? (car A) x) A)
        ((cons (car A) (adjoin x (cdr A))))
  )
)
;;;------------------------------------------------------------------------
;;; EXERCISE 3
;;;------------------------------------------------------------------------
;
; Define the function union(A,B) = A U B, which returns the union set of
; the two given sets.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;


; Example of use
;(displayln "union:")
;(union '((1) (2) (3)) '((2) (5))) ; => ((1) (3) (2) (5))
;(union '(a f c b) '(z a b c))     ; => (f z a b c)


(define (union a b)
  (cond ((null? a) b)
        ((null? b) a)
        ((member (car a) b) (union (cdr a) b))
        (cons (car a) (union (cdr a) b))
  )
)



;;;------------------------------------------------------------------------
;;; EXERCISE 4
;;;------------------------------------------------------------------------
;
; Define the function add(l, e, f) which, given a list l with its elements
; already sorted out in the order induced by function f and given a new
; element e, returns the orderder list that includes e. Function f has
; arity two and f(x,y) returns #t if x is sorted out before y and #f in
; any other case.
;
; Note: the predefined function sort should not be used.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

; Example of use
;(displayln "\nadd:")
;(add '(10 8 7.2 3 1) 5 >)         ; => (10 8 7.2 5 3 1)
;(add '(-12.5 -3 0 7 12 49) 7 <=)  ; => (-12.5 -3 0 7 7 12 49)

(define (add l e f)
  (if (null? l) (list e))
  (if (f e (car l))
      (cons e l)
      (cons (car l) (add (cdr l) e f))
  )

)


;;;------------------------------------------------------------------------
;;; COMPLEMENTARY EXERCISES
;;;------------------------------------------------------------------------
;;;

;;;------------------------------------------------------------------------
;;; EXERCISE 5
;;;------------------------------------------------------------------------
;
; Using the function add(l, e, f), define the function my-sort(l, f) which
; returns the sorted list resulting of applying the order induced by
; function f on the elements of list l.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

; Example of use
;(displayln "\nmy-sort:")
;(my-sort '(9 3.2 10 3.5 -13 5) >)  ; => (10 9 5 3.5 3.2 -13)


;;;------------------------------------------------------------------------
;;; EXERCISE 6
;;;------------------------------------------------------------------------
;
; Use the previous function (i.e. my-sort) to sort out the list of list ll
; (defined below), in the way in which the sublists are sorted out by their
; length, in increasing order. In the specific case in which two lists have
; got the same number of elements, the original order should be preserved.

(define ll '((a b c) (d) (e (f)) () ((a) b))) ; Definition of the list ll

; Example of use
(displayln (cons "\nLista ll:" ll))
;(my-sort ll length-comp)



;;;------------------------------------------------------------------------
;;; EXERCISE 7
;;;------------------------------------------------------------------------
;
; Define the function intersection(A,B), which returns the intersection
; set of the two given ones.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;

; Example of use
;(displayln "intersection:")
;(intersection '((1) (2) (3)) '((2) (5))) ; => ((2))
;(intersection '(a f c b) '(z a b c))     ; => (a c b)
