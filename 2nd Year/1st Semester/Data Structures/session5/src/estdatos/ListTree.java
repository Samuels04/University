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
	private List<Tree<E>> children;
	Tree<E> parent;
	/**
	 * Creates a tree whose root node is labeled as specified and
	 * whose children are the root nodes of the specified trees.
	 * @param e	the label of this tree's root
	 * @param trees the trees whose roots are the children of
	 * this tree's root (trees is an array of trees)
	 */
	@SafeVarargs
	public ListTree(E e, Tree<E> ...trees) {
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

	public ListTree(Tree<E> t) {
		labelroot = t.label();
		children = new LinkedList<>();
		ChildrenIterator<Tree<E>> itr = t.childrenIterator();
		while(itr.hasNext()){
			ListTree<E> s = new ListTree<>(itr.next());
			children.add(s);
			s.parent = this;
		}
		
	}


	public boolean isLeaf() {
		return this.children.size() == 0;
	}


	public E label() {
		return this.labelroot;
	}


	public ChildrenIterator<Tree<E>> childrenIterator() {
		return new ListTreeIterator();
	}

	private final class ListTreeIterator implements ChildrenIterator<Tree<E>> {

		private ListIterator<Tree<E>> lit = children.listIterator();


		public boolean hasNext() {
			return this.lit.hasNext();
		}


		public Tree<E> next() {
			return this.lit.next();
		}


		public void set(Tree<E> e){
			ListTree<E> s = new ListTree<>(e);
			s.parent = ListTree.this;
			lit.set(s);
		}

		public void add(Tree<E> e){
			ListTree<E> s = new ListTree<>(e);
			s.parent = ListTree.this;
			lit.add(s);
		}
		

	}


}
