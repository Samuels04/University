;; FUNCTIONAL PROGRAMMING
;; HIGHER ORDER FUNCTIONS - lambda expressions, filters

;;
;; The symbol Data provides data about people with the following format:
;;
;;   ( (Name Surname1 Surname2 Age Sex (studies) Works?)
;;      ...
;;    )
;;
;; Biological sex: M (male) or F (female)
;; Studies: list of the person's higher education studies
;; Works?: #t or #f
;;

(define Data '(
               (LUIS GARCIA PEREZ 26 H (MEDICINE COMPUTER_SCIENCE) #t)
               (MARIA LUZ DIVINA 23 M (COMPUTER_SCIENCE BIOLOGY) #t)
               (ADOLFO MONTES PELADOS 24 H (COMPUTER_SCIENCE) #f)
               (ANA GARCIA GONZALEZ 18 M () #f)
               (JOSE PEREZ MONTES 36 H () #t)
               (JOSHUA IGLESIAS GARCIA 12 H () #f)
               (MARUJA FERNANDEZ GARCIA 9 M () #f)
               (ROSA LINDO SUAREZ 29 M (ECONOMY MEDICINE) #t)
               (GUILLERMO PUERTAS VENTANAS 42 H (ECONOMY) #f)
               ))

(define data-person (cadr Data))
(display "Example of the data of a single person: ")
(writeln data-person)




;; Additionally, the following functions for accessing the information of a person are provided:


;; get-name(p) returns the name of the person
(define (get-name p)
  (car p))

(display "get-name: ")
(get-name data-person)  ;=> MARIA


;; get-surnames(p) returns a list with both surnames of the person
(define (get-surnames p)
  (list (cadr p) (caddr p)))

(display "get-surnames: ")
(get-surnames data-person)  ;=> (LUZ DIVINA)

;; get-full-name(p) returns a list with the name and both surnames of the person
(define (get-full-name p)
  (cons (get-name p) (get-surnames p)))

(display "get-full-name: ")
(get-full-name data-person) ; => (MARIA LUZ DIVINA)


;; get-age(p) returns the age of the person
(define (get-age p)
  (cadddr p))

(display "get-age: ")
(get-age data-person)  ;=> 23


;; get-sex(p) returns the biological sex of the person
(define (get-sex p)
  (car (cddddr p)))

(display "get-sex: ")
(get-sex data-person) ; => F


;; get-studies(p) retuns the list of the higher education studies of the person
(define (get-studies p)
  (cadr (cddddr p)))

(display "get-studies: ")
(get-studies data-person)  ;=> (COMPUTER_SCIENTIST)


;; works?(p) returns whether the person is working or not
(define (works? p)
  (caddr (cddddr p)))

(display "works? ")
(works? data-person)  ;=> #t



;;----------------------------------------------------------------------
;; 1 - Applying the function Extract
;;----------------------------------------------------------------------
;; Extract(Data, Filter, Format) => (...)
;;
;; Higher order function that receives:
;; Data                     : the data to be analysed
;; Filter(Person)  => #t/#f : a filter function to be applied on every person.
;;                                Note: this does not refer to SCHEME's filter function.
;; Format(Person) => (...)  : a function returning the relevant information
;;                                of a person
;;
;;
;; The function Extract returns the list of the elements of Data which satisfy Filter
;; and formatted via Format. In general, the Filter function, as well as the Format
;; one, will be lambda functions. However, in convinient cases the already defined access
;; functions can also be use.


;; Recursive definition of Extract
;  1. Base        : Extract((), Filter, Format) = ()
;  2. Recurrence  : Data is not () => Data = cons(car(Data), cdr(Data))
;       Hypothesis: We know Extract(cdr(Data), Filter, Format) = H
;       Thesis    : Extract(Data, Filter, Format) =
;                                  if Filter(car(Data))
;                                    then cons(Format(car(Data)), H)
;                                    else H

(define (Extract Data Filter Format)
  (cond
    ([null? Data] Data)
    ([Filter (car Data)] (cons (Format (car Data))
                               (Extract (cdr Data) Filter Format)))
    (else (Extract (cdr Data) Filter Format))))




;;-----------------------------------------------------
;; EXERCISES
;; Use the function Extract with lambda functions for Filter and Format or, if needed, the
;; previously defined access functions, in order to obtain from Data the following information:
(displayln "\n------------------------------------------\nEXERCISE 1: Extract(Data, Filter, Format)")

; a) Example: The list of the names of the adults
(displayln "\na) Example. The list of the names of the adults:")
(displayln "Extract(Data, p -> get_age(p) >= 18, get-name")

(Extract Data (lambda (p) (>= (get-age p) 18)) get-name) ; => (LUIS MARIA ADOLFO ANA JOSE)

;; Notice that the function Extract does two processings over the data: Filtering and then
;; Formatting the already filtered data. Observe that using the predefined function 'filter' with
;; the same filtering function, filters exactly the same data, but provides all the known
;; information related to them:

(displayln "\nThe predefined function filter does filter the same people, but returns all the information of them: ")
(displayln "filter(p -> get_age(p) >= 18, Data")

(filter (lambda (p) (>= (get-age p) 18)) Data)
;=> ((LUIS GARCIA PEREZ 26 V (COMPUTER_SCIENCE MEDICINE) #t)
;    (MARIA LUZ DIVINA 23 M (COMPUTER_SCIENCE) #t)
;    (ADOLFO MONTES PELADOS 24 V (COMPUTER_SCIENCE) #f)
;    (ANA GARCIA GONZALEZ 22 M () #f)
;    (JOSE PEREZ MONTES 36 V () #t))
;    (GUILLERMO PUERTAS VENTANAS 42 H (ECONOMY) #f)


;; And so, in order to obtain the desired information of the returned data (in this case, their names),
;; further processing would be needed (the formatting performed by Extract).


; b) The list of the full names of all the people
(displayln "\nb) List of the full names of all the people:")

(Extract Data (lambda (p) p) get-full-name)

; c) The list of names of all the women who work
(displayln "\nc) List of the names of all the women who work:")

(Extract Data (lambda (p) (and (eq? (works? p) #t) (eq? (get-sex p) 'M))) get-name)

; d) The list of the full names of all the people who have Computer Science studies
(displayln "\nd) List of the full names of all the people who have Computer Science studies:")

(Extract Data (lambda (p) (member 'COMPUTER_SCIENCE (get-studies p))) get-full-name)


; e) The list of pairs (gender age), as a list of two elements, of every person without studies
(displayln "\ne) List of gender and age of every person without studies:")

(Extract Data (lambda (p) (null? (get-studies p))) (lambda (p) (cons (get-sex p) (get-age p))))

; f) The list of ages of all the women who have Computer Science studies
(displayln "\nf) List of the ages of all the women who have Computer Science studies:")

(Extract Data (lambda (p)(and (eq? (get-sex p) 'M)(member 'COMPUTER_SCIENCE (get-studies p)))) get-age)

; g) The list of the full names of all the people that do not work
(displayln "\ng) List of the full names of all the people that do not work:")

(Extract Data (lambda (p) (eq? (works? p) #f)) get-full-name)


;;----------------------------------------------------------------------
;: 2 - FUNCTION gen-filter
;;----------------------------------------------------------------------
;; gen-filter::(A -> B) x (C x B -> BOOL) x C -> (A -> BOOL)
;; gen-filter(Extractor Operator Value) => lambda(Person)
;;
;; This Higher Order Function returns a lambda(p) function to be applied 
;; as a filter when using Extract. Its parameters are:
;; 
;; Extractor: Function that will extract from the information of the person the data to be checked 
;; Operator: Functions that will compare the Value with the output of the Extractor function
;; Value: constant value to which the output of the Extractor function is compared with


(define (gen-filter Extractor Operator Value)
  (lambda (p) (Operator Value (Extractor p)))) ; The novelty is that the function returns another function


;;-----------------------------------------------------
;; EXERCISES
;; Using gen-filter, define the filters used in the previous exercises and
;; then use the Extract function with the generated filter
(displayln "\n------------------------------------------\nEXERCISE 2: gen-filter(Extractor, Operator, Value)")


; a) Example: The list of the names of the adults
(displayln "\na) Example. The list of the names of the adults:")
(displayln "gen-filter(get-age <= 18) => p -> 18 <= get-age(p)")

(Extract Data                       ; Data of the people
         (gen-filter get-age <= 18) ; Filter: generated filter to only return the adult people
         get-name)                  ; Format



; b) The list of the full names of all the people
(displayln "\nb) List of the full names of all the people:")

(Extract Data (gen-filter get-age > 1000) get-full-name)

; c) The list of names of all the women who work
(displayln "\nc) List of the names of all the women who work:")

(Extract Data (gen-filter (lambda (p) (and (eq? (get-sex p) 'M)(eq? (works? p) #t))) eq? #t)  get-name)

; d) The list of the full names of all the people who have Computer Science studies
(displayln "\nd) List of the full names of all the people who have Computer Science studies:")

(Extract Data (gen-filter get-studies member 'COMPUTER_SCIENCE) get-full-name)

; e) The list of pairs (gender age), as a list of two elements, of every person without studies
(displayln "\ne) List of gender and age of every person without studies:")

(Extract Data (gen-filter (lambda (p) (null? (get-studies p))) eq? #t)(lambda (p)(cons (get-sex p)(get-age p))))

; f) The list of ages of all the women who have Computer Science studies
(displayln "\nf) List of the ages of all the women who have Computer Science studies:")

(Extract Data (gen-filter (lambda (p) (and (eq? (get-sex p) 'M)(member 'COMPUTER_SCIENCE (get-studies p)))) eq? #t) get-age)

; g) The list of the full names of all the people that do not work
(displayln "\ng) List of the full names of all the people that do not work:")

(Extract Data (gen-filter works? eq? #f) get-full-name)



;;;----------------------------------------------------------------------------------------
;;; COMPLEMENTARY EXERCISES
;;;----------------------------------------------------------------------------------------
;;;
(displayln "\n------------------------------------------\nCOMPLEMENTARY EXERCISES")

(define numbers
  '((n1 (3 7 3))(n2 (3 4 9 0 1))(n3 (3 0 3 4)) (n4 (7))))

;; Having defined the symbol numbers as above (a list of numbers given
;; by a list of its digits and their associated name), use the function
;; filter to obtain the requested numbers.

; a) Obtain the numbers with more than 3 digits
(displayln "\na) List of numbers with more than 3 digits:") ;; => ((n2 (3 4 9 0 1)) (n3 (3 0 3 4)))



; b) Obtain every number containing a 7
(displayln "\nb) List of numbers containing a 7:") ;; => ((n1 (3 7 3)) (n4 (7)))



; c) Obtain every number whose first digit is a 3
(displayln "\nc) List numbers whose first digit is a 3:") ;; => ((n1 (3 7 3)) (n2 (3 4 9 0 1)) (n3 (3 0 3 4)))



; d) Obtain all the numbers whose sum of digits is lower than 12
(displayln "\nd) List of numbers whose sum of digits is lower than 12:") ;; => ((n3 (3 0 3 4)) (n4 (7)))




;------------------------------------------------------
; Define, using HOF, the function frequency(x, l) that
; returns the number of repetitions of x in the list l
;------------------------------------------------------
;



(displayln "\nFrequency ")
;(frequency '(a) '(a b (a) a d (a))) ; => 2



;------------------------------------------------------
; Define the recursive function filtered(f, L) that, given
; a one-parameter boolean function and a list L,
; returns the list of the elements of L that satisfy f
;-------------------------------------------------------
;


(displayln "\nFiltered recursively: ")
;(filtered list? '(1 (2) ((a)) 3))  ; => ((2) ((a)))


;------------------------------------------------------
; Define the recursive function for-all(f, L) that returns
; the resulting list of applying the function f to every
; element of the list
;-------------------------------------------------------
;


(displayln "\nFor-all: ")
;(for-all cadr '((a b) ((a) c) (d (e)))) ;  => (b c (e))



;----------------------------------------------------------------------
; Using the functions given at the beginning (that extract information
; of the defined symbol Data), define the function person?(p)
; that allows checking that the the given information for a person
; is correct. The information is correct according to the way the
; information of people is provided in Data).
; Among other conditions: it must be a list of seven elements,
; the studies must have been provided as a list...
;
; Check every condition that you consider relevant. At least a relevant
; condition must be checked for each item.
;----------------------------------------------------------------------
;



;(person? '(MARIA LUZ DIVINA 23 M (INFORMATICA) #t))    ; => #t
;(person? '(MARIA LUZ DIVINA 2 3 M (INFORMATICA) #t))   ; => #f
;(person? '(MARIA (LUZ DIVINA) 23 M (INFORMATICA) #t))  ; => #f
;(person? '(MARIA LUZ DIVINA 23 M INFORMATICA #t))      ; => #f
;(person? '(MARIA LUZ DIVINA 23 H (INFORMATICA) #t))    ; => #t

