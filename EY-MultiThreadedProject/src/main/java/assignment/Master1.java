package assignment;

/**
 * This class is created for Part 1 assignment 1.
 */
public class Master1 {
       public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        SimpleQueue<Integer> simpleQueue = new SimpleQueue<Integer>();

        Worker worker = new Worker(simpleQueue, "Worker");

        new Thread(worker).start();

        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                simpleQueue.add(i);
            }
        }

        while (!simpleQueue.isEmpty()){
            Thread.sleep(10);
        }

        long end = System.currentTimeMillis();

        System.out.println("Total Processing time: " + (end - start)/1000 + " seconds");
    }
}
