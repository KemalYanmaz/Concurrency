/*
Reente


 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Buyer extends Thread{
    static int garlicCount, potatoCount = 0;
    static Lock pencil = new ReentrantLock();

    private void addGarlic(){
        pencil.lock();
        garlicCount++;
        pencil.unlock();
    }

    private void addPotato(){
        pencil.lock();
        potatoCount++;
        pencil.unlock();
    }
    @Override
    public void run() {
        for(int i=0;i<10_000;i++){
            addGarlic();
            addPotato();
        }
    }
}
public class Exercise5 {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Buyer();
        Thread olivia = new Buyer();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy "+ Buyer.garlicCount + " garlic.");
        System.out.println("We should buy "+ Buyer.potatoCount + " potatoes.");
    }
}
