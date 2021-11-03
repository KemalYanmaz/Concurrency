/**
 * Here a shared value using by more than one thread along reading,modifying and writing value of garlicCount.
 * We need control the accessing to garlicCount variable by one thread per time.
 */

class Shopper extends Thread{

    static int garlicCount = 0;

    @Override
    public void run() {
        for(int i=0;i<10_000_000;i++){
            garlicCount++;
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
