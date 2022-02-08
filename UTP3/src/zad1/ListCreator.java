package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T> {
    List<T> list;
    private List data;
    public ListCreator(List<T> list) {
        this.list = list;
    }
    static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<T>(src);
    }
    ListCreator<T> when(Predicate<T> sel){
        data = new ArrayList<T>();
        for (int i = 0; i <list.size(); i++) {
            if (sel.test(list.get(i))) data.add(list.get(i));
        }
        this.list = data;
        return this;
    }
    <K> List<K> mapEvery(Function<T,K> map){
        data = new ArrayList<K>();
        for (int i = 0; i <list.size(); i++) {
            data.add(map.apply(list.get(i)));
        }
        return data;
    }
}

