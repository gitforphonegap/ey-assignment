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
public class WorkerPriorityQueue implements Runnable {
    private String threadName;

    private String status;

    private PriorityQueue priorityQueue;

    public WorkerPriorityQueue(PriorityQueue priorityQueue) {
        this.priorityQueue = priorityQueue;
    }

    public WorkerPriorityQueue(PriorityQueue priorityQueue, String threadName) {
        this.priorityQueue = priorityQueue;
        this.threadName = threadName;
        this.status = "STARTED";
    }

    @Override
    public void run() {
        while (!priorityQueue.isEmpty()) {
            Integer request = priorityQueue.remove();

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
