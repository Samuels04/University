package datastr;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;

public class SortedLListSet<E> extends LlistSet<E> {
	protected Comparator<? super E> cmp;
	
    // Constructors

	public SortedLListSet() {
		super();
	}

	public SortedLListSet(Collection<? extends E> e){
		super(e);
	}

	public SortedLListSet(Comparator<? super E> e){
		super();
		this.cmp = e;
	}

	public SortedLListSet(Comparator<? super E> e, Collection<? extends E> f){
		this(e);
		addAll(f);
	}
	
	@SuppressWarnings("unchecked")
	private int compare(Object a, Object b) {
		if (cmp == null && !(a instanceof Comparable<?>) ) {
			throw new ClassCastException();
		}
		
		return cmp == null ? ((Comparable<? super E>) a).compareTo((E) b)
			               : cmp.compare((E) a, (E) b);
	}
	
	@Override
	public boolean add (E e) {
		ListIterator<E> itr = list.listIterator();
		while(itr.hasNext()){
			E current = itr.next();
			if(compare(e, current) < 0){
				itr.previous();
				itr.add(e);
				return true;
			}
			else if(compare(e, current) == 0)
				return false;
				
		}
		itr.add(e);
		return true;

	}
	
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(!(obj instanceof SortedLListSet))
			return false;
		if(((SortedLListSet<E>)obj).size() != this.size())
			return false;
		
		Iterator<E> itr1 = this.iterator();
		Iterator<E> itr2 = ((SortedLListSet<E>) obj).iterator();

		while(itr1.hasNext() && itr2.hasNext()){
			if(itr1.next().equals(itr2.next()))
				return true;
		}
		return false;

	}

	
}
	
