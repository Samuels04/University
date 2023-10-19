package estdatos;

import java.util.Iterator;

/**
 * Extension of the interface Iterator<E> that will be used for
 * obtaining, one by one, the subtrees whose roots are the
 * children nodes of the root of a sorted tree. 
 * @param <E> the type of the items
 */
public interface ChildrenIterator<E> extends Iterator<E> {
	
	/**
	 * Replaces the last element returned by {@code next()} by the
	 * specified element (optional).
	 * @param e the replaced element
	 * @throws IllegalStateException if {@code next()} has not been
	 * called or an element has been replaced, removed, or added after
	 * the last call to {@code next()}
	 * @throws NullPointerException if the tree does not admit label
	 * with value {@code null}
	 */
	default void set(E e) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Add the specified element before the element that will be
	 * returned by the operation {@code next()}
	 * @param e
	 */
	default void add(E e) {
		throw new UnsupportedOperationException();
	}
}
