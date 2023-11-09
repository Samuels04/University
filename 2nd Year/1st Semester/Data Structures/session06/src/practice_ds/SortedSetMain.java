package practice_ds;


import java.util.Arrays;
import java.util.Iterator;

import datastr.LlistSet;
import datastr.SortedLListSet;

public class SortedSetMain {	
	
	public static void main(String[] args) {
		
		SortedLListSet<Integer> s=new SortedLListSet<Integer>();
		s.add(25);
		s.add(30);
		s.add(3);
		s.add(30);
		s.add(5);
		s.add(15);
		System.out.println("s: "+s);	
		
	    System.out.println("s: contains 66? "+s.contains(66));
		System.out.println("s: contains 3? "+s.contains(3));
			
		SortedLListSet<Integer> s2=new SortedLListSet<Integer>(s);
		System.out.println("s: equals(s2)? "+s.equals(s2));

		
	}		
}

/*******************  OUTPUT: **************************
s: [3, 5, 15, 25, 30]
s: contains 66? false
s: contains 3? true
s: equals(s2)? true
s: [1, 3, 5, 15, 25, 27, 30, 39, 41]
******************************************************/
