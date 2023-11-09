package datastr;
import java.util.*;

public class LlistBag<E> extends LlistSet<E>{

    public LlistBag(Collection<? extends E> e){
        list = new LinkedList<>(e);
    }

    public LlistBag(){
        list = new LinkedList<>();
    }

    public boolean add(E e){
        return list.add(e);
    }


    
}
