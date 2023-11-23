package datastr;

import java.util.Set;
import java.util.Map.Entry;

public interface CrossReferences extends Iterable<Entry<String, Set<Integer>>> {
	
	/**
	 * Add the reference to the given line number for the
	 * specified word.
	 * @param word the specified word
	 * @param line the number of the line where the given
	 * word was found
	 */
	public void addWord(String word, int line);
	
	/**
	 * Returns the set of words in the specified line of the
	 * text in alphabetical order.
	 * @param line the number of the line in the text
	 * @return the set of words in the specified line of the
	 * text in alphabetical order
	 */
	public Set<String> words(int line);
	
	/**
	 * Returns the total number of references.
	 * @return the total number of references
	 */
	public int size();

}
