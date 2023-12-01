// -------------------------------------------------------------------
//
//  ALGORITHMICS: IMPLEMENTATION FOR THE KNAPSACK 0/1 PROBLEM
//
//              BACKTRACKING - recursive version
//
//  SESSION 2 ALGORITHMIC SCHEMAS
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
void knapsack_ALL(int *, int , int , int , int *);
void knapsack_ONE(int *, int , int , int , int *, bool *);
void knapsack_OPTIMAL(int *, int *, int , int , int , int *, int *, int *);

// main function
int main(){
int n,C,i;
int *x,*x_best,v_best,*P, *B;
bool flag;

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
x=(int*)malloc((n+1)*sizeof(int));
x_best=(int *)malloc((n+1)*sizeof(int));

printf ("\n\n\t\t WEIGHTS\n\n");

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
knapsack_ALL(P,n,C,1,x);

// calling to ONE_FEASIBLE
printf("\n\n\tONE FEASIBLE SOLUTION:\n");
flag=true;
knapsack_ONE(P,n,C,1,x,&flag);

// calling to the OPTIMAL
printf("\n\n\tOPTIMAL SOLUTION:\n");
v_best=INT_MIN;
knapsack_OPTIMAL(P,B,n,C,1,x,x_best,&v_best);

// display the solution (optimal sequence of decisions and maximum benefit)
process(x_best,n);
printf("\t\tOptimal benefit: %d\n\n\n\n", v_best);

// Release memory
free(x);
free(x_best);
free(P);
free(B);
return 0;
}

// function to work out the weight linked to the sequence of decisions x
int weight(int *P,int *x,int k){
int i, total=0;
for (i=1;i<=k;i++){
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
void knapsack_ALL(int *P, int n, int C, int k, int *x){
  x[k]=-1;    // prepare_traversal_level_k
  while (x[k]<1){  // exist_sibling_level_k
        x[k]=x[k]+1; // next_sibling_level_k
        if (k==n && (weight(P,x,k)<=C)) process(x,n); // if solution(x,k) and correct(x,k)
        if (k<n && (weight(P,x,k)<=C)) knapsack_ALL(P,n,C,k+1,x);// if not solution(x,k) and correct(x,k)
       }
}

// backtracking function to provide ONE FEASIBLE SOLUTION
void knapsack_ONE(int *P, int n, int C, int k, int *x, bool *flag){
  x[k]=-1;
  while (x[k]<1 && *flag==true){
        x[k]=x[k]+1;
        if (k==n && weight(P,x,k)<=C){
                                        *flag=false;
                                        process(x,n);
                                   }
        if (k<n && weight(P,x,k)<=C) knapsack_ONE(P,n,C,k+1,x,flag);
       }
}

// backtracking function to provide the OPTIMAL SOLUTION
void knapsack_OPTIMAL(int *P, int *B, int n, int C, int k, int *x, int *x_best, int *v_best){
  int i,aux;
  x[k]=-1;
  while (x[k]<1){
                   x[k]=x[k]+1;
                   if (k==n && weight(P,x,k)<=C){
                                                aux=value(B,x,k);
                                                if (aux > *v_best){
                                                                    *v_best=aux;
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
                   if (k<n && weight(P,x,k)<=C) knapsack_OPTIMAL(P,B,n,C,k+1,x,x_best,v_best);
               }
}

