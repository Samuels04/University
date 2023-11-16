
package practice_ds;
import datastr.Polynomial;

public class MainPoly {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Polynomial p=new Polynomial();
		System.out.println("p: "+p);
		p.put(0,-7.5);
		p.put(6,2.5);
		p.put(3,4.3);
		System.out.println("p: "+p);	
		System.out.println("p's degree: "+p.degree());	
		System.out.println("Evaluation for x=2: "+p.evaluate(2));
		System.out.println("Coefficient for degree 6: "+p.coefficient(6));
		Polynomial p2=new Polynomial(p);
		p.put(6,1.5);
		p.put(5,-8.5);
		p.put(0,7.5);
		System.out.println("p: "+p);
		p.remove(5);
		System.out.println("p: "+p);
		System.out.println("*********************");
		
		p.put(2,-3.7);
		System.out.println("p: "+p);
		System.out.println("p2: "+p2);
		System.out.println("p+p2: "+Polynomial.sum(p,p2));
		
	}

}

//Program's output
//p: 
//p: 2.5x6 +4.3x3 -7.5x0 
//p's degree: 6
//Evaluation for x=2: 186.9
//Coefficient for degree 6: 2.5
//p: 4.0x6 -8.5x5 +4.3x3 
//p: 4.0x6 +4.3x3 
//*********************
//p: 4.0x6 +4.3x3 -3.7x2 
//p2: 2.5x6 +4.3x3 -7.5x0 
//p+p2: 6.5x6 +8.6x3 -3.7x2 -7.5x0 
