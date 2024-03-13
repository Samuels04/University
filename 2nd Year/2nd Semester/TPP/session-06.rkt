(require mzlib/compat racket/function)

; Whenever possible, use currification


;;;------------------------------------------------------------------------------------------------------------------------------------
; RECURSION EXAMPLE
;
; Recursively define the function addLast(l, l1, l2, ...) which, given a list l and a
; sequence l1, l2, ..., returns the list of lists (l1' l2' ...), where li' is the
; concatenation of li with l.
; -----------------------------------------------------------------------------------------
;
; Given that l1, l2, ... is a sequence of an undetermined number of lists and all of
; them should be concatenated to list l, which is a fixed argument, the recursion is
; going to be established over the list of optional arguments (lists).
;
; 1. Base       : lists is an empty list addLast(l) = ()
; 2. Recurrencia: lists is not an empty list lists = cons(car(lists), cdr(lists))
;                    For instance, if lists = (l1 l2 l3 ...), then car(lists)=l1 and cdr(lists)=(l2 l3 ...)
;     Hypothesis: assume we know addLast(l, l2, l3, ...); that is, apply(addLast, cons(car(l), cdr(lists))) = H
;         Thesis: addLast(l, l1, l2, ...) = cons(append(car(lists)), H)

(define (addLast l . lists)
  (cond [(null? lists) lists]
        [else (cons (append (car lists) l)
                    (apply addLast (cons l (cdr lists))))]))

