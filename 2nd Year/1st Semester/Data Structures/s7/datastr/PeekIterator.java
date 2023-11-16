package datastr;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Interface extending the {@code Iterator<E>} interface
 * that adds the operation {@code peek()}.
 * @param <E> the type of elements provided, one by one,
 * by the iterator.
 */
public interface PeekIterator<E> extends Iterator<E> {

	/**
	 * Allows to consult the next element to iterate.
	 * @return the value of the next element of the iteration;
	 * that is, the element that will be returned by next()
	 * @throws NoSuchElementException if the iteration does
	 * not have more elements.
	 */
	E peek();

}
