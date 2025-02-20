package assignment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkerBlockingQueue implements Runnable {
    private String threadName;

    private String status;

    private BlockingQueue<Integer> blockingQueue;

    public WorkerBlockingQueue(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public WorkerBlockingQueue(BlockingQueue<Integer> blockingQueue, String threadName) {
        this.blockingQueue = blockingQueue;
        this.threadName = threadName;
        this.status = "STARTED";
    }

    @Override
    public void run() {
        while(blockingQueue.isEmpty()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        while (!blockingQueue.isEmpty()) {
            Integer request = blockingQueue.remove();

            processIndividualRequest(request);
        }

        status = "COMPLETED";
    }

    private void processIndividualRequest(int request) {
        try {
            Thread.sleep(request);

            double logValue = Math.log(request);

            System.out.println("Incoming request " + request + "\n Printing log Value for request" + logValue + "\n Processed By Worker " + threadName);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
