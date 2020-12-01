import java.util.NoSuchElementException;

/**
 * Queue data structure based off a linked list. Creates an unbounded queue-like linked list that uses FIFO ordering. Elements are added to the tail of the queue. 
 * Only the head of the queue is accessible, and must be removed to access subsequent nodes.
 * 
 * @author DeerByte
 * 
 */

public class LinkedQueue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Default constructor. Instantiates an empty LinkedQueue.
     */
    LinkedQueue() {
        head = null;
        size = 0;
    }

    /**
     * Constructor. Instantiates a LinkedQueue object with the argument data as the first node. 
     * @param data - Data used to instantiate head node. 
     */
    LinkedQueue(T data) {
        head = new Node<T>(data);
        size = 1;
    }

    /**
     * Adds the element to the end of the Queue. If the Queue is empty, the element is added as the head. 
     * @param e - Element added to LinkedQueue.
     */
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
        size++;
    }

    /**
     * Returns the head of the LinkedQueue without removing the element. 
     * @return T - element at head of queue
     * @throws NoSuchElementException - if the queue is empty
     */
    public T peek() {
        return head.getData(); 
    }

    /**
     * Returns the head of the LinkedQueue, removing the element
     * @return T - element at head of queue
     * @throws NoSuchElementException - if the queue is empty
     */
    public T poll() {
        return remove();
    }

    /**
     * Returns the node at the head of the queue. Returns null if no such element exists.
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * Returns the head of the queue and removes the element, replacing the head with the next element in the queue if it exists. 
     * @return T - element at head of queue
     * @throws NoSuchElementException
     */
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
        size--;

        return returnNode.getData();
    }

    public int size() {
        return size;
    }

    
}
