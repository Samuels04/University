// ----------------------------------------------------------------------------
//
// Implementation in C for the algorithm turn
//
// ALGORITHMS: PRACTICE OF ANALYSIS OF ALGORITHMS
//
// Copyright: Raquel Cortina
// Date: 1st September 2019
//
// Program to measure the execution time with C implementation
//
// ------------------------------------------------------------------------------------------
//
// The library time.h provides a function to measure the time. It is named as clock.
//
// clock_t clock(void);
//
// This function returns the consumption time spends by the processor (in tics from the clock)
// until the call to the function clock is made. The time in seconds is determined doing the
// the division of the value returned by the function clock and the value from the
// macro CLOCKS_PER_SEC.
//
// The library stdlib.h provides the function rand which chooses a number randomly. If random number
// inside a specific range is required, for example between 0 and 1000, we will use
// the operator modulo (%) as follow:
//
// rand()%1000
//
// ------------------------------------------------------------------------------------------


#include <stdio.h>
#include <stdlib.h>
#include <time.h>


# define NUM_SIZES 12


# define REPETITIONS 100


int fillOut(int *, int);
int turn(int *, int);


int main()
{
 int i,j;
 clock_t time_start, time_final;


 int sizes[NUM_SIZES]={1000,2000,3000,4000,5000,6000,7000,8000,9000,10000, 11000,12000};


 printf("\n\nTURN A VECTOR\n\n");
 printf("Consumption time:\n\n\n");
 printf("\t\tSize\t\tTime\n\n");
 printf("\t\t-----\t\t------\n\n");



 for ( i = 0; i < NUM_SIZES; i++)
     {
     
     int *V = (int*)malloc(sizes[i]*sizeof(int));

     
     fillOut(V,sizes[i]);

     
     time_start = clock();

    
     for (j=1; j <= REPETITIONS; j++)
       
         turn(V, sizes[i]);

     
     time_final=clock();

     // Release the space allocated for the vector
     free(V);

     
     printf("\t\t%d\t\t%f\n", sizes[i],(time_final-time_start) /(double)CLOCKS_PER_SEC / REPETITIONS);
     }
  return 0;
}


int fillOut(int *V, int n)
{
    int i;
    for (i=1;i<=n;i++)
        V[i-1]=rand()%1000;
    return 0;
}


int turn(int *V,int n)
{
    int i, j, aux;

    for (i = 1; i < n -2; i++){

        for (j = 0; j <= n - i; j++) {

            aux = V[j-1];
            V[j-1] = V[j];
            V[j] = aux;

        }
        
    }
    return *V;
}
