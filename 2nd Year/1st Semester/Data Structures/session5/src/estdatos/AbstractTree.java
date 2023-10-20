package estdatos;


import java.util.LinkedList;
import java.util.List;

public abstract class AbstractTree<E> implements iTree<E> {
		
	private String childrenList() {
		List<E> l = new LinkedList<>();
		ChildrenIterator<iTree<E>> itr = childrenIterator();
		while (itr.hasNext()) {
			l.add(itr.next().label());
		}
		
		return l.toString();
	}
	
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append(label().toString());
		out.append(": ");
		out.append(childrenList());
		out.append('\n');
		
		ChildrenIterator<iTree<E>> itr = childrenIterator();
		while (itr.hasNext()) {
			iTree<E> child = itr.next();
			if (child.childrenIterator().hasNext()) {
				out.append(child.toString());
			}
		}
		
		return out.toString();
	}

	@Override
	public boolean equals(iTree<E> e){
		return e.label() == this.label();
	}
}
