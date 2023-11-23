package practice_ds;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import datastr.CrossReferences;
import datastr.CrossReferencesImp;

public class App {

 	/**
	 * Returns the cross references from the text file with
	 * the specified number.
	 * @param fileName the name of the text file
	 * @return the cross references from the text file
	 * @throws FileNotFoundException if reading the indicated
	 * text file is not possible
	 */
	private static CrossReferences processText(String fileName)
 			throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		
		CrossReferences cr = new CrossReferencesImp();
		
		int l = 1;

		while(sc.hasNextLine()){
			String line = sc.nextLine();
			String[] words = line.split("[( ,;:. )]");
			
			for(String word : words) {
				cr.addWord(word.toLowerCase(), l);
			}
			 
			l++;

		}
		
		sc.close();
		
		return cr;
 	}
 	
 
	public static void main(String[] args) {
		CrossReferences cr;
		try {
			
			cr = processText("text.txt");
			System.out.println("\nCross references");
			System.out.println("--------------------");
			System.out.println("Word\t\tLines where it appears");
			
			Iterator<Entry<String, Set<Integer>>> itr = cr.iterator();

			Entry<String, Set<Integer>> next;
			

			int numberOfReferences = 0;

			while(itr.hasNext()) {
				next = itr.next();
				if(next.getKey().length() <= 7)
					System.out.printf("%s\t\t\t%s\n", next.getKey(), next.getValue().toString());
				else
					System.out.printf("%s\t\t%s\n", next.getKey(), next.getValue().toString());

				numberOfReferences += next.getValue().size();
			}

			
			

			System.out.printf("\nNumber of references: %d\n", numberOfReferences);
			System.out.println();
			System.out.printf("Words in line 4: %s", cr.words(4).toString());
		} catch (FileNotFoundException e) {
		    System.err.format("Error while opening the file: %s", e.getMessage());
		}
	}

}

/*


Cross references
--------------------
Word              Lines where it appears
ansi                 	[5]
archivos             	[1, 5]
cada                 	[2]
caracteres           	[2, 3]
carro                	[4]
codificacion         	[5]
compone              	[2]
constan              	[1]
cr                   	[4]
de                   	[1, 2, 3, 4, 5]
define               	[3]
donde                	[2]
dos                  	[3]
el                   	[1]
en                   	[1, 5]
estar                	[5]
implicita            	[3]
lf                   	[4]
los                  	[1, 5]
linea                	[2, 3, 4]
lineas               	[2]
mediante             	[3]
o                    	[6]
oem                  	[5]
operativo            	[1]
pueden               	[5]
retorno              	[4]
salto                	[4]
se                   	[2, 3]
secuencia            	[2]
sistema              	[1]
texto                	[1, 5]
un                   	[3]
una                  	[2]
unicode              	[6]
usuario              	[3]
utf-8                	[6]
varias               	[1]
windows              	[1, 5]
y                    	[4]

Number of references: 53

Words in line 4: [carro, cr, de, lf, linea, retorno, salto, y]

*/