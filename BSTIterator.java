import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTIterator<T extends Comparable<T>> implements Iterator<T>{
    private Node<T> pointer;

    public BSTIterator(LinkedQueue<T> queue) {
        pointer = queue.getHead();
    }

    public BSTIterator(BST<T> binaryTree) {
    
    }

    public boolean hasNext(){
        return pointer != null;
    }

    public T next() {
        if (pointer == null) {
            throw new NoSuchElementException();
        }
        
        T obj = pointer.getData();
        pointer = pointer.getNext();

        return  obj;
    }




}
