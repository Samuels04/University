package practice_ds;

import java.util.Collection;

import datastr.ListBag;
import datastr.ListBagSort;

public class App {

	public static void main(String[] args) {
		Collection<Character> c1 = new ListBag<>();
		
		c1.add('f');
		c1.add('t');
		c1.add('a');
		c1.add('q');
		c1.add('s');
		c1.add('a');
		c1.add('c');
		c1.add('r');
		c1.add('s');
		c1.add('b');
		
		Collection<Character> c2 = new ListBagSort<>(c1);
		Collection<Character> c3 = new ListBagSort<>(c2);
		Collection<Character> c4 = new ListBagSort<>(c1,
				(ch1, ch2) -> { return -ch1.compareTo(ch2); });
		
		System.out.printf("c1: %s\n", c1);
		System.out.printf("c2: %s\n", c2);  // must be sorted in natural order
		System.out.printf("c3: %s\n", c3);  // must be sorted in natural order
		System.out.printf("c4: %s\n", c4);  // must be sorted in the opposite order
		System.out.printf("c3 == c2 ? %b\n", c3.equals(c2));  // must be true
		System.out.printf("c3 == c4 ? %b\n", c3.equals(c4));  // must be false
	}

}
