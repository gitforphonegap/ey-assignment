package assignment;

import org.junit.jupiter.api.Test;

class PriorityQueueTest {
    @Test
    void add() {
        PriorityQueue queue = createMockPriorityQueue();

        PriorityQueue temp = queue.front;

        while(temp != null) {
            System.out.println(temp.data);

            temp = temp.next;
        }
    }

    @Test
    void remove() {
        PriorityQueue queue = createMockPriorityQueue();

        PriorityQueue temp = queue.front;

        while(temp != null) {
            System.out.println(temp.data);

            temp = temp.next;
        }
    }

    private PriorityQueue createMockPriorityQueue() {
        PriorityQueue queue = new PriorityQueue();

        queue.add(2000);
        queue.add(300);
        queue.add(500);
        queue.add(800);
        queue.add(600);
        queue.add(9900);
        queue.add(2200);

        return queue;
    }
}