package datastr;

import java.util.Collection;
import java.util.Comparator;
//import java.util.Iterator;
import java.util.LinkedList;
//import java.util.List;
import java.util.ListIterator;


public class ListBagSort<E> extends ListBag<E> {

    private Comparator<? super E> cmp;
    
    public ListBagSort(){
        data = new LinkedList<E>();
    }

    public ListBagSort(Collection<E> c){
        this();
        this.addAll(c);
    }

    public ListBagSort(Collection<E> c, Comparator<? super E> cp){
        this();
        cmp = cp;
        this.addAll(c);
        
    }
    @SuppressWarnings("unchecked")
    public int compare(E a, E b) {
        return cmp == null ? ((Comparable<E>) a).compareTo(b) : cmp.compare(a, b);
    
    }

    @Override
    public boolean equals(Object b){
        return this.data.contains(b);
    }


    @Override
    public boolean add(E e){
        ListIterator<E> it = this.data.listIterator();
        if (e == null)
            return false;
        
        if(this.data.isEmpty()){
            return data.add(e);
        } else {
            while(it.hasNext()){
                if(compare(it.next(), e) > 0){
                    it.previous();
                    it.add(e);
                    return true;
                }
            
            }
        }

        it.add(e);
        
        return false;
    }
}