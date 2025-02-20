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
public class Worker implements Runnable {
    private String threadName;

    private String status;

    private SimpleQueue<Integer> simpleQueue;

    public Worker(SimpleQueue<Integer> simpleQueue) {
        this.simpleQueue = simpleQueue;
    }

    public Worker(SimpleQueue<Integer> simpleQueue,String threadName) {
        this.simpleQueue = simpleQueue;
        this.threadName = threadName;
        this.status = "STARTED";
    }

    @Override
    public void run() {
        while (!simpleQueue.isEmpty()) {
            Integer request = simpleQueue.remove();

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
