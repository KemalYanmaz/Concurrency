/*
tryLock method of Lock
 */


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class itemCounters extends Thread{

    private int itemsToAdd = 0; // items this shopper is waiting to add
    private static int itemsOnNotepad = 0; // total items on shared notepad
    private static Lock pencil = new ReentrantLock();

    public itemCounters(String name){
        setName(name);
    }

    @Override
    public void run() {
        while (itemsOnNotepad<20){
            if((itemsToAdd>0) && pencil.tryLock()){// add item(s) to shared notepad
                try{
                    itemsOnNotepad+=itemsToAdd;
                    System.out.println(this.getName() + " added "+ itemsToAdd+" item(s) to notepad.");
                    itemsToAdd = 0;
                    Thread.sleep(300);//time spend writing
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    pencil.unlock();
                }
            }else{ // look for other things to buy
                try{
                    Thread.sleep(100);
                    itemsToAdd++;
                    System.out.println(this.getName()+" found something to buy");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }
}

public class Exercise6 {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new itemCounters("Barron");
        Thread olivia = new itemCounters("Olivia");
        long start = System.currentTimeMillis();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        long finish = System.currentTimeMillis();
        System.out.println("Elapsed time: "+ (float) (finish-start)/1000+ " seconds");

    }
}
