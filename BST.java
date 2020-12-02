import java.util.Comparator;
import java.util.Iterator;

/**
 * Binary Search Tree structure. Methods for adding, removing, traversing, finding, as well as a few helper methods
 * 
 * @author Hoang Vo, Justin Gajer
 * 
 */

public class BST<T extends Comparable<T>> implements Iterable<T> {
    
    private Comparator<T> comparator;
    public static final int INORDER = 0;
    public static final int PREORDER = 1;
    public static final int POSTORDER = 2;
    private BSTNode root;
    private int size;

    /**
	 * Non-parameterized constructor of BST class. 
     * Follows natural ordering of the data objects
	 * Sets size at 0, and root as null. 
	 */
	public BST() {
		root = null;
        size = 0;
	}

    /**
     * Constructor. Instantiates a BSTobject with the argument of a comparator added
     * @param Comparator - Comparator used by the driver class to order
     */
    public BST(Comparator <T> comparator){
        root = null;
        size = 0;
        this.comparator = comparator;
    }
    
    
    class BSTNode implements Comparable<BSTNode> {
        private T data;
        private BSTNode left;
        private BSTNode right;
        private BST<T>.BSTNode rightChild;
        

        /**
         * Non-parameterized BSTNode constructor. Sets all instance variables to null.
         */
        public BSTNode() {
            setLeft(null);
            setRight(null);
            setData(null);
        }

        /**
         * BSTNode constructor taking one parameter of <T> type data.
         * @param d given data point of <T> type inserted into a node
         */
        public BSTNode(T data) {
            setLeft(null);
            setRight(null);
            setData(data);
        }

        /* Getters & Setters */
        public T getData() {
            return data;
        }

        public void setData(T d) {
            this.data = d;
        }

        public void setLeft(BSTNode l) {
            this.left = l;
        }

        public BSTNode getLeft() {
            return left;
        }

        public void setRight(BSTNode r) {
            this.right = r;
        }

        public BSTNode getRight() {
            return right;
        }

        public boolean isLeaf() {
            return (getLeft() == null) && (getRight() == null);
        }

        @Override
        public int compareTo(BST<T>.BSTNode o) {
            return this.getData().compareTo(o.getData());
        }

        // Might need further implementing
        @Override
        public String toString() {
            return getData().toString();
        }

        @Override
        public boolean equals(Object other) {
            return data.equals(other);
        }
    } //Node Class End 

    /**
     * Finds height of the tree
     * @param data
     * @return data, root from the private find method
     */
    public T find(T data) {
        return find(data, root);
    }

    /**
     * Add data of <T> type into the binary search tree
     * @param data of T type
     */
    public void add(T data) {
        BSTNode node = new BSTNode(data);
        if (root == null) {
            size++;
            root = node;
        } else {
            add(root, node);
        }
    }

    /**
     * Returns height value of the binary search tree -1 if the tree is null. Height
     * index begins at 0.
     * 
     * @return height value of the binary seach tree
     */
    public int height() {
        return height(root);
    }

    /**
     * Return the number of nodes in the tree.
     * 
     * @return size of int type
     */
    public int size() {
        return size;
    }

    /**
     * Print method for in order they appear
     */
    public LinkedQueue<T> inOrder() {
        LinkedQueue<T> queue = new LinkedQueue<>();
        traverse(root, queue, INORDER);
        return queue;
    }

    /**
     * Print method for post order
     */
    public LinkedQueue<T> postOrder() {
        LinkedQueue<T> queue = new LinkedQueue<>();
        traverse(root, queue, POSTORDER);
        return queue;
    }

    /**
     * Print method for pre order
     */
    public LinkedQueue<T> preOrder() {
        LinkedQueue<T> queue = new LinkedQueue<>();
        traverse(root, queue, PREORDER);
        return queue;
    }

    /**
     * Iterator create
     * @return iterator
     */
    public Iterator<T> iterator() {
        return new BSTIterator<T>(this);
    }

    /**
     * Remove method
     * @param other - Generic data type (avenger)
     */
    public boolean remove(T other) {

        BSTNode toRemove = new BSTNode(other);
        BSTNode focus = root;
        BSTNode parent = root;

        boolean isItALeftChild = true;

        if (isNaturalOrder()) {
            while (focus.compareTo(toRemove) != 0) {

                parent = focus;
    
                // To find out if we go left or right
                if (focus.compareTo(toRemove) > 0) {
                    isItALeftChild = true;
                    focus = focus.getLeft();
                } else {
                    isItALeftChild = false;
                    focus = focus.getRight();
                }
                // If node is not found false
                if (focus == null) {
                    return false;
                }
    
            }
        }
        else {
            while (comparator.compare(focus.getData(), toRemove.getData()) != 0) {

                parent = focus;
    
                // To find out if we go left or right
                if (comparator.compare(focus.getData(), toRemove.getData()) < 0) {
                    isItALeftChild = true;
                    focus = focus.getLeft();
                } else {
                    isItALeftChild = false;
                    focus = focus.getRight();
                }
                // If node is not found false
                if (focus == null) {
                    return false;
                }
            }
        }
        

        // Case of which node has no children
        if (focus.getLeft() == null && focus.getRight() == null) {

            // if it is just simply a root, we delete it
            if (focus == root) {
                root = null;
            }
            // If it was marked as a left child
            // of the parent delete it in its parent
            else if (isItALeftChild) {
                parent.setLeft(null);
            }
            // opposite for right
            else {
                parent.setRight(null);
            }
        }

        // If no right child
        else if (focus.getRight() == null) {

            if (focus == root) {
                root = focus.getLeft();
            }
            // If focus Node was on the left of parent
            // move the focus Nodes left child up to the
            // parent node
            else if (isItALeftChild) {
                parent.setLeft(focus.getLeft());
            }
            else {
                parent.setRight(focus.getLeft());
            }

        }

        // If no left child
        else if (focus.getLeft() == null) {

            if (focus == root) {
                root = focus.getRight();
            }
            // If focus Node was on the left of parent
            // move the focus Nodes right child up to the
            // parent node
            else if (isItALeftChild) {
                parent.setLeft(focus.getRight());
            }
            else {
                parent.setRight(focus.getRight());
            }
        }

        // Case of two children we need to find a replacement node with a helper method

        else {

            BSTNode replacement = getReplacementNode(focus);

            // If the focus is root replace root
            // with the replacement
            if (focus == root) {
                root = replacement;
            }
            // If the deleted node was a left child
            // make the replacement the left child
            else if (isItALeftChild) {
                parent.setLeft(replacement);
            }
            // we do the opposite for right child
            else {
                parent.setLeft(replacement);
            }
            replacement.setLeft(focus.getLeft());
        }  

        return true;
    }
    
