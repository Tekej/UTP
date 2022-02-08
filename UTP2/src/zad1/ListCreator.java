/**
 *
 *  @author Bolshedvorskyi Denys S19374
 *
 */

package zad1;
import java.util.ArrayList;
import java.util.List;
public class ListCreator<T> {
    List<T> list;
    private List data;
    public ListCreator(List<T> list) {
        this.list = list;
    }
    static <T> ListCreator<T> collectFrom(List<T> src) {
        return new ListCreator<T>(src);
    }
    ListCreator<T> when(Selector<T> sel){
        data = new ArrayList<T>();
        for (T aList : list) {
            if (sel.select(aList)) {
                data.add(aList);
            }
        }
        this.list = data;
        return this;
    }
    <K> List<K> mapEvery(Mapper<T,K>map){
        data = new ArrayList<K>();
        for (T e : list) {
            data.add(map.map(e));
        }
        this.list = data;
        return data;
    }
}