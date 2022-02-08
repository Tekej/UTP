import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.DefaultListModel;


public class Task extends Thread implements Callable<Integer>{
    public Object lock = this;
    boolean pause;
    public String thread ="";
    int c;
    int number;
    String data;
    PropertyChangeListener pCListener;
    ExecutorService executor = Executors.newFixedThreadPool(3);
    Future<Integer> resultFuture;

    public Task(int c, DefaultListModel<Task> model) {
        super();
        thread="running";
        this.c = c;
        model.addElement(this);
    }

    public void setPropertyChangeListener(PropertyChangeListener pCListener) {
        this.pCListener = pCListener;
    }

    public void addData(String s) {
        if(pCListener != null) {
            if(data != null) {
                this.pCListener.propertyChange(new PropertyChangeEvent(this, "data", data, data += s));
            }else {
                this.pCListener.propertyChange(new PropertyChangeEvent(this, "data", data, data = s));
            }
        }else {
            data += s;
        }
    }

    @Override
    public Integer call() throws InterruptedException {
        boolean run = true;
        while (run) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
            int random =(int) (Math.random()*99+1);
            number += random;
            this.addData("Thread "+(c/1000)+" (limit="+c+"):"+random+",sum="+number+"\n");
            if (number>c){
                run=false;
            }
            pauseThread();
        }
        this.addData("Task ended" +'\n');
        return number;
    }

    public void taskStart() {
        Callable<Integer> callable = this;
        resultFuture = executor.submit(callable);
    }

    public String taskStatus() {
        if(this.resultFuture.isCancelled()) {
            return "Interrupted";
        }else if(this.resultFuture.isDone()) {
            return "Finished";
        }
        if(thread.equals("suspend"))return "Suspend";
        return "Running";
    }
    public void Suspend(){
        pause = true;
        thread = "suspend";
    }
    public void Countinue() throws InterruptedException {
        pause = false;
        thread = "continue";
        pauseThread();
    }
    public void taskStop() {
        this.addData("Task Interrupted" + '\n');
        this.resultFuture.cancel(true);
    }
    private void pauseThread () throws InterruptedException {
        synchronized (lock)
        {
            if (pause){
                this.addData("Task suspend" + '\n');
                lock.wait();
            }else if(thread.equals("continue")){
                thread = "running";
                notify();
            }// Note that this can cause an InterruptedException
        }
    }

    @Override
    public String toString() {
        return "Thread №"+c/1000;
    }
}