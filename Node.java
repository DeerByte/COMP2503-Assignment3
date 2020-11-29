/**
 * @author Justin 
 * Node class with private variables, constructors, get/set, toString methods
 */
public class Node<T> {

    private T data;
    private Node<T> next;

    public Node() {
        data = null;
        next = null;
    }
    /**
     * Constructor for objects of class Node
     */
    public Node(T data) {
        this.data = data;
        next = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T o) {
        data = o;
        }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> n) {
        next = n;
    }

    public boolean hasNext() {
        return next != null;
    }

    public String toString() {
        return "Node: " + getData().toString();
    }

}
