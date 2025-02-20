package assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is created for Part 1 assignment 4.
 */
public class Master4 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        String totalWorkerBlockingQueueThreads = args[0];

        BlockingQueue<Integer> blockingQueue = new BlockingQueue<Integer>();

        int totalThreads = Integer.parseInt(totalWorkerBlockingQueueThreads);
        List<WorkerBlockingQueue> workerBlockingQueues = setUpAndExecuteWorkerBlockingQueuePool(blockingQueue, totalThreads);

        for (int i = 0; i < 10000; i++) {
            if (i % 100 == 0) {
                blockingQueue.add(i);
            }
        }

        while (workerBlockingQueues.stream().filter(w -> w.getStatus().equals("COMPLETED")).count() != totalThreads) {
            Thread.sleep(10000);
        }

        long end = System.currentTimeMillis();

        System.out.println("Total Processing time: " + (end - start) / 1000 + "seconds");
    }

    private static List<WorkerBlockingQueue> setUpAndExecuteWorkerBlockingQueuePool(BlockingQueue blockingQueue, int threadCount) {
        List<WorkerBlockingQueue> workerBlockingQueues = new ArrayList<WorkerBlockingQueue>();

        for (int i = 1; i <= threadCount; i++) {
            WorkerBlockingQueue workerBlockingQueue = new WorkerBlockingQueue(blockingQueue, "WorkerBlockingQueue" + i);

            workerBlockingQueues.add(workerBlockingQueue);

            new Thread(workerBlockingQueue).start();
        }

        return workerBlockingQueues;
    }
}
