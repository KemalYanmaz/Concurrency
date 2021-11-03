/*
 * Here a shared value using by more than one thread along reading,modifying and writing value of garlicCount.
 * We need control the accessing to garlicCount variable by one thread per time.
 *
 * Atomic Operation
 * Execute as a single action, relative to other threads.
 * Cannot be interrupted by other concurrent threads.
 */
import java.util.concurrent.atomic.*;

class Shopper extends Thread{

    static AtomicInteger garlicCount = new AtomicInteger(0);
    @Override
    public void run() {
        for(int i=0;i<10_000_000;i++){
            garlicCount.incrementAndGet();
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

        System.out.println("we should buy "+Shopper.garlicCount+" garlic.");
    }
}
