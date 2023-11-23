package datastr;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class CrossReferencesImp implements CrossReferences {

    private SortedMap<String, Set<Integer>> map;

    public CrossReferencesImp() {
        this.map = new TreeMap<>();
    }

    public CrossReferencesImp(CrossReferences cr) {
        if (cr instanceof CrossReferencesImp) {
            this.map = ((CrossReferencesImp)cr).map;
        }
        else {
            this.map = new TreeMap<>();
            Entry<String, Set<Integer>> next;
            Iterator<Entry<String, Set<Integer>>> itr = cr.iterator();
            while (itr.hasNext()) {
                next = itr.next();
                map.put(next.getKey(), next.getValue());
            }
        }
    }

    @Override
    public void addWord(String word, int line) {
        if (this.map.keySet().contains(word)){
           if(!this.map.get(word).contains(line)) {
                this.map.get(word).add(line);
           }
        }
        else {
            Set<Integer> set = new TreeSet<>();
            set.add(line);
            this.map.put(word, set);
        }
    }

    @Override
    public Set<String> words(int line) {
        Set<String> set = new TreeSet<>();
        Iterator<Entry<String, Set<Integer>>> itr = this.iterator();
    
        Entry<String, Set<Integer>> next;
        while(itr.hasNext()){
            next = itr.next();
            if(next.getValue().contains(line))
                set.add(next.getKey());
        }
        return set;
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public Iterator<Entry<String, Set<Integer>>> iterator() {
        return map.entrySet().iterator();
    }
    
}
