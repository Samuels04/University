package datastr;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

public interface IterableExtends<T> extends Iterable<T> {
	
	/**
	 * Applies the specified action to every element provided
	 * by the iterator, one by one.
	 * @param action the action to be applied
	 */
	default void forAll(Consumer<? super T> action) {
		Iterator<T> itr = this.iterator();
		while(itr.hasNext())
			action.accept(itr.next());
	}
	
	/**
	 * Applies the specified action to every element that
	 * satisfied the given filter.
	 * @param action the action to be applied
	 * @param filter the given filter
	 */
	default void forEachFiltered(Consumer<? super T> action, Predicate<? super T> filter) {
		Iterator<T> itr = this.iterator();
		
		while (itr.hasNext())
			if(filter.test(itr.next()))
				action.accept(itr.next());

	}
	
	/**
	 * Returns the accumulated result of applying specified
	 * function to the elements provided by the iterator,
	 * one by one.
	 * @param f the given function
	 * @return if {@code A0, A1, A2, ...An-1} are the items
	 * provided by the iterator, the value of
	 * {@code f(...f(f(A1, A0), A2), An-1) }
	 * @throws IllegalStateException if this iterable object
	 * is empty (the iterator does not have items to provide)
	 */
	default T reduceAll(BiFunction<T, T, T> f) {
		Iterator<T> itr = this.iterator();

		if (!itr.hasNext())
			throw new IllegalStateException();
		
		T result = itr.next();

		while(itr.hasNext()){
			result = f.apply(result, itr.next());
		}
		return result;
	}
	
	/**
	 * Returns the accumulated result of applying specified
	 * function to the elements that satisfy the given filter.
	 * @param f the given function
	 * @return if {@code a, b, c, ...k} are the items provided
	 * by the iterator that satisfy the filter, the value of
	 * {@code f(...f(f(b, a), c), k) }
	 * @throws IllegalStateException if this iterable object
	 * is empty (the iterator does not have items to provide) or
	 * no item satisfies the given filter
	 */
	default T reduceFiltered(BiFunction<T, T, T> f, Predicate<? super T> filter) {
		Iterator<T> itr = this.iterator();

		if (!itr.hasNext())
			throw new IllegalStateException();
		
		T result = itr.next();

		while (itr.hasNext())
			if (filter.test(itr.next()))
				result = f.apply(result, itr.next());
		
		return result;
	}
	
	/**
	 * Eliminates every item of this iterable object, leaving
	 * it empty.
	 * @throws UnsupportedOperationException if this operation
	 * is not supported
	 */
	default void removeAll() {

		Iterator<T> itr = this.iterator();

		while(itr.hasNext())
			itr.remove();
	}

	/**
	 * Eliminates of this iterable object every item that
	 * satisfy the specified filter.
	 * @param filter the given filter
	 * @throws UnsupportedOperationException if this operation
	 * is not supported
	 */
	default void removeFiltered(Predicate<? super T> filter) {

		Iterator<T> itr = this.iterator();

		while(itr.hasNext())
			if(filter.test(itr.next()))
				itr.remove();
	}

}
