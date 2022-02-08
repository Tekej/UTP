package zad1;

public class Main {

    public static void main(String[] args) {
        Buffer b = new Buffer(10);
        Producer p = new Producer(b);
        Consumer c = new Consumer(b);
        Thread t1 =new Thread(p);
        Thread t2 =new Thread(c);
        t1.start();
        t2.start();
    }
}
