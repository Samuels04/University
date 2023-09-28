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
        data = new LinkedList<>();
    }

    public ListBagSort(Collection<E> c){
        data = new LinkedList<>(c);
    }

    public ListBagSort(Collection<E> c, Comparator<? super E> cp){
        data = new LinkedList<>(c);
        cmp = cp;
    }

    public int compare(E a, E b) {
        return cmp.compare(a, b);
    }

    @Override
    public boolean equals(Object b){
        return this.data.contains(b);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(Object e){
        ListIterator<E> it = this.data.listIterator();
        if (e == null)
            return false;
        
        if(this.data.isEmpty()){
            return data.add((E) e);
        } else {
            for(int i = 0; i < this.data.size() && it.hasNext(); i++){
                if(compare(it.next(), (E) e) < 0){
                    data.add(it.previousIndex(), (E) e);
                    return true;
                }
            
            }
        }
        
        return false;
    }
}