// ---------------------------------------------------------------------------------
//
// ALGORITHMICS: LAB OF RECURSIVE ALGORITHMS DESIGN
//
// This file includes the implementation of a recursive algorithm in C to calculate
// the n-th power of a where a>=0 and n>=0
//
// Copyright: Maria Teresa González & Nahuel Costa
//
// Date: 20th October 2023
//
// ---------------------------------------------------------------------------------
//
// Complete the tasks by students in lab session 2.1 
//
//----------------------------------------------------------------------------------


#include <stdio.h>

// Function prototypes

int POWER(int,int);

int POWER_details(int,int);


int main() {
    int n,a;

    printf("\t\t CALCULATE THE N-TH POWER OF A NUMBER\n\n");

  
    do{
        printf(" Input the base a (a>=0): ");

        scanf("%d",&a);

    } while(a<0);

  
    do{
        printf(" Input the exponent n (n>=0): ");

        scanf("%d",&n);

    } while(n<0);

    printf("\n\n");
    printf(" --------\n");
    printf(" POWER\n");
    printf(" --------\n");
    printf(" The power %d-th of %d is: %d \n\n\n", n, a, POWER(a,n));
    printf(" ---------------------------------------------\n");
    printf(" POWER - displaying how it works inside \n");
    printf(" ---------------------------------------------\n");
    printf(" \n It is displayed the internal process: successive calls to the function, result in the base case and \n");
    printf(" partial results until the final result is achieved");
    printf("\n\n\n DESCENDING in the chain of calls ... (it is applied the SUCCESSOR FUNCTION until the base case is achieved)\n\n");
    printf("\n\nThe power %d-th of %d is: %d \n\n", n, a, POWER_details(a,n));


    printf("\t\t CALCULATE THE N-TH POWER OF 3\n\n");
  
    do{
        printf(" Input the exponent n (n>=0): ");

        scanf("%d",&n);

    } while(n<0);

    printf("The %d-th power of 3 is: %d\n\n", n, power3_details(n));


    printf("\t\t CALCULATE THE MCD OF TWO NUMBERS\n\n");

    do{
        printf("Input the first number a (a > 0)");

        scanf("%d", &a);
    }while(a <= 0);

    do{
        printf("Input the second number n (n > 0)");

        scanf("%d", &n);
    }while(n <= 0);

    printf("The MCD of %d and %d is: %d\n\n", a, n, MCD_details(a, n));


    printf("\t\t CALCULATE THE FACTORIAL OF A NUMBER\n\n");

    do {
        printf("Input the number n ");

        scanf("%d", &n);
    } while(n < 0);

    printf("The factorial of %d is: %d\n\n", n, factorial(n));


    printf("\t\t CALCULATE THE SEMIFACTORIAL OF A NUMBER\n\n");

    do {
        printf("Input the number n ");

        scanf("%d", &n);
    } while(n < 0);

    printf("The semifactorial of %d is: %d\n\n", n, semifactorial(n));

     
    printf("\t\t CALCULATE THE NUMBER OF DIGITS OF A NUMBER\n\n");

    do {
        printf("Input the number n ");

        scanf("%d", &n);
    } while(n < 0);

    printf("The number of digits of %d is: %d\n\n", n, number_digits(n));

     
    printf("\t\t ADD THE DIGITS OF A NUMBER\n\n");

    do {
        printf("Input the number n ");

        scanf("%d", &n);
    } while(n < 0);

    printf("The sum of the digits of %d is: %d\n\n", n, add_digits(n));

     
    printf("\t\t LET'S PLAY HOPSCOTCH\n\n");

    do {
        printf("Input the number n ");

        scanf("%d", &n);
    } while(n < 0);

    printf("The result of playing hopscotch with %d is: %d\n\n", n, hopscotch(n));


    printf("\t\t LET'S GUESS SOME PATHS IN A PLANE\n\n");

    do {
        printf("Input the x-coordinate");

        scanf("%d", &n);
    } while(n < 0);

    do {
        printf("Input the y-coordinate");

        scanf("%d", &a);
    } while(a < 0);

    printf("The the number of paths from the origin to (%d, %d) is %d\n\n", n, a, plane(n, a));

    return 0;
}


int POWER(int a, int n){

  if (n==0) return 1;

  else return POWER(a,n-1)*a;

}


int POWER_details(int a, int n){
  int p;

  if (n==0){

    printf("\tpower(%d,%d)\n\n",a,n);

    p=1;

    printf("\n\n BASE CASE and result\n\n");

    printf("\tpower(%d,%d) ---> result = %d\n\n",a,n,p);

    printf("\n\n ASCENDING in the chain of calls ... (it is applied the COMBINATION FUNCTION until the initial call is achieved)\n\n");

    return p;
  }

  else{

    printf("\tpower(%d,%d)\n",a,n);

    p=POWER_details(a,n-1)*a;

    printf("\tpower(%d,%d) ---> result = %d\n",a,n,p);

    return p;
  }
}


int power3(int n){
  
    if(n == 0)
        return 1;
    if(n == 1)
        return 3;
    else
        return 2*power3(n - 1) + 3*power3(n - 2);

}

int power3_details(int n){
  
    if(n == 0)
        printf("Now n is equal to %d\n", n);
        return 1;
    if(n == 1)
        return 3;
    else {
        printf("Now n is equal to %d\n", n);
        return 2*power3_details(n - 1) + 3*power3_details(n - 2);
    }
        
}

int MCD(int a, int b){

    if(a == b)
        return a;

    if (a > b)
        return MCD(a - b, b);
    else
        return MCD(a, b - a);

}

int MCD_details(int a, int b){

    if(a == b)
        printf("Now a is equal to %d and b is equal to %d\n", a, b);
        return a;

    if (a > b){
        printf("Now 'a' is equal to: %d and 'b' is equal to: %d\n", a, b);
        return MCD_details(a - b, b);
    }
    else{
        printf("Now 'a' is equal to: %d and 'b' is equal to: %d\n", a, b);
        return MCD_details(a, b - a);
    }

}

int factorial(int n){
    if (n == 0)
        return 1;

    else
        return n*factorial(n - 1);

}

int semifactorial(int n){

    if(n == 1 || n == 2)
        return n;

    if(n > 2)
        return semifactorial(n - 2)*n;

}

int number_digits(int n){

    if (n < 10)
        return 1;
    else 
        return number_digits(n/10);


}

int fibonacci(int n){
    if (n == 0 || n == 1)
        return 1;

    if (n > 2)
        return fibonacci(n - 1) + fibonacci(n - 2);

}

int add_digits(int n){

    if(n < 10)
        return n;
    
    else
        return n%10 + add_digits(n/10);
    
}

int hopscotch(int n){
    
    if(n <= 2)
        return n;
    else
        return hopscotch(n - 1) + hopscotch(n - 2);

}

int plane(int x, int y){

    if((x == 0 && y > 0) || (x > 0 && y == 0))
        return 1;

    else
        return plane(x - 1, y) + plane(x, y - 1);

}