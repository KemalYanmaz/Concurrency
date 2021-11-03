
/**
*
* Parallel execution
 * Accomplish a single task faster
 * Accomplish more tasks in a given time
 *
 * Salad Recipe
 * 1- Chop lettuce
 * 2- Chop cucumbers
 * 3- Chop tomatoes
 * 4- Chop onions
 * 5- Mix vegetables
 * 6- Add Dressing
 * The concurent tasks of salad recipe are may say as 1,2,3 and 4 since they does not require an order (order dependent)
*/

class VegetableChopper extends Thread{

    public int vegetable_count = 0;
    public static boolean chopping = true;

    public VegetableChopper(String name){
        this.setName(name);
    }

    public void run(){
        while(chopping){
            System.out.println(this.getName() + " chopped a vegetable!");
            vegetable_count++;
        }
    }
}
public class Exercise1 {
    public static void main(String[] args) throws InterruptedException {
        VegetableChopper barron = new VegetableChopper("Barron");
        VegetableChopper olivia = new VegetableChopper("Olivia");

        barron.start();                 // Barron start chopping
        olivia.start();                 // Olivia start chopping
        Thread.sleep(1000);       // Continue chopping 1 second
        VegetableChopper.chopping = false; // Stop chopping

        barron.join();
        olivia.join();
        System.out.format("Barron chopped %d vegetables.\n", barron.vegetable_count);
        System.out.format("Olivia chopped %d vegetables.\n", olivia.vegetable_count);
    }
}
