/**
 * @author Justin, DeerByte
 * Node class with private variables, constructors, get/set, toString methods
 */
public class Node<T> {

    private T data = null;
    private Node<T> next = null;

    public Node() {

    }
    /**
     * Constructor for objects of class Node
     */
    public Node(T element) {
        this.data = element;
    }

    public Node(T element, Node<T> next) {
        data = element;
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T element) {
        data = element;
    }

    public Node<T> getNext() {
        return next;
    }
    
    public void setNext(Node<T> node) {
        next = node;
    }

    public boolean hasNext() {
        return next != null;
    }

    public String toString() {
        return "Node: " + getData().toString();
    }

}