(display "addLast: ")
(addLast '((a) b) () '(c) '(1 (2 3))) ;=> (((a) b) (c (a) b) (1 (2 3) (a) b))

(display "addLast: ")
(addLast '((a) b) () '(c) '(1 (2 3))) ;=> (((a) b) (c (a) b) (1 (2 3) (a) b))




;;;------------------------------------------------------------------------------------------------------------------------------------
; EXERCISES ON ADDLAST

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function addLast-HOF(l, l1, l2, ...), equivalent to
;  the previous one but just using HOF.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (addLast-HOF l . lists)

  (map (curryr append l) lists)

)

(display "addLast-HOF: ")
(addLast-HOF '((a) b) () '(c) '(1 (2 3))) ;=> (((a) b) (c (a) b) (1 (2 3) (a) b))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Using HOF, define the function addLast+(ll, l1, l2, ...) which,
;  given a list of lists ll and a sequence of lists l1, l2, ...,
;  returns the list of lists (l1', l2', ...), where li' is the
;  concatenation of li with each of the lists in ll (in the same
;  order as they appear in ll).
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (addLast+ ll . lists)

  (map append (map (curryr cons ll) lists))

)



(display "addLast+: ")
(addLast+ '((a) ((b) c)) () '(d) '(1 (2 3))) ;=> ((a (b) c) (d a (b) c) (1 (2 3) a (b) c))




;;;------------------------------------------------------------------------------------------------------------------------------------
; FIRST BLOCK OF EXERCISES
;
; Use HOF to define the requested function, given the definition of the symbol Hands.
;  This symbol contains the information about the situation of a UNO game in a
;  given moment.
;
; All the cards of the UNO deck have a numeric value between 0 and 9 or a symbolic
;  value corresponding to the associated action: take2, reverse, lose-turn,
;  colour-wildcard and take4-wildcard. In addition, each card has a colour associated:
;  blue, red, green or yellow, with the exception of the wildcards (which can be used
;  in any situation). Thus, each card will be represented by the list of two elements
;  (value colour), with the exception of the wildcards, that will be represented by
;  a list with a single element (their value).

(define Hands
  '((ana    ((4 red) (9 red) (9 green) (colour-wildcard) (9 green) (8 green) (3 blue)))
    (rosa   ((0 yellow) (1 yellow) (3 red) (8 blue) (4 green) (3 red) (9 blue)))
    (luis   ((colour-wildcard) (8 yellow) (5 red) (7 yellow) (5 green) (4 yellow)))
    (pedro  ((7 yellow) (1 red) (4 green) (9 red) (7 red) (6 yellow) (4 red)))
    (maria  ((7 green) (8 yellow) (0 red) (take4-wildcard) (3 green) (8 green) (3 blue)))
    (carmen ((8 blue) (5 green) (5 yellow) (6 yellow) (3 yellow) (6 blue) (lose-turn blue)))
    (blanca ((3 yellow) (1 red) (5 yellow) (4 yellow) (2 blue) (9 blue) (7 blue)))
    (quique ((9 yellow) (reverse red) (2 red) (6 green) (8 red) (1 blue) (1 green)))))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function cards-in-hand(name, hands) which, given the name
; of the player and the hands data return the cards of the player.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(display "cards-in-hand: ")
;(cards-in-hand 'maria Hands) ;=> ((7 green) (8 yellow) (0 red) (colour-wildcard) (3 green) (8 green) (3 blue))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function wildcard?(cards-hand) which return whether
; the player has got a wildcard in his/her hand or not.
;
; Note: there are two types of wildcards: colour-wildcard
; and take4-wildcard.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(display "wildcard? (luis): ")
;(wildcard? (cards-in-hand 'luis Hands)) ;=> #t
;(display "wildcard? (maria): ")
;(wildcard? (cards-in-hand 'maria Hands)) ;=> #t
;(display "wildcard? (quique): ")
;(wildcard? (cards-in-hand 'quique Hands)) ;=> #f



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function compatible-value(value, cards-hand) which
;  returns the list of cards from a hand that are playable according
;  to their value (numeric or symbolic) and the given one.
;
; A card is playable if it has got the same value or if it is
;  a wildcard.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(display "compatible-value (9, ana): ")
;(compatible-value 9 (cards-in-hand 'ana Hands)) ;=> ((9 red) (9 green) (9 green) (colour-wildcard))
;(display "compatible-value (8, maria): ")
;(compatible-value 8 (cards-in-hand 'maria Hands)) ;=> ((8 yellow) (8 green) (take4-wildcard))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function compatible-colour(colour, cards-hand) which
;  returns the list of cards from a hand that are playable according
;  to their colour and the given one.
;
; A card is playable if its colour is the same or if it is
;  a wildcard.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(display "compatible-colour: ")
;(compatible-colour 'green (cards-in-hand 'maria Hands)) ;=> ((7 green) (3 green) (8 green) (take4-wildcard))
;(display "compatible-colour: ")
;(compatible-colour 'red (cards-in-hand 'pedro Hands)) ;=> ((1 red) (9 red) (7 red) (4 red))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function compatibale(card, cards-hand) which returns
;  the list of cards that are compatible with the given card
;  (compatible with its colour or its value). The argument card
;  will always be a list with two elements (value, colour), even if
;  the card is a wildcard, when the colour will be the choosen one
;  to continue with the game.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(display "compatible: ")
;(compatible '(9 red)
;             (cards-in-hand 'ana Hands)) ;=> ((4 red) (9 red) (9 green) (colour-wildcard) (9 green))
;(display "compatible: ")
;(compatible '(colour-wildcard green)
;             (cards-in-hand 'maria Hands)) ;=> ((7 green) (take4-wildcard) (3 green) (8 green))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function players-with-wildcards(hands) which returns
;  the list of the names of the players that hold a wildcard.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(display "players-with-wildcards: ")
;(players-with-wildcards Hands) ;=> (ana luis maria)



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Define the function players-with-compatibles(card, hands) which
;  returns, for each player, the cards that he/she holds and that
;  are compatible with the given one. Again, the argument card is
;  always going to be a list with two values (value, colour).
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(displayln "players-with-compatibles:")
;(players-with-compatibles '(0 red) Hands)
;=> ((ana ((4 red) (9 red) (colour-wildcard)))
;    (rosa ((0 yellow) (3 red) (3 red)))
;    (luis ((colour-wildcard) (5 red)))
;    (pedro ((1 red) (9 red) (7 red) (4 red)))
;    (maria ((0 red) (take4-wildcard)))
;    (carmen ())
;    (blanca ((1 red)))
;    (quique ((reverse red) (2 red) (8 red))))

;(players-with-compatibles '(colour-wildcard green) Hands)
;=> ((ana ((9 green) (colour-wildcard) (9 green) (8 green)))
;    (rosa ((4 green)))
;    (luis ((colour-wildcard) (5 green)))
;    (pedro ((4 green)))
;    (maria ((7 green) (take4-wildcard) (3 green) (8 green)))
;    (carmen ((5 green)))
;    (blanca ())
;    (quique ((6 green) (1 green))))



;;;------------------------------------------------------------------------------------------------------------------------------------
; SECOND BLOCK OF EXERCISES
;
; The symbol Vectors holds a list of vectors of variable dimensions in the following
;  format: (vector-name list-of-coordinates). Implement the requested functions.
;;------------------------------------------------------------------------------------

(define Vectors
  '(v1 (12 2.5 -8 24 33)
    v2 (1 -2 3 0 7 -2.3 0 21)
    v3 (-12 2.8 3.5)
    v4 (-1 2 -2.3 5)
    v5 (-12.5 -3 8 5 24 0 -3 12)
    v6 (3 0 18 15 9 12.5)))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Recursively define the function sum(l-names, l-vectors), which
;  returns the sum of the coordinates of the vectors in l-vectors
;  whose name is contained in l-names.
;
; Note: avoid repeating any calculations.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(define (sum l-names l-vectors)



;);;



;(display "\nsum('(v2 v3 v1 v7) Vectors): ")
;(sum '(v2 v3 v1 v7) Vectors) ;=> 85.5



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Resursively define the function product(x, l-names, l-vectors),
;  which returns the list of vectors obtained after multipliying by
;  x each coordinate of the vectors of l-vectors whose name is
;  contained in l-names.
;
; The function by-scalar(x, vector-coordinate) is been provided in
;  order to return the vector resulting of multiplying each
;  coordinate by x.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(define (by-scalar x vector-coordinates)
  (map (curry * x) vector-coordinates))


;(displayln "\nproduct(0.5, (v1 v7 v3), Vectors):")
;(product 0.5 '(v1 v7 v3) Vectors) ;=> (v1 (6.0 1.25 -4.0 12.0 16.5) v3 (-6.0 1.4 1.75))




; In the example above, product(0.5, (v1 v7 v3), Vectors), the resulting vectors
;  are named v1 and v3. However, these resulting vectors do not have the same
;  coordinates as the original ones given in the Vectors list.
;
; Modify the function product(x, l-names, l-Vectors) so that the resulting Vectors
;  are named in the form x*vi. To do this, you may use the following predefined
;  functions:
;    symbol->string(symbol) returns the string corresponding to the given symbol
;    number->string(number) returns the string corresponding to the specified number
;    string->symbol(string) returns the symbol corresponding to the given string
;    string-append(s1, s2, ...) returns the concatenation of the specified strings


;(displayln "\nproduct(0.5, (v1 v7 v3), Vectors) - improved:")
;(product 0.5 '(v1 v7 v3) Vectors) ;=> (0.5*v1 (6.0 1.25 -4.0 12.0 16.5) 0.5*v3 (-6.0 1.4 1.75))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Using HOF, define the symbol SumCompVectors, that holds the
;  overall sum of all the components of each vector in the
;  list Vectors.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



;(display "\nSum of all the components of all vectors: ") ;=> 177.2
;(displayln SumCompVectors)




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Using HOF, define the symbol NumberCoordinates, which holds, for
;  each vector, a list of two elements (vector-name
;  number-coordinates). That is, the list of the number of coordinates
;  for each vector in the original Vectors list.
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;(display "\nNumber of coordinates: ") ;=> ((v1 5) (v2 8) (v3 3) (v4 4) (v5 8) (v6 6))
;(displayln NumberCoordinates)


