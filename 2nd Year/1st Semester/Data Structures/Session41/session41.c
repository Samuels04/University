

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// function prototypes
int read_vector(int *, int);
int write_vector(int *, int);
int maximum_vector_DandC(int *,int,int);
int product_vector_DandC(int *,int,int);
bool is_ascending_order_vector_DandC(int *, int, int);

void read_matrix (int **, int, int);
void write_matrix (int **, int, int);
bool EXAM_RECURSION_NOVEMBER_2021 (int **, int, int, int);


// main program
int main() {
    int n,m,x;
    int *V;
    float *W;
    /*
    do{
       printf("\nInput the number of elements of the vector to calculate the maximum and the product (>=1): ");
       scanf("%d",&n);} while (n<1);

    // allocate dynamic memory
    V = (int*) malloc(n*sizeof(int));


    read_vector(V,n);
    write_vector (V,n);

    //printf("\nThe maximum value in the vector is %d\n\n",maximum_vector_DandC(V,0,n-1));
    printf("\nThe product of the elements in the vector is %d\n\n",product_vector_DandC(V,0,n-1));
    if (is_ascending_order_vector_DandC(V, 0, n-1)) 
		printf("\nThe elements of the vector are sorted in ascending order");
	else
		printf("\nThe elements of the vector are not sorted in ascending order");
	
    //free memory
    free(V);
    
    //PROBLEM 1 FROM THE RECURSION EXAM NOVEMBER 2021
    */

    printf("Input the number of rows: ");
    scanf("%d",&n);
    printf("Input the number of columns: ");
    scanf("%d",&m);

    // define the pointer variable to an int and allocate memory for n rows
    int **M = (int**) malloc(n*sizeof(int*));

    //allocate memory for columns
    for(int i=0;i<n;i++)
        M[i] = (int*) malloc(m*sizeof(int));

    // Read the matrix
    read_matrix (M,n,m);


    printf("\n\nThe elements in the matrix are: \n\n");

    // Display the elements of the matrix
    write_matrix (M,n,m);

    //Call the function which checks the property
    if (EXAM_RECURSION_NOVEMBER_2021(M,1,n,m)) printf("It is satisfied that every row has a number of zeros lower or equal than the immediately next row\n\n");
    else printf("It is not satisfied that every row has a number of zeros lower or equal than the immediately next row\n\n");

    // Free the memory for the columns
    for (int i=0;i<n;i++) free(M[i]);

    // Free the memory for the rows
    free(M);
    


    return 0;
}

//
// Function definitions
//

// Function for reading the components of a vector
int read_vector(int *V, int n){
    for (int i=0;i<n;i++) {
        printf("\nInput the value of a component %d: ",i+1);
    scanf("%d",V+i);
    }
    return 0;
}

// Function for writing the components of a vector
int write_vector(int *V, int n){
    printf("\nThe elements of the vector are: [ ");
    for (int i=0;i<n;i++) {
        printf("%d ",*(V+i));
    }
    printf("]\n");
    return 0;
}

// Function which provides the maximum value of a vector applying the divide and conquer schema
int maximum_vector_DandC(int *V, int start, int end){
    int m, max1, max2, max;

    if (start == end) 
        return V[start];
    else {
        // divide
        m=(start+end)/2;

        // solve
        max1=maximum_vector_DandC(V,start,m);
        max2=maximum_vector_DandC(V,m+1,end);

        // combine
        return max1 >= max2 ? max1 : max2;
    }
}

// Function for multiplying the elements of a vector applying the divide and conquer schema
int product_vector_DandC(int *V, int start, int end){
    int product1, product2, m, product;

    if(start == end)
        return V[start];
    else {
        m  = (start + end)/2;

        product1 = product_vector_DandC(V, start, m);
        product2 = product_vector_DandC(V, m + 1, end);

        return product1*product2;
    }
}

//Function for checking up if the elements of a vector are sorted in ascending order
bool is_ascending_order_vector_DandC(int *V, int start, int end) {
    int m, is1, is2;
    if(start == end)
        return false;
    else{

        m = (start + end)/2;

        is1 = is_ascending_order_vector_DandC(V, start, m);
        is2 = is_ascending_order_vector_DandC(V, m + 1, end);

        return is1 && is2;

    }
}

// PROBLEM 1 FROM THE RECURSION EXAM NOVEMBER 2021


void read_matrix (int **M, int n, int m){
    int i,j;
    for (i=0;i<n;i++)
        for (j=0;j<m;j++){
            printf("Input the element [%d,%d] :  ",i+1,j+1);
            scanf("%d",&M[i][j]);
            // another choice
            //scanf("%d",M[i]+j);
            // and another choice
            //scanf("%d",*(M+i)+j);
        }
    }

void write_matrix (int **M, int n, int m){
    int i,j,aux;
    for (i=0;i<n;i++)
    {
        for (j=0;j<m;j++){
            printf("%d\t",M[i][j]);
            // another choice
            //printf("%d\t",*(M[i]+j));
            // and another choice
            //printf("%d\t",*(*(M+i)+j));
        };
    printf("\n\n");
    }
}


// Function which solves problem 1 from chapter 2 exam applying the divide and conquer schema
bool EXAM_RECURSION_NOVEMBER_2021(int **M, int start_rows, int end_rows, int m){
    bool result1, result2;
    if(start_rows == end_rows){
        return true;
    }
    else {
        result1 = EXAM_RECURSION_NOVEMBER_2021(M,start_rows, m, m);
        result2 = EXAM_RECURSION_NOVEMBER_2021(M, m + 1, end_rows, m);

        return result1 && result2 && EXAM_RECURSION_NOVEMBER_2021(M, m, m+1, m);

    }
}
