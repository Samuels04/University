package datastr;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Polynomial implements Poly{
    private SortedMap<Integer, Double> terms;

    public Polynomial(){
        terms = new TreeMap<>();
    }

    public Polynomial(double coefficient, int exponent) {
        terms = new TreeMap<>();
        terms.put(exponent, coefficient);
    }

    public Polynomial(Poly p) {
        if (p instanceof Polynomial) {
            Polynomial aux = (Polynomial) p ;
            terms = new TreeMap<>(aux.terms);
        } else 
            for(int i = 0; i <= p.degree(); i++)
                if(p.coefficient(i) != 0)
                    terms.put(i, p.coefficient(i));
            
        
    }

    @Override
    public Double put(Integer exp, Double coef) {
        if(terms.keySet().contains(exp)){
            Double result = terms.get(exp);

            terms.put(exp, coef + result);
            return result;
        }

        terms.put(exp, coef);
        return 0.0;
    }

    @Override
    public Double remove(Integer exp) {
       if(!terms.keySet().contains(exp)){
            return null;
       }
       Double result = terms.get(exp);
       terms.remove(exp);

       return result;
    }

    @Override
    public Double coefficient(Integer exp) {
        return !terms.keySet().contains(exp) ? 0.0 : terms.get(exp);
    }

    @Override
    public Integer degree() {
       return terms.isEmpty() ? 0 : terms.lastKey();
    }

    @Override
    public Double evaluate(Integer x) {
        Iterator<Integer> itr1 = terms.keySet().iterator();
        Iterator<Double> itr2 = terms.values().iterator();

        Double result = 0.0;

        while(itr1.hasNext() && itr2.hasNext()) {
            result += Math.pow(x*itr2.next(), itr1.next());
        }

        return result;
    }

    @Override
    public String toString() {
        String res = "";

        Set<Map.Entry<Integer,Double>> s = terms.entrySet();
        Iterator<Map.Entry<Integer,Double>> it = s.iterator();

        while (it.hasNext()) {
            Map.Entry<Integer,Double> t = it.next();

            String st = "" + t.getValue() + "x" + t.getKey();
            if (terms.lastKey() != t.getKey() && t.getValue()>0)
            st = '+' + st;
            res = st + " " + res;
        }
        return res;
    }

    public static Polynomial sum (Polynomial p1, Polynomial p2) {

        Iterator<Entry<Integer, Double>> itr1 = p1.terms.entrySet().iterator();
        Iterator<Entry<Integer, Double>> itr2 = p2.terms.entrySet().iterator();
        Polynomial p3 = new Polynomial();


        int exp1 = itr1.next().getKey();
        int exp2 = itr2.next().getKey();
        while(itr1.hasNext() && itr2.hasNext()){
           
            if (exp1 < exp2){
                p3.terms.put(exp1, p1.coefficient(exp1));
                exp1 = itr1.next().getKey();
            }
            else if(exp1 > exp2){
                p3.terms.put(exp2, p2.coefficient(exp2));
                exp2 = itr2.next().getKey();
            }
            else {
                if(p1.coefficient(exp1) + p2.coefficient(exp1) == 0){
                    exp1 = itr1.next().getKey();
                    exp2 = itr2.next().getKey();
                } else {
                    p3.terms.put(exp1, p1.coefficient(exp1) + p2.coefficient(exp1));
                    exp1 = itr1.next().getKey();
                    exp2 = itr2.next().getKey();
                }
                
            }
        }
        while(itr1.hasNext()){
            p3.terms.put(exp1, p1.coefficient(exp1) + p2.coefficient(exp1));
            exp1 = itr1.next().getKey();
        }
        return p3;

    }
}
