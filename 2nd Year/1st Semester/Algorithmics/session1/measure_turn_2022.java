/**
 * @author ALGORITHMICS
 *
 */
public class measure_turn_2022 {

	/**
	 * @param args
	 */

	//Function to fill up the vector
	protected static void fillOut(int[] vector, int n){
		for(int i=0; i<n; i++){
			vector[i]=i;
		}
	}


	//Function to turn the vector
	protected static void turn(int[] vector, int n){
		int i,j,aux;
		for(i=0; i<n-1;i++){
			for(j=0;j<n-1-i;j++){
				aux=vector[j];
				vector[j] = vector[j+1];
				vector[j+1]=aux;
			}
		}
	}



	public static void main(String[] args) {

		int REPETITIONS = 100;
		int NUM_SIZES = 12;

		int i,j;
		double time_start, time_final;

		// It is defined a vector with the different sizes to be measured
		 int[] sizes={1000,2000,3000,4000,5000,6000,7000,8000,9000,10000,11000,12000};

		// Present the header of the table with sizes vs. times
		 System.out.printf("TURN A VECTOR\n\n");
		 System.out.printf("Consumption time:\n\n");
		 System.out.printf("\t\tSize\t\tTime\n\n");
		 System.out.printf("\t\t-----\t\t------\n\n");


		 // Loop to go through the different sizes to be measured
		 for (i=0;i<NUM_SIZES;i++)
		     {
		     // Create a vector with the required size
			 int[] vector = new int [sizes[i]];

		     // Fill up the vector for the sizes[i]
		     fillOut(vector, sizes[i]);

		     // Call to the function clock before running the code to be measured
		     time_start = System.currentTimeMillis();

		     // Loop to repeat the experiment
		     for (j = 0; j < REPETITIONS; j++)
		         // Call to the function to be measured
		         turn(vector, sizes[i]);

		     // Call to the function clock after running the code to be measured
		     time_final = System.currentTimeMillis();


		     // Show the size and the consumption time (consumption time/repetitions)
		     System.out.printf("\t\t%d\t\t%f\n", sizes[i],((time_final-time_start) /1000 / REPETITIONS));
		     }
	}

}