    // Private methods.

    /**
     * find method
     * @param data - Generic Type
     * @param root - BSTNode
     * @return T
     */
    private T find(T data, BSTNode root) {
        int c;
        if (root == null) {
            return null;
        }

        if (isNaturalOrder()) {
            c = data.compareTo(root.getData());
        }
        else {
            c = comparator.compare(data, root.getData());
        }

        if (c == 0) {
            return root.getData();
        }
        else if (c < 0) {
            return find(data, root.getLeft());
        }
        else {
            return find(data, root.getRight());
        }
    }

    /**
     * 
     * add method for adding an element to the tree
     * @param root BSTNode
     * @param node BSTNode
     * 
     */
    private void add(BSTNode root, BSTNode node) {

        int index;
        if (isNaturalOrder()) {
            index = node.compareTo(root);
        }
        else {
            index = comparator.compare(root.getData(), node.getData());
        }
        
        if (index < 0) {
            // the element to be added is less than the root
            if (root.getLeft() == null) {
                // there is no left node so
                // we can add it here
                root.setLeft(node);
                size++;
            } else {
                // add it to the left sub-tree
                add(root.getLeft(), node);
            }
        } else if (index > 0) {
            // the element to be added is greater than the root
            if (root.getRight() == null) {
                // there is no right node so
                // we can add it here
                root.setRight(node);
                size++;
            } else {
                // add it to the right sub-tree
                add(root.getRight(), node);
            }
        }
    }

    /**
     * finds the height of the tree
     * @param Node - BSTNode
     * @return int
     */
    private int height(BSTNode Node) {
        if (Node == null) {
            return -1;
        }

        int leftheight = height(Node.left);
        int rightheight = height(Node.right);

        if (leftheight > rightheight) {
            return leftheight + 1;
        } else {
            return rightheight + 1;
        }
    }

    /**
     * 
     * Traverses the tree using switch case base on order
     * @param r - BSTNode
     * @param travetype - int
     * 
     */
    private void traverse(BSTNode r, LinkedQueue<T> queue, int travType) {
        if (r != null) {
            switch (travType) {
                case INORDER:
                    traverse(r.getLeft(), queue, travType);
                    visit(r, queue);
                    traverse(r.getRight(), queue, travType);
                    break;
                    
                case PREORDER:
                    visit(r, queue);
                    traverse(r.getLeft(), queue, travType);
                    traverse(r.getRight(), queue, travType);
                    break;

                case POSTORDER:
                    traverse(r.getLeft(), queue, travType);
                    traverse(r.getRight(), queue, travType);
                    visit(r, queue);
                    break;
            }
        }
    }
    
    /**
     * 
     * visit helper method for traversal method. Returns null if empty or it will print the root data
     * @param root - BSTNode
     * 
     */
    private void visit(BSTNode root, LinkedQueue<T> queue) {
        if (root != null) {
            queue.add(root.getData());
        }
    }

    /**
     * Helper method for remove method to get the replacement node
     * @param replacedNode - BSTNode
     * @return BSTNode
     * 
     */
    private BSTNode getReplacementNode(BSTNode replacedNode) {
    
        BSTNode replacementParent = replacedNode;
        BSTNode replacement = replacedNode;
        BSTNode focus = replacedNode.rightChild;

        // find if no left child
        while (focus != null) {
            replacementParent = replacement;
            replacement = focus;
            focus = focus.getLeft();
        }

        //moving the replacement node into the parent's leftchild node
        //then move the replaced nodes right child into the replacement's right child
        if (replacement != replacedNode.getRight()) {
            replacementParent.setLeft(replacement.getRight());
            replacement.setRight(replacedNode.getRight());
        }
        return replacement;
    }

    /**
     * Method for retrieving avenger data
     * @param hero - T generic type
     * @param root - BSTNode
     */
	public T getData(T hero) {
		return hero;
    }
    
    private boolean isNaturalOrder() {
        if (comparator == null) {
            return true;
        }
        return false;
    }
}