package datastr;

import java.util.*;

public class LlistSet<E> extends AbstractSet<E> implements Set<E> {

    LinkedList<E> list;

    public LlistSet(Collection<? extends E> collection) {
        this.list = new LinkedList<>();
        this.addAll(collection);
    }

    public LlistSet(){
        this.list = new LinkedList<E>();
    } 

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean add(E e) {
        Iterator<E> itr = this.iterator();

        while(itr.hasNext()) 
            if(itr.next().equals(e))
                return false;

        list.add(e);
        return true;
        
    }
    
}
