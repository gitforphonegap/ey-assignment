package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is created for Part 1 assignment 2.
 */
public class Master2 {
       public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        String totalWorkerThreads = args[0];
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<Integer>();

        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                simpleQueue.add(i);
            }
        }

       int totalThreads = Integer.parseInt(totalWorkerThreads);

       List<Worker> workers = setUpAndExecuteWorkerPool(simpleQueue, totalThreads);

        while (workers.stream().filter(w -> w.getStatus().equals("COMPLETED")).count() != totalThreads) {
            Thread.sleep(10000);
        }

        long end = System.currentTimeMillis();

        System.out.println("Total Processing time: " + (end - start)/1000 + "seconds");
    }

    private static List<Worker> setUpAndExecuteWorkerPool(SimpleQueue simpleQueue, int threadCount) {
        List<Worker> workers = new ArrayList<Worker>();

        for (int i = 1; i <= threadCount; i++) {
            Worker worker = new Worker(simpleQueue, "Worker" + i);

            workers.add(worker);

            new Thread(worker).start();
        }

        return workers;
    }
}
