package zad1;


import java.util.ArrayList;
import java.util.List;

public class Buffer {
    int count;
    int len;
    int length;
    List<Integer> bufor = new ArrayList<>();
    public Buffer(int l) {
        len = 0;
        count = 0;
        length = l;
        System.out.println("Został utworzony bufor z pojemnością: " + length);
    }

    public int get() {
        len--;
        int value = bufor.get(0);
        bufor.remove(0);
        return value;
    }

    public void put(int n) {
        bufor.add(n);
        len++;
    }
}
