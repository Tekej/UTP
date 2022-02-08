package zad1;

public class Producer implements Runnable {
    Object o = new Object();
    boolean go = true;
    Buffer b;
    public long end = System.currentTimeMillis() + 15 * 1000;

    public Producer(Buffer b) {
        this.b = b;
        int value = (int) (Math.random() * 100) + 1;
        System.out.println("Liczba została zapisana do bufora: " + value);
        b.put(value);
    }

    @Override
    public void run() {
        try {
        while (go) {
            if (b.bufor.size() != b.length) {
                synchronized (o) {
                    new Producer(b);
                }
            }
            int zasypia = (int) (Math.random() * 3);
            if (System.currentTimeMillis() > end) {
                stop();
            }
            Thread.sleep(zasypia * 1000);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void stop() {
        go = false;
    }
}
