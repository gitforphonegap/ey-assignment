package assignment;

public class PriorityQueue {
    Integer data;

    PriorityQueue front;
    PriorityQueue rear;
    PriorityQueue next;

    public PriorityQueue() {
    }

    public PriorityQueue(Integer data) {
        this.data = data;
    }

    public void add(Integer data) {
        int currentpriority = data % 200;

        if (front == null) {
            front = this;
            next = null;

            this.data = data;
        } else if (rear == null) {
            int frontPriority = (front.data) % 200;
            PriorityQueue current = new PriorityQueue(data);

            if (currentpriority > frontPriority) {
                rear = current;
            } else {
                rear = front;
                front = current;
                front.next = rear;
            }
        } else {
            int frontPriority = (front.data) % 200;
            PriorityQueue current = new PriorityQueue(data);

            if (currentpriority > frontPriority) {
                PriorityQueue temp = front;
                PriorityQueue previousNode = null;

                do {
                    int tempPriority = (temp.data) % 200;

                    if (currentpriority < tempPriority) {
                        break;
                    } else {
                        previousNode = temp;
                        temp = temp.next;
                    }
                } while (temp != null);

                previousNode.next = current;
                current.next = temp;
            } else {
                PriorityQueue temp = front;

                front = current;
                front.next = temp;
            }
        }
    }


    public Integer remove(){
        PriorityQueue node = front;
        PriorityQueue previousNode = null;

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
