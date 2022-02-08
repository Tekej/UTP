package zad3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class InputConverter<T> {
    private T fname;
    InputConverter(T fname) {
        this.fname = fname;
    }

    <R> R convertBy(Function... function) {
        List<R> lista = new ArrayList();
        lista.add((R) function[0].apply(fname));
        for (int i = 1; i < function.length; i++) {
            lista.add((R) function[i].apply(lista.get(i - 1)));
        }
        return lista.get(lista.size() - 1);
    }
}
