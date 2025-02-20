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
public class WorkerPostResponseQueue implements Runnable {
    private  SimpleQueue<Double> responseQueue;
    private  BlockingQueue<Integer> blockingQueue;

    private String threadName;

    private String status;

    public WorkerPostResponseQueue(BlockingQueue blockingQueue, SimpleQueue<Double> responseQueue, String threadName) {
        this.blockingQueue = blockingQueue;
        this.responseQueue = responseQueue;
        this.threadName = threadName;
        this.status = "STARTED";
    }

    @Override
    public void run() {
        while(blockingQueue.isEmpty()){
            try {
                Thread.sleep(2000);
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

            responseQueue.add(logValue);

            System.out.println("Incoming request " + request + "\n Printing log Value for request" + logValue + "\n Processed By Worker " + threadName);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
