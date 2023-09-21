package datastr;

import java.util.Iterator;

public class InternalIterator<T> implements IterableExtends<T> {

    private Iterable<T> it;

    public InternalIterator(Iterable<T> it){
        this.it = it;
    }
    @Override
    public Iterator<T> iterator() {
       return it.iterator();
    }

    
}
