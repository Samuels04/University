

import estdatos.ChildrenIterator;
import estdatos.Tree;
import estdatos.FTree;
import estdatos.LCRSTree;
import estdatos.ListTree;



public class App {
	
    /**
     * @param t a sorted tree
     * @param e the label to look for
     * @return the length of the path from the tree root {@code t}
     * to the first node label in postorder {@code e}. If the label
     * is not found in the tree, returns -1
     */
	public static <E> int length(Tree<E> t, E e) {

		return 0;
	}

	public static void main(String[] args) {

		// Obtain a tree t of type Tree<Character> from the strings obtained
		// when going through it in preorder and postorder.
		// This tree will be an instance of TreeLCRS<Character> and, hence,
		// is represented by a binary tree.
		Tree<Character> t = FTree.createTree("abdhicefjg", "hidbejfgca");
		
		System.out.printf("¿t instanceof TreeLCRS<Character>? %b\n",t instanceof LCRSTree<?>);
		System.out.printf("¿t instanceof TreeList<Character>? %b\n", t instanceof ListTree<?>);
		// Check of type. Every subtree of t must be of type TreeLCRS<Character>
		FTree.typeChecking("t", t);
			
		// Now, the tree t will be used with the constructors of type TreeList<E>
		// in order to create the instances of this type: tree1 y tree2. In
		// addition, a copy of tree1 is created.
		Tree<Character> tree1 = new ListTree<>(t);
		Tree<Character> tree2 = new ListTree<>('x', new ListTree<>('y'), t);
		Tree<Character> tree1Copy = new ListTree<>(tree1);
		
		// Check of type. Every subtree of tree1, tree2 and tree1Copy
		// must be of type TreeLCRS<Character>
		FTree.typeChecking("tree1", tree1);
		FTree.typeChecking("tree2", tree2);
		FTree.typeChecking("tree1Copy", tree1Copy);
		System.out.println();
		
		/*** equals operation required ***/
		// Object obj = t;
		
		// // Checking the equalities of trees tree1 and tree2 with obj
		// System.out.printf("¿tree1.equals(t)? %b\n", tree1.equals(obj));	// true
		// System.out.printf("¿tree2.equals(t)? %b\n", tree2.equals(obj));	// false
		// System.out.println();
		
		/*** parent() operation from ListTree<E> required, as well as the
	         the obligatory operations of the iterator childrenIterator() ***/
	
		// FTree.parents((ListTree<Character>)tree2);	// Parent of x:  
		// 											// Parent of y: x
		// 											// Parent of a: x
		// 											// Parent of b: a
		// 											// Parent of d: b
		// 											// Parent of h: d
		// 											// Parent of i: d
		// 											// Parent of c: a
		// 											// Parent of e: c
		// 											// Parent of f: c
		// 											// Parent of j: f
		// 											// Parent of g: c		
		// System.out.println();

		
		/*** Obligatory operations of the iterator childrenIterator() required ***/

		// // Show on the console the trees tree1 and tree2. They are covered in
		// // preorder and, for each node with children, its label is shown, and,
		// // then, the list with the labels of the children.
		// System.out.printf("Tree tree1:\n%s\n", tree1);	// Tree tree1:
		// 												// a: [b, c]
		// 												// b: [d]
		// 												// d: [h, i]
		// 												// c: [e, f, g]
		// 												// f: [j]
		// System.out.printf("Tree tree2:\n%s\n", tree2);	// Tree tree2:
		// 												// x: [y, a]
		// 												// a: [b, c]
		// 												// b: [d]
		// 												// d: [h, i]
		// 												// c: [e, f, g]
		// 												// f: [j]

		
		/*** Optional operations of the iterator childrenIterator() required ***/
			
		// // Delete from the tree tree1 the subtree whose root node has label 'c'.
		// FTree.removeTree(tree1, 'c');
		// System.out.printf("Tree tree1:\n%s\n", tree1);	// Tree tree1:
		// 												// a: [b]
		// 												// b: [d]
		// 												// d: [h, i]
		// System.out.println();
		
		// // The tree tree1 is created again, along with a new one (other)
		// tree1 = new ListTree<>(tree1Copy);
		// Tree<Character> other = 
		// 		new ListTree<Character>('1',
		// 		                        new ListTree<>('2'),
		// 		                        new ListTree<>('3', new ListTree<>('&')),
		// 		                        new ListTree<>('4', new ListTree<>('!')));		
		// System.out.printf("Tree tree1:\n%s\n", tree1);	// Tree tree1:
		// 												// a: [b, c]
		// 												// b: [d]
		// 												// d: [h, i]
		// 												// c: [e, f, g]
		// 												// f: [j]
		// System.out.printf("Tree other:\n%s\n", other);	// Tree other:
		// 												// 1: [2, 3, 4]
		// 												// 3: [&]
		// 												// 4: [!]
		
		// FTree.addTree(other, '3',
		// 		new ListTree<Character>('r',
		// 								new ListTree<>('$'),
		// 								new ListTree<>('%')));
		
		// System.out.printf("Tree other:\n%s\n", other);	// Tree other:
		// 												// 1: [2, 3, r, 4]
		// 												// 3: [&]
		// 												// r: [$, %]
		// 												// 4: [!]

		// // In the tree other, the subtree whose root node has label '3'
		// // is replaced by the tree tree1.
		// FTree.replaceTree(other, '3', tree1);
		// System.out.printf("Tree other:\n%s\n", other);	// Tree other:
		// 												// 1: [2, a, r, 4]
		// 												// a: [b, c]
		// 												// b: [d]
		// 												// d: [h, i]
		// 												// c: [e, f, g]
		// 												// f: [j]
		// 												// r: [$, %]
		// 												// 4: [!]
		
		/*** length function required ***/

		// System.out.printf("Level of the node with label 'r': %d\n",
		// 		App.length(other, 'r'));							// 1
		// System.out.printf("Level of the node with label 'j': %d\n",
		// 		App.length(other, 'j'));							// 4
		// System.out.printf("Level of the node with label 'k': %d\n",
		// 		App.length(other, 'k'));							// -1
		// System.out.println();
		
		// Range of characters [inf, sup]
		final char inf = '0';
		final char sup = '9';
		

	}

}
