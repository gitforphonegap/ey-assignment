package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is created for Part 1 assignment 5.
 */
public class Master5 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        String totalWorkerPostResponseQueueThreads = args[0];

        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>();
        SimpleQueue<Double> responseQueue = new SimpleQueue<>();

        int totalThreads = Integer.parseInt(totalWorkerPostResponseQueueThreads);
        List<WorkerPostResponseQueue> workerPostResponseQueues = setUpAndExecuteWorkerPostResponseQueuePool(blockingQueue, responseQueue, totalThreads);

        for (int i = 1; i < 10000; i++) {
            if (i % 100 == 0) {
                blockingQueue.add(i);
            }
        }

        while (workerPostResponseQueues.stream().filter(w -> w.getStatus().equals("COMPLETED")).count() != totalThreads) {
            Thread.sleep(10000);
        }

        double logtotal = calculateLogTotal(responseQueue);

        long end = System.currentTimeMillis();

        System.out.println("Total value of log total: " + logtotal);
        System.out.println("Total Processing time: " + (end - start) / 1000 + "seconds");
    }

    private static double calculateLogTotal(SimpleQueue<Double> responseQueue) {
        double count = 0;

        SimpleQueue<Double> temp = responseQueue.front;

        while (temp != null) {
            count = count + temp.data;

            temp = temp.next;
        }

        return count;
    }

    private static List<WorkerPostResponseQueue> setUpAndExecuteWorkerPostResponseQueuePool(BlockingQueue blockingQueue, SimpleQueue<Double> responseQueue, int threadCount) {
        List<WorkerPostResponseQueue> workerPostResponseQueues = new ArrayList<>();

        for (int i = 1; i <= threadCount; i++) {
            WorkerPostResponseQueue workerPostResponseQueue = new WorkerPostResponseQueue(blockingQueue, responseQueue,"WorkerPostResponseQueue" + i);

            workerPostResponseQueues.add(workerPostResponseQueue);

            new Thread(workerPostResponseQueue).start();
        }

        return workerPostResponseQueues;
    }
}
