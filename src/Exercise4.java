/*
 * Here a shared value using by more than one thread along reading,modifying and writing value of garlicCount.
 * We need control the accessing to garlicCount variable by one thread per time.
 *
 * Atomic Operation
 * Execute as a single action, relative to other threads.
 * Cannot be interrupted by other concurrent threads.
 */
import java.util.concurrent.locks.*;

class Shopper extends Thread{

    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock();
    @Override
    public void run() {
        for(int i=0;i<5;i++){
            pencil.lock();
            garlicCount++;
            pencil.unlock();
            try {
                Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName()+" is thinking");
        }
    }
}

public class Exercise4 {

    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();

        barron.start();
        olivia.start();
        barron.join();
        olivia.join();

        System.out.printf("we should buy %d garlic.",Shopper.garlicCount);
    }
}
