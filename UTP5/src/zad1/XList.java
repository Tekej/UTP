package zad1;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

class XList<T> extends ArrayList<T> {
    XList(T... args) {
        super(Arrays.asList(args));
    }

    public XList(Collection<T> c) {
        super(c);
    }

    public static<T> XList<T> of(T... args) {
        return new XList<T>(args);
    }
    public static<T> XList<List<T>> of(List<T>... args) {
        return new XList<List<T>>(args);
    }
    public static<T> XList<T> of(Collection<T> c) {
        return new XList<>(c);
    }

    public static <T> XList<T> charsOf(String string) {
        List returnList  = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            returnList.add(String.valueOf(string.charAt(i)));
        }
        return new XList<T>(returnList);
    }

    public static <T> XList<T> tokensOf(String... string) {
        List returnList;
        if(string.length == 1) {
            returnList = new ArrayList<>(Arrays.asList(string[0].split("\\s")));
        }else {
            returnList = new ArrayList<>(Arrays.asList(string[0].split(string[string.length - 1])));
        }
        return new XList<T>(returnList);
    }

    XList<T> union(T... args) {
        List<T> returnList = new ArrayList<>(this);
        returnList.addAll(Arrays.asList(args));
        return new XList<>(returnList);
    }

    public XList<T> union(Collection<T> c) {
        List<T> returnList = new ArrayList<>(this);
        returnList.addAll(c);
        return new XList<>(returnList);
    }

    public XList<T> diff(Collection<T> c) {
        List<T> returnList = new ArrayList<>(this);
        returnList.removeAll(c);
        return new XList<>(returnList);
    }

    XList<T> unique() {
        List<T> returnList = this.stream().distinct().collect(Collectors.toList());
        return new XList<T>(returnList);
    }

    XList<List<T>> combine() {
        List<List<T>> lista = new ArrayList<List<T>>((Collection<? extends List<T>>) this);
        List<List<T>> returnList = new ArrayList<>();
        List<T> comb;
        for (int k = 0; k <lista.get(2).size(); k++) {
            for (int i = 0; i < lista.get(1).size(); i++) {
                for (int j = 0; j < lista.get(0).size(); j++) {
                    comb = (List<T>) Arrays.asList(lista.get(0).get(j).toString(),lista.get(1).get(i).toString(),lista.get(2).get(k).toString());
                    returnList.add(comb);
                }
            }
        }
        return new XList<List<T>>(returnList);
    }
    public <R> XList<R> collect(Function<T, R> f) {
        List<R> returnList = new ArrayList<R>();
        for (int i = 0; i <this.size(); i++) {
            List<T> lista= new ArrayList<T>((Collection<? extends T>) this.get(i));
            returnList.add(f.apply((T) new XList<T>(lista)));
        }
        return new XList<R>(returnList);
    }

    public String join(String string) {
        String s ="";
        for (int i = 0; i <this.size(); i++) {
            if(i!=this.size()-1){
                s+=this.get(i)+string;
            }else{
                s+=this.get(i);
            }

        }
        return s;
    }

    public String join() {
        String s ="";
        for (int i = 0; i <this.size(); i++) {
            s+=this.get(i);
        }
        return s;
    }
    void forEachWithIndex(BiConsumer<T, Integer> c) {
        for (int i = 0; i < this.size(); i++) {
            c.accept(this.get(i), i);
        }
    }
}
//[1, 3, 9, 11]
//[5, 6, 9]
//[100, 200, 300]
//[100, 200, 300]
//[3, 4, 5]
//[3, 4, 5]
//[a, l, a,  , m, a,  , k, o, t, a]
//[ala, ma, kota]
//[A, B, C]
//[1, 3, 9, 11, 5, 6, 9]
//[1, 3, 9, 11, 5, 6, 9, 11]
//[1, 3, 9, 11, 5, 6, 9, 11]
//[1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4]
//[1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4, 3, 4, 5]
//[1, 9, 11, 6, 9, 11, 100, 200, 300]
//[]
//[1, 3, 9, 11, 5, 6, 100, 200, 300, 4]
//[[a, b], [X, Y, Z], [1, 2]]
//[[a, X, 1], [b, X, 1], [a, Y, 1], [b, Y, 1], [a, Z, 1], [b, Z, 1], [a, X, 2], [b, X, 2], [a, Y, 2], [b, Y, 2], [a, Z, 2], [b, Z, 2]]
//aX1 bX1 aY1 bY1 aZ1 bZ1 aX2 bX2 aY2 bY2 aZ2 bZ2
//a-X-1 b-X-1 a-Y-1 b-Y-1 a-Z-1 b-Z-1 a-X-2 b-X-2 a-Y-2 b-Y-2 a-Z-2 b-Z-2
//[2, 4, 16, 20, 22, 60, 6, 8]
//[4, 16, 22, 60, 8]
//[16, 22, 60, 8]
