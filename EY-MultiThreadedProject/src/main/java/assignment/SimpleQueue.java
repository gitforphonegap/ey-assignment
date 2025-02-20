package assignment;

public class SimpleQueue<T> {
    T data;

    SimpleQueue<T> front;
    SimpleQueue<T> rear;
    SimpleQueue<T> next;

    public SimpleQueue() {
    }

    public SimpleQueue(T data) {
        this.data = data;
    }

    public void add(T data){
        if(front == null){
            front = this;
            next = null;

            this.data = data;
        } else if (rear == null){
            SimpleQueue<T> current = new SimpleQueue<T>(data);

            rear = front;
            front = current;
            front.next = rear;
        } else {
            SimpleQueue<T> current = new SimpleQueue<T>(data);

            SimpleQueue<T> temp = front;

            front = current;
            front.next = temp;
        }
    }

    public T remove(){
        SimpleQueue<T> node = front;
        SimpleQueue<T> previousNode = null;

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

        return node.data;
    }

    public boolean isEmpty(){
        return front == null;
    }
}
