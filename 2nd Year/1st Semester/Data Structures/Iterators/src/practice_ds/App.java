package practice_ds;

//import java.util.Collection;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import datastr.InternalIterator;
import datastr.IterableExtends;

public class App {
	private final static Iterable<String> CSTR = Stream.of("this", "is", "a stream", "formed", "by", "several", "strings").collect(Collectors.toCollection(LinkedList::new));
	
	public static void main(String[] args) {

		/* 
		 *  Using an internal iterator (object of the type
		 *  InternalIterator<String>)
		 */
		System.out.println("\n\tWith internal iterators");
		System.out.println("\t-----------------------");
		
		InternalIterator<String> itr = new InternalIterator<>(CSTR);

		// Show on the console the strings of the collection CSTR	

		itr.forAll(s -> System.out.print(s));

		// Strings of CSTR with more than 5 characters
	
		itr.forEachFiltered(s -> System.out.print(s), s -> s.length() > 5);

		// 'Lowest' string in the collection CSTR

		String lowestString = null;
		for(String s: CSTR){
			
		}
		

		// Concatenation of the strings of CSTR with more than 5 characters.
		// They are concatenated according to the order in which an iterator
		// provides them.

	

		/* 
		 *  Using an external iterator (object of the type
		 *  Iterator<String>) 
		 */
		System.out.println("\n\tWith external iterators");
		System.out.println("\t-----------------------");
		
		// how on the console the strings of the collection CSTR	

		
		// Strings of CSTR with more than 5 characters

		
		// 'Lowest' string in the collection CSTR

		
		// Concatenation of the strings of CSTR with more than 5 characters.
		// They are concatenated according to the order in which an iterator
		// provides them.


	}

}
