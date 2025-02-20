package assignment;

public class BlockingQueue<T> {
    T data;

    BlockingQueue<T> front;
    BlockingQueue<T> rear;
    BlockingQueue<T> next;

    int size = 0;

    public BlockingQueue() {
    }

    public BlockingQueue(T data) {
        this.data = data;
    }

    public void add(T data) throws InterruptedException {
        while(size > 5){
            Thread.sleep(1000);
        }

        if(front == null){
            front = this;
            next = null;

            this.data = data;
        } else if (rear == null){
            BlockingQueue<T> current = new BlockingQueue<T>(data);

            rear = front;
            front = current;
            front.next = rear;
        } else {
            BlockingQueue<T> current = new BlockingQueue<T>(data);

            BlockingQueue<T> temp = front;

            front = current;
            front.next = temp;
        }

        size++;
    }

    public T remove(){
        BlockingQueue<T> node = front;
        BlockingQueue<T> previousNode = null;

        if(node == null) {
            return null;
        }

        while (node.next != null) {
            previousNode = node;
            node = node.next;
        }

        if(previousNode != null){
            previousNode.next = null;
        } else {
            front = null;
        }

        size--;

        return node.data;
    }

    public boolean isEmpty(){
        return front == null;
    }
}
