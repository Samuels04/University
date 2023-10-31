package datastr;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListBag<E> extends AbstractCollection<E>{
    protected List<E> data;

    public ListBag(){
        data = new LinkedList<E>();
    }

    

    @Override
    public Iterator<E> iterator() {
        return data.iterator();
    }

    @Override
    public int size() {
        return this.data.size();
    }


    @Override
    public boolean add(E e) {
        return e == null ? false : data.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return data.remove(o);
    }
}
