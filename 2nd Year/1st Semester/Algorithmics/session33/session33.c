// ---------------------------------------------------------------------------------
//
//  ALGORITHMICS: IMPLEMENTATION ACCORDING TO THE BACKTRACKING SCHEMA DE LA SOLUCI�N DEL PROBLEMA DE
//              DESCOMPOSE A NUMBER N INTO M ADDENDS WITH N>=M>0, WHERE THE ADDITION OF THE M ADDENDS
//              HAS TO BE N AND THEIR PRODUCT IS MAXIMUM. IT IS ALSO GENERATED: ALL FEASIBLE
//              SOLUTIONS AND ONE FEASIBLE SOLUTION.
//
//
//  SESSION 3 OF ALGORITHMIC SCHEMAS
//
//  Date: 1st December 2023
//
// ----------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>
#include <stdbool.h>

// prototypes of backtracking functions
void DESCOMPOSE_BACK_ALL(int , int , int , int *);
void DESCOMPOSE_BACK_ONE(int , int , int , int *, bool*);
void process(int*, int);
int add(int*, int);
//void DESCOMPOSE_BACK_OPTIMAL(int , int , int , int *, int *, int *);

// prototypes of auxiliary functions to be used in backtracking: correct, value, process



// function main
int main(){
   int N,M;
   int *x,*x_best,v_best;
   bool flag;

   do{
      printf ("\nInput the number to be decomposed: ");
      scanf("%d",&N);
   }while(N<=0);

   do{
      printf ("\nInput the number of addends: ");
      scanf("%d",&M);
   }while(M>N);

   //
   // Vectors x and x_best have size M
   //

   x=(int*)malloc((M+1)*sizeof(int));
   //x_best=(int *)malloc((M+1)*sizeof(int));

   // Calculating all feasible solutions
   printf("\n\nALL FEASIBLE SOLUTIONS\n");
   DESCOMPOSE_BACK_ALL(N,M,1,x);

   // Calculating one feasible solution
   printf("\n\nONE FEASIBLE TUPLE:\n");
   flag=true;
   DESCOMPOSE_BACK_ONE(N,M,1,x,&flag);

   // Calculating the optimal solution
   printf("\n\nOPTIMAL SOLUTION:\n");
   v_best=INT_MIN;
   //DESCOMPOSE_BACK_OPTIMAL(N,M,1,x,x_best,&v_best);

   // Pull up the solution (secuence of optimal decisions and the maximum benefit)
   process(x_best,M);
   printf("\t\tThe maximum product according to the decomposition is: %d\n", v_best);

   // release memory
   free(x);
   free(x_best);

   return 0;
}


//
// BACKTRACKING
//

// definitions for auxiliary functions to be used in backtracking: correct, value and process
int add(int *x, int k) {
   int sum = 0;
   int i =0;
   while (i < k){
      sum += x[k];
   }
   return sum;
}

void process(int *x, int k){
   int i = 0;
   printf("<");
   while (i < k){
      printf("%d", x[k]);
   }
   printf(">");
}


void DESCOMPOSE_BACK_ALL(int N, int M, int k, int *x){
   while (x[k] < N){
      x[k] += 1;
      if(k = M && add(x,k) == N) {
         process(x, k);
      }
      if(k < M && add(x,k) < N){
         DESCOMPOSE_BACK_ALL(N,M,k + 1, x);
      }
   }
}

void DESCOMPOSE_BACK_ONE(int N, int M, int k, int *x, bool* flag){
   x[k]=0;
   while(x[k] < N && *flag == true){
      x[k] += 1;
      if(k == M && add(x,k) == N) {
         process(x, k);
         *flag = false;
      }
      if(k < M && add(x,k) < N){
         DESCOMPOSE_BACK_ALL(N,M,k + 1, x);
      }
   }
}


void DESCOMPOSE_BACK_OPTIMAL(int N, int M, int k, int *x, int *x_best, int *v_best){
   int i, aux;
   x[k] = 0;
   while(x[k] < N){

      x[k] += 1;
      if(k==M && add(x, k) ==M){
         aux = product(x,k);
         if(aux > *v_best){
            *v_best = aux;
            for(i = 1; i<=M; i++){
               x_best[i] = x[i];
            }
         }
      }
      if(k < M && add(x,k) < N)
         DESCOMPOSE_BACK_OPTIMAL(N,M,k+1,x, x_best, v_best);

   }
}
