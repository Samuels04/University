package estdatos;

public interface iTree<E> {

	/**
	 * Returns true if the root of this tree is a leaf
	 * 
	 * @return {@code true} if the root of this tree is a leaf
	 */
	boolean isLeaf();

	/**
	 * Returns the label of this tree's root.
	 * 
	 * @return the label of this tree's root
	 */
	E label();
	
	/**
	 * Iterator for the subtrees that are children of this tree's root.
	 * 
	 * @return an iterator of the children nodes of this tree's root
	 */
	ChildrenIterator<iTree<E>> childrenIterator();

	/**
	 * Changes the label of this tree's root to the specified
	 * object (optional operation).
	 * 
	 * @param e the new label for the root
	 * @throws UnsupportedOperationException if the operation is not
	 * supported for this tree
	 * @throws NullPointerException if this tree does not accept
	 * labels with value {@code null}
	 */
	default void setLabel(E e) {
		throw new UnsupportedOperationException();
	}
	
	default public boolean equals(iTree<E> t) {
		if (!label().equals(t.label())) {
			return false;
		}
		
		ChildrenIterator<iTree<E>> itrThis = childrenIterator();
		ChildrenIterator<iTree<E>> itrT = t.childrenIterator();
		while (itrThis.hasNext() && itrT.hasNext()) {
			if (!itrThis.next().equals(itrT.next())) {
				return false;
			}
		}
		
		return itrThis.hasNext() == itrT.hasNext();
	}
}
