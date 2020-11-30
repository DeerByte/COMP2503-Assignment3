import java.util.NoSuchElementException;

public class LinkedQueue<T> {
    private Node<T> head;
    private Node<T> tail;

    LinkedQueue() {
        head = null;
    }

    LinkedQueue(T data) {
        head = new Node<T>(data);
    }

    public void add(T e) {
        Node<T> node = new Node<>(e);

        if (head == null) {
            head = node;
            tail = node;

        } else if (head == tail) {
            
            head.setNext(node);
            tail = node;

        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public T peek() {
        return head.getData(); 
    }

    public T poll() {
        return remove();
    }

    public Node<T> getHead() {
        return head;
    }

    public T remove() {
        if (head == null) {
            throw new NoSuchElementException();
        } 
        Node<T> returnNode;

        if (head == tail) {
            returnNode = head;
            tail = null;
            head = null;

        } else {
            returnNode = head;
            head = head.getNext();
        }

        return returnNode.getData();
    }

    
}
