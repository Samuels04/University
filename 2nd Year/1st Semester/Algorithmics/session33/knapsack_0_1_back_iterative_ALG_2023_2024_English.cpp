// -------------------------------------------------------------------
//
//  ALGORITHMICS: IMPLEMENTATION FOR THE KNAPSACK 0/1 PROBLEM
//
//              BACKTRACKING - iterative version
//
//  SESSION 2 ALGORITHMICS SCHEMAS
//
//  Date: 1st December 2023
//
// -------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>


// functions prototypes
int weight(int *,int *,int);
int value(int *,int *,int);
void process (int *, int);
void knapsack_ALL(int *, int , int );
void knapsack_ONE(int *, int , int);
void knapsack_OPTIMAL(int *, int *, int , int);

// main function
int main(){
int n,C,i;
int *P, *B;

printf ("\n\tInput the capacity for the knapsack: ");
scanf("%d",&C);

printf ("\n\tInput the number of objects: ");
scanf("%d",&n);
//
// Vectors P, B, x and x_best will have n+1 size with the aim of
// "using" the section [1..n] and ignoring position 0. Then, the
// implementation would correspond exactly with the algorithm one explain in the lecture
//
P=(int *)malloc((n+1)*sizeof(int));
B=(int *)malloc((n+1)*sizeof(int));

printf ("\n\n\t\t   WEIGHTS\n\n");

for(i=1;i<=n;i++){
   printf ("\n\tInput the weight for the object %d: ",i);
   scanf("%d",&P[i]);
}

printf ("\n\n\t\t BENEFITS\n\n");

for(i=1;i<=n;i++){
   printf ("\n\tInput the benefit for the object %d: ",i);
   scanf("%d",&B[i]);
}

// calling to ALL_FEASIBLE_SOLUTIONS
printf("\n\n\tALL FEASIBLE SOLUTIONS\n");
knapsack_ALL(P,n,C);

// calling to ONE_FEASIBLE
printf("\n\n\tONE FEASIBLE SOLUTION:\n");
knapsack_ONE(P,n,C);

// calling to the OPTIMAL
printf("\n\n\tOPTIMAL SOLUTION:\n");
knapsack_OPTIMAL(P,B,n,C);

// releasing memory
free(P);
free(B);
return 0;
}

// function to work out the weight linked to the sequence of decisions x
int weight(int *P,int *x,int k){
int i, total=0;
for (i=1;i<=k;i++) {
   total=total+x[i]*P[i];
  }
return total;
}

// function to work out the value linked to the sequence of decisions x
int value(int *B,int *x,int k){
int i, total=0;
for (i=1;i<=k;i++){
   total=total+x[i]*B[i];
  }
return total;
}

// function to display the sequence of decisions x
void process (int *x, int n){
int i;
printf("\n\t< ");
for (i=1;i<=n;i++){
   printf("%d ",x[i]);
   }
printf(">");
}

// backtracking function to provide ALL FEASIBLE SOLUTIONS
void knapsack_ALL(int *P, int n, int C){
  int k;
   //
  // Vector x will have n+1 size with the aim of "using" the
// section [1..n] and ignoring position 0. Then, the implementation
// would correspond exactly with the algorithm one explain in the lecture
//

  int *x=(int*)malloc((n+1)*sizeof(int));

  k=1;
  x[k]=-1;  // prepare_traversal_level_k
  while (k > 0){
    if (x[k] < 1){  // exist_sibling_level_k
      x[k]=x[k]+1; // next_sibling_level_k
      if (k==n && weight(P,x,k)<=C) process(x,n); // if solution(x,k) and correct(x,k)
      if (k<n && weight(P,x,k)<=C){ // if not solution(x,k) and correct(x,k)
        k=k+1;
        x[k]=-1;// prepare_traversal_level_k
      }
    }
    else k=k-1;
  }
  free(x);
}

// backtracking function to provide ONE FEASIBLE SOLUTION
void knapsack_ONE(int *P, int n, int C){
  bool flag;
  int k;
  //
  // Vector x will have n+1 size with the aim of "using" the
// section [1..n] and ignoring position 0. Then, the implementation
// would correspond exactly with the algorithm one explain in the lecture
//
  //
  int *x=(int*)malloc((n+1)*sizeof(int));

  k=1;
  flag=true;
  x[k]=-1;
  while (k > 0 && flag){
    if (x[k] < 1){
      x[k]=x[k]+1;
      if (k==n && weight(P,x,k)<=C){
          process(x,n);
          flag=false;
      }
      if (k<n && weight(P,x,k)<=C){
        k=k+1;
        x[k]=-1;
      }
    }
    else k=k-1;
  }
  free(x);
}

// backtracking function to provide the OPTIMAL SOLUTION
void knapsack_OPTIMAL(int *P, int *B, int n, int C){
  int aux,i,k,v_best;
  //
  // Vector x and x_best will have n+1 size with the aim of "using" the
// section [1..n] and ignoring position 0. Then, the implementation
// would correspond exactly with the algorithm one explain in the lecture
//
  int *x=(int*)malloc((n+1)*sizeof(int));
  int *x_best=(int*)malloc((n+1)*sizeof(int));

  k=1;
  v_best=INT_MIN;
  x[k]=-1;
  while (k > 0){
    if (x[k] < 1){
      x[k]=x[k]+1;
      if (k==n && weight(P,x,k)<=C){
        aux=value(B,x,k);
        if (aux > v_best){
          v_best=aux;
          //option 1
          //for(i=1;i<=n;i++)
          //   x_best[i]=x[i];

          //option 2
          //void *memcpy(void *s1, const void *s2, size_t n);
          //memcpy copy the first n bytes from the area of memory pointed by
          //s2 to the area of memory pointed by s1
          memcpy(x_best,x,(n+1)*sizeof(int));
        }
      }
      if (k<n && weight(P,x,k)<=C){
        k=k+1;
        x[k]=-1;
      }
    }
    else k=k-1;
  }
  //display the solution (the optimal sequence of decisions and the maximum benefit)
  process(x_best,n);
  printf("\t\tOptimal benefit: %d\n\n\n",v_best);
  free(x);
  free(x_best);
}
