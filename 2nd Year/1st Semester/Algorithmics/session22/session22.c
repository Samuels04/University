#include <stdio.h>
#include <stdbool.h>

#define ELEMENTS 6
#define ORDER 4

// Prototypes
int POWER_recursive(int,int);
int POWER_iterative(int,int);

int MCD_recursive(int,int);
int MCD_iterative(int,int);

int BOARDS_recursive(int,int);
int BOARDS_iterative(int,int);

int iADD_VECTOR_recursive_backward(int [], int);
int ADD_VECTOR_iterative_backward(int [], int);

int iADD_VECTOR_recursive_forward(int [], int, int);
int ADD_VECTOR_iterative_forward(int [], int, int);

int iPALINDROME_VECTOR_recursive(int [], int, int);
//int PALINDROME_VECTOR_iterative(int [], int, int);

bool iSYMMETRIC_MATRIX_recursive(int [][ORDER], int);
bool symmetric_band(int [][ORDER], int);
bool SYMETRIC_MATRIX_iterative (int [][ORDER],int);

// Funcion Principal
int main(){
  int a,n,b,m;

  // POWER
  a=2;
  n=5;
  printf(" ---------------------\n");
  printf(" POWER n-ESIMAL of a\n");
  printf(" ----------------------");
  printf("\n\n The power %d-th of %d is: %d  (recursive version)\n\n", n, a, POWER_recursive(a,n));
  printf("\n\n The power %d-th of %d is: %d (iterative version)\n\n", n, a, POWER_iterative(a,n));


  // MCD
  a=48;
  b=60;
  printf(" --------------------\n");
  printf(" MAX COMMON DIVISOR\n");
  printf(" --------------------");
  printf("\n\n The max common divisor of %d and %d is: %d  (recursive version)\n\n", a,b, MCD_recursive(a,b));
  printf("\n\n The max common divisor of %d and %d is: %d  (iterative version)\n\n", a,b, MCD_iterative(a,b));

  // BOARDS
  
  n=5;
  m=2;
  printf(" --------\n");
  printf(" BOARDS\n");
  printf(" --------");
  printf("\n\n The number of ways to place a %d x %d board on a %d x %d board is: %d (recursive version)\n\n",m,m,n,n,BOARDS_recursive(n,m));
  printf("\n\n The number of ways to place a %d x %d board on a %d x %d board is: %d (iterative version) \n\n",m,m,n,n,BOARDS_iterative(n,m));
  

  // ADD VECTOR
  int V[ELEMENTS]={1,2,3,3,2,1};

  printf(" -----------\n");
  printf(" ADD VECTOR\n");
  printf(" -----------");
  printf("\n\n The sum of the elements of the vector is: %d (recursive backward version)\n\n",iADD_VECTOR_recursive_backward(V,ELEMENTS));
  printf("\n\n The sum of the elements of the vector is: %d  (iterative backward version)\n\n",ADD_VECTOR_iterative_backward(V, ELEMENTS));
  printf("\n\n The sum of the elements of the vector is: %d  (recursive backward version)\n\n",iADD_VECTOR_recursive_forward(V,ELEMENTS,1));
  printf("\n\n The sum of the elements of the vector is: %d  (iterative backward version)\n\n",ADD_VECTOR_iterative_forward(V,ELEMENTS,1));
  
/*
  // PALINDROME VECTOR
  int W[ELEMENTS]={1,2,3,3,2,2};

  printf(" -------\n");
  printf(" PALINDROME\n");
  printf(" -------");
  if (iPALINDROME_VECTOR_recursive(W,1,ELEMENTS)) printf("\n\n The vector is palindrome  (recursive version)\n\n");
  else printf("\n\n The vector is not palindrom  (recursive version)\n\n");
  //if (PALINDROME_VECTOR_iterative(W,1,ELEMENTS)) printf("\n\n The vector is palindrome  (iterative version) \n\n");
  //else printf("\n\n The vector is not palindrom  (iterative version)\n\n");


  //SYMMETRIC MATRIX
  int M[][ORDER]={{1,2,5,7},
                  {2,8,6,9},
                  {5,6,9,2},
                  {7,9,2,1}};


  printf(" ----------------\n");
  printf(" SYMMETRIC MATRIX\n");
  printf(" ----------------");

  if (iSYMMETRIC_MATRIX_recursive(M,ORDER))  printf("\n\n THE MATRIX IS SYMMETRIC (recursive version)\n\n");
  else printf("\n\n THE MATRIX IS NOT SYMMETRIC (recursive version)\n\n");

  //if (SYMMETRIC_MATRIX_iterative(M,ORDER))  printf("\n\n THE MATRIX IS SYMMETRIC (iterative version)\n\n");
  //else printf("\n\n THE MATRIX IS NOT SYMMETRIC (iterative version)\n\n");
  */

  return 0;
}


// Function implementation

int POWER_recursive(int a, int n){
  if (n==0) return 1;
  else return POWER_recursive(a,n-1)*a;
}

int POWER_iterative(int a, int ninicial){
  int n = 0, p = 1;

  while(n != ninicial){

    n += 1;

    p = p * a;

  }

  return p;
}

int MCD_recursive(int a, int b){
  if (a==b) return a;
  else if (a>b) return MCD_recursive(a-b,b);
  else return MCD_recursive(a,b-a);
}


int MCD_iterative(int a_initial, int b_initial){

  int a = a_initial, b = b_initial;

  while(a != b){
    if (a > b)
      a = a - b;
    else
      b = b - a;
  }
  return a;

}

int  BOARDS_recursive(int n, int m){
  
    if(m == 1)
      return n*n;
    return BOARDS_recursive(n - 1, m - 1);

}

int BOARDS_iterative(int n_initial, int m_initial){
  
  int n,m;
  n = n_initial;

  m = m_initial;

  while(m > 1){

    n -= 1;
    m -= 1;

  }
  
  return n*n;

}


int iADD_VECTOR_recursive_backward (int V[], int j){
  
  if (j == 0){
    return 0;
  }
  return iADD_VECTOR_recursive_backward(V, j - 1) + V[j - 1];

}


int ADD_VECTOR_iterative_backward(int V[], int j_initial){
  int j = j_initial;
  int e = 0;

  while(j != 0){

    j--;

    e += V[j - 1];

  }

  return e;
}


int iADD_VECTOR_recursive_forward (int V[], int n, int j){
  
  if (j == n){
    return V[n];
  }
  return iADD_VECTOR_recursive_forward(V, n,  j + 1) + V[j];

}


int ADD_VECTOR_iterative_forward(int V[], int  n, int j_initial){

  int j = n, e = V[n - 1];

  while(j != j_initial){

    j--;

    e += V[j - 1];

  }

  return e;

}


int iPALINDROME_VECTOR_recursive (int V[], int i, int j){
  // TO BE COMPLETED BY THE STUDENT
}


int PALINDROME_VECTOR_iterative(int V[], int i_initial, int j_initial){
  // TO BE COMPLETED BY THE STUDENT
}

bool iSYMMETRIC_MATRIX_recursive (int A[][ORDER], int k){
}

bool symmetric_band(int A[][ORDER], int k){
}

//bool SYMMETRIC_MATRIZ_iterative (int A[][ORDER], int k_initial){
  // TO BE COMPLETED BY THE STUDENT
//}