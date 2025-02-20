package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is created for Part 1 assignment 3.
 */
public class Master3 {
       public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        String totalWorkerThreads = args[0];
        PriorityQueue priorityQueue = new PriorityQueue();

        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                priorityQueue.add(i);
            }
        }

       int totalThreads = Integer.parseInt(totalWorkerThreads);

       List<WorkerPriorityQueue> workers = setUpAndExecuteWorkerPool(priorityQueue, totalThreads);

        while (workers.stream().filter(w -> w.getStatus().equals("COMPLETED")).count() != totalThreads) {
            Thread.sleep(10000);
        }

        long end = System.currentTimeMillis();

        System.out.println("Total Processing time: " + (end - start)/1000 + "seconds");
    }

    private static List<WorkerPriorityQueue> setUpAndExecuteWorkerPool(PriorityQueue priorityQueue, int threadCount) {
        List<WorkerPriorityQueue> workers = new ArrayList<WorkerPriorityQueue>();

        for (int i = 1; i <= threadCount; i++) {
            WorkerPriorityQueue worker = new WorkerPriorityQueue(priorityQueue, "Worker" + i);

            workers.add(worker);

            new Thread(worker).start();
        }

        return workers;
    }
}
