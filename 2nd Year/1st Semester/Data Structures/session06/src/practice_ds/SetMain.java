package practice_ds;

import java.util.Arrays;

import datastr.LlistSet;

public class SetMain {	
	
	public static void main(String[] args) {
		
		Integer [] a={1,5,3,7,11,9};
		
		LlistSet<Integer> s=new LlistSet<Integer>();
		s.add(25);
		s.add(30);
		s.add(3);
		s.add(30);
		System.out.println("s: "+s);
		
		s.addAll(Arrays.asList(a));
		System.out.println("s: "+s);	
		System.out.println("s empty?: "+s.isEmpty());
		System.out.println("s contains 3?: "+s.contains(3));
		
		LlistSet<Integer> s1=new LlistSet<Integer>(s);
		System.out.println("s1: "+s1);
		
		s1.removeAll(s);
		
		System.out.println("s1: "+s1);	
		
		LlistSet<String> t=new LlistSet<String>();
		t.add("Pedro");
		t.add("Luis");
		t.add("Maria");
		t.add("Ana");
		t.add("Ana");
		
		System.out.println("t: "+t);	
		t.remove("Luis");	
		System.out.println("t: "+t);	
	}
}

/*******************  OUTPUT: **************************
s: [25, 30, 3]
s: [25, 30, 3, 1, 5, 7, 11, 9]
s empty?: false
s contains 3?: true
s1: [25, 30, 3, 1, 5, 7, 11, 9]
s1: []
t: [Pedro, Luis, Maria, Ana]
t: [Pedro, Maria, Ana]
***************************************************/
