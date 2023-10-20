package estdatos;

// Remove the import statement for LCRSTree if you are not using it
// import estdatos.LCRSTree;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Mutable data type for sorted trees.
 * @param <E> the type of the nodes labels
 */
public class ListTree<E> extends AbstractTree<E> {

	private E labelroot;
	private List<iTree<E>> children;
	iTree<E> parent;
	/**
	 * Creates a tree whose root node is labeled as specified and
	 * whose children are the root nodes of the specified trees.
	 * @param e	the label of this tree's root
	 * @param trees the trees whose roots are the children of
	 * this tree's root (trees is an array of trees)
	 */
	@SafeVarargs
	public ListTree(E e, iTree<E> ...trees) {
		this.labelroot = e;
		children = new LinkedList<>();
		for(int i = 0; i < trees.length; i++){
			ListTree<E> s = new ListTree<>(trees[i]);
			children.add(s);
			s.parent = this;
			
		}
		parent = null;
	}
	
	/**
	 * Conversion constructor. Creates a tree of this class and
	 * equal to the specified tree.
	 * @param t the given tree
	 */

	public ListTree(iTree<E> t) {
		labelroot = t.label();
		children = new LinkedList<>();
		ChildrenIterator<iTree<E>> itr = t.childrenIterator();
		while(itr.hasNext()){
			ListTree<E> s = new ListTree<>(itr.next());
			children.add(s);
			s.parent = this;
		}
		
	}

	@Override
	public boolean isLeaf() {
		return this.children.size() == 0;
	}

	@Override
	public E label() {
		return this.labelroot;
	}

	@Override
	public ChildrenIterator<iTree<E>> childrenIterator() {
		return new ListTreeIterator();
	}

	private final class ListTreeIterator implements ChildrenIterator<iTree<E>> {

		private ListIterator<iTree<E>> lit = children.listIterator();

		@Override
		public boolean hasNext() {
			return this.lit.hasNext();
		}

		@Override
		public iTree<E> next() {
			return this.lit.next();
		}

		@Override
		public void set(iTree<E> e){
			ListTree<E> s = new ListTree<>(e);
			s.parent = ListTree.this;
			lit.set(s);
		}
		@Override
		public void add(iTree<E> e){
			ListTree<E> s = new ListTree<>(e);
			s.parent = ListTree.this;
			lit.add(s);
		}
		

	}


}
