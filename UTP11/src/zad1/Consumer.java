package zad1;

public class Consumer implements Runnable {
    Object o = new Object();
    boolean go = true;
    Buffer b;
    public long end = System.currentTimeMillis() + 15 * 1000;

    public Consumer(Buffer b) {
        this.b = b;
        System.out.println("Liczba została pobrana z bufora: " + b.get());
    }

    @Override
    public void run() {
        try {
        while (go) {
            if (b.bufor.size() > 0) {
                synchronized (o) {
                    new Consumer(b);
                }
            }
            if (System.currentTimeMillis() > end) {
                stop();
            }
            int zasypia = (int) (Math.random() * 3);

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
