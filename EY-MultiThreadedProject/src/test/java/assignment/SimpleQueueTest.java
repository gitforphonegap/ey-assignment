package assignment;

import org.junit.jupiter.api.Test;

class SimpleQueueTest {
    @Test
    void add() {
        SimpleQueue<Integer> queue = createMockSimpleQueue();

        SimpleQueue<Integer> temp = queue.front;

        while(temp != null) {
            System.out.println(temp.data);

            temp = temp.next;
        }
    }

    @Test
    void remove() {
        SimpleQueue<Integer> queue = createMockSimpleQueue();

        SimpleQueue<Integer> temp = queue.front;

        while(temp != null) {
            System.out.println(temp.data);

            temp = temp.next;
        }
    }

    private SimpleQueue<Integer> createMockSimpleQueue() {
        SimpleQueue<Integer> queue = new SimpleQueue<Integer>();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(7);

        return queue;
    }
}