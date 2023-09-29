#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <stdbool.h>

# define NUM_SIZES 10
# define REPITERA 100000
# define REPRECUR 10000
# define REPSEARCH 1000

// Functions prototypes
int fillOut(int **, int, int);
int AddIter(int **, int);
int AddRecur(int **, int);
bool Search(int **, int, int);

// Main program
int main()
{
  int i,j;

  clock_t time_ini1, time_ini2, time_fin1, time_fin2;

  int sizes[NUM_SIZES]={1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};

  int **Matrix = NULL, AddIterResult = 0, AddRecurResult = 0; 


  printf("\n\n\t\tLAB SESSION 2 - ANALYSIS OF ALGORITHMS  \n\n");

  printf("Time used:\n\n\n");


  // STUDENT: COMMENT/UNCOMMENT THE NEXT printf ACCORDING TO THE MEASURES YOU NEED TO TAKE


  //printf("\t\tsizes\t\tTimeBest case\tTime Worst case\n");

  //printf("\t\t-----\t\t----------------\t----------------\n");


  printf("\t\tsizes\t\tIterative Time\tRecursive Time\n");

  printf("\t\t-----\t\t----------------\t----------------\n");

  for (i = 0; i < NUM_SIZES; i++) {

    Matrix = (int**) malloc (sizes[i] * sizeof(int*));

    for(int j = 0; j < sizes[i]; j++){

      Matrix[j] = (int*) malloc (sizes[i] * sizeof(int*));

    }


    int valueToFill = 2;

    fillOut(Matrix,sizes[i], valueToFill);


    // SNIPPET OF CODE TO ANALYSE THE SEARCH IN THE MATRIX

    /*
    time_ini1=clock();

    for (j = 1; j <= REPSEARCH; j++)
      Search(Matrix, sizes[i], valueToFill);

    time_fin1 = clock();


    time_ini2 = clock();

    for (j = 1; j <= REPSEARCH; j++)
      Search(Matrix, sizes[i], valueToFill + 1);

    time_fin2 = clock();


    printf("\t\t%d\t\t%f", sizes[i],(time_fin1-time_ini1) /(double)CLOCKS_PER_SEC / REPSEARCH);
    printf("\t\t%f\n",              (time_fin2-time_ini2) /(double)CLOCKS_PER_SEC / REPSEARCH);

    */

    // SNIPPET OF CODE TO ANALYSE THE ADDITION OF THE ELEMENTS IN THE DIAGONAL OF THE MATRIX
    

    time_ini1 = clock();

    for (j = 1; j <= REPITERA; j++)
      AddIterResult = AddIter(Matrix, sizes[i]);

    time_fin1 = clock();


    time_ini2 = clock();

    for (j = 1 ;j <= REPRECUR; j++)
      AddRecurResult = AddRecur(Matrix, sizes[i]);

    time_fin2 = clock();


    if (AddRecurResult != AddIterResult) { printf("Inconsistency in the addition\n\n"); return -1; }


    printf("\t\t%d\t\t%f", sizes[i],(time_fin1-time_ini1) /(double)CLOCKS_PER_SEC / REPITERA);
   
    printf("\t\t%f\n",              (time_fin2-time_ini2) /(double)CLOCKS_PER_SEC / REPRECUR);

  
    for(int j = 0; j < sizes[i]; j++)
      free(Matrix[j]);
    
    free(Matrix);

  }
  return 0;
}


// Functions definition
int fillOut(int **M, int n, int m){

  for(int i = 0; i < n; i++)

    for(int j = 0; j < n; j++)

      M[i][j] = m;

}

bool Search(int **M, int n, int x){

  int i,j;

  bool found;

  i = 0;

  found = false;

  while(i < n && !found){

    i++;

    j = 0;

    while(j < n && !found){

      j++;

      if (M[i - 1][j - 1] == x)
        found = true;
      
    }

  }

  return found;

}

int AddIter(int**M, int n){

  int i, sum;

  sum = 0;

  for(int i = 0; i < n; i++)

    sum += M[i][i];

  return sum;

}

int AddRecur(int **M, int n){

  if(n == 1)

    return M[0][0];

  else 

    return M[n - 1][n - 1] + AddRecur(M, n - 1);

}
