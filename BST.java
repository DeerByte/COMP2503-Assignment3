
import java.util.Comparator;

public class BST<T extends Comparable<T>> implements Iterable<T> {
    
    private Comparator<T> comparator;

    class BSTNode implements Comparable<BSTNode> {
        private T data;
        private BSTNode left;
        private BSTNode right;
        private int key;
        private BST<T>.BSTNode rightChild;
        private BST<T>.BSTNode leftChild;
        

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
         * 
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
            
            // Call compareTo method of an Avengers type Object
            return this.getData().compareTo(o.getData());
        }

        /* TODO: toString method of BSTNode class */
        @Override
        public String toString() {
            return "";
        }

        /* TODO: equals method of BSTNode class */
        @Override
        public boolean equals() {
            return false;
        }
    }
// node class end


        

        

    public static final int INORDER = 0;
    public static final int PREORDER = 1;
    public static final int POSTORDER = 2;

    private BSTNode root;
    private int size;

    /**
	 * Non-parameterized constructor of BST class. 
	 * Sets size at 0, and root as null. 
	 */
	public BST() {
		root = null;
        size = 0;
	}

    public BST(Comparator <T> comp){
        comparator = comp;
    }

    /**
     * TODO: find() javadoc of BST class Return true if element data is present in
     * the tree.
     * 
     * @return data
     */
    public T find(T data) {
        return find(data, root);
    }

    /**
     * Add data of <T> type into the binary search tree
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
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * TODO: javadoc
     */
    public void inOrder() {
        traverse(root, INORDER);
    }

    /**
     * TODO: javadoc
     */
    public void postOrder() {
        traverse(root, POSTORDER);
    }

    /**
     * TODO: javadoc
     */
    public void preOrder() {
        traverse(root, PREORDER);
    }

    public BSTIterator<T> iterator() {
        return new BSTIterator<T>(this);
    }

    /**
         * REMOVE METHOD, UNTESTED
         */
        public boolean remove(T other) {

            BSTNode toRemove = new BSTNode(other);
            BSTNode focus = root;
            BSTNode parent = root;
    
            boolean isItALeftChild = true;
    
            while (focus.compareTo(toRemove) != 0) {
    
                parent = focus;
    
                // To find out if we go left or right
    
                if (focus.compareTo(toRemove) == -1) {
    
                    isItALeftChild = true;
                    focus = focus.getLeft();
    
                } else {
    
                    isItALeftChild = false;
                    focus = focus.getRight();
    
                }
    
                // If node is not found false
    
                if (focus == null)
                    return false;
    
            }
    
            // Case of which node has no children
    
            if (focus.getLeft() == null && focus.getRight() == null) {
    
                // if it is just simply a root, we delete it

                if (focus == root) {}
                    root = null;
    
                // If it was marked as a left child
                // of the parent delete it in its parent
    
                else if (isItALeftChild)
                    parent.setLeft(null);
    
                // opposite for right
    
                else
                    parent.setRight(null);
    
            }
    
            // If no right child
    
            else if (focus.getRight() == null) {
    
                if (focus == root)
                    root = focus.leftChild;
    
                // If focus Node was on the left of parent
                // move the focus Nodes left child up to the
                // parent node
    
                else if (isItALeftChild)
                    parent.leftChild = focus.leftChild;
    
                else
                    parent.rightChild = focus.leftChild;
    
            }
    
            // If no left child
    
            else if (focus.leftChild == null) {
    
                if (focus == root)
                    root = focus.rightChild;
    
                // If focus Node was on the left of parent
                // move the focus Nodes right child up to the
                // parent node
    
                else if (isItALeftChild)
                    parent.leftChild = focus.rightChild;
    
                else
                    parent.rightChild = focus.rightChild;
    
            }
    
            // Case of two children we need to find a replacement node with a helper method
    
            else {

                BSTNode replacement = getReplacementNode(focus);
    
                // If the focusNode is root replace root
                // with the replacement
    
                if (focus == root)
                    root = replacement;
    
                // If the deleted node was a left child
                // make the replacement the left child
    
                else if (isItALeftChild)
                    parent.leftChild = replacement;
    
                // we do the opposite for right child
    
                else
                    parent.rightChild = replacement;
    
                replacement.leftChild = focus.leftChild;
    
            }  

            return true;
        }


    
    // Private methods.

    private T find(T data, BSTNode root) {
        if (root == null)
            return null;
        int c = data.compareTo(root.getData());

        if (c == 0)
            return root.getData();
        else if (c < 0)
            return find(data, root.getLeft());
        else
            return find(data, root.getRight());
    }

    /* Do the actual add of node n to tree rooted at r */
    private void add(BSTNode root, BSTNode node) {
        int index = node.compareTo(r);
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

    /* Implement a height method. */
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

    /*
     * Traverse the tree. travtype determines the type of traversal to perform.
     * 
     * **ORDERS**
     * 
     * 
     * 
     * 
     */
    private void traverse(BSTNode r, int travType) {
        if (r != null) {
            switch (travType) {
                case INORDER:
                    traverse(r.getLeft(), travType);
                    visit(r);
                    traverse(r.getRight(), travType);
                    break;
                case PREORDER:
                    visit(r);
                    traverse(r.getLeft(), travType);
                    traverse(r.getRight(), travType);
                    break;
                case POSTORDER:
                    traverse(r.getLeft(), travType);
                    traverse(r.getRight(), travType);
                    visit(r);
                    break;
            }
        }
    }

    private void visit(BSTNode root) {
        if (root != null)
            System.out.println(root.getData());
    }

    /* traverse the subtree r using level order. */
    private void levelOrder(BSTNode r) {
        BSTNode currNode = r;
        Queue<BSTNode> q = new Queue<BSTNode>();

        q.enqueue(currNode);

        while (!q.isEmpty()) {
            currNode = q.dequeue();
            visit(currNode);
            if (currNode.getLeft() != null)
                q.enqueue(currNode.getLeft());
            if (currNode.getRight() != null)
                q.enqueue(currNode.getRight());
        }
    }

    //Helper method for remove method to get the replacement node
    private BSTNode getReplacementNode(BSTNode replacedNode) {
    
        BSTNode replacementParent = replacedNode;
        BSTNode replacement = replacedNode;
        BSTNode focusNode = replacedNode.rightChild;

        // if there are no left children we do this

        while (focusNode != null) {

            replacementParent = replacement;

            replacement = focusNode;

            focusNode = focusNode.leftChild;

        }


        // moving the replacement node into the parent's leftchild node
        //and then move the replaced nodes righ child into the replacement's right child

        if (replacement != replacedNode.rightChild) {

            replacementParent.leftChild = replacement.rightChild;
            replacement.rightChild = replacedNode.rightChild;

        }
        return replacement;
    }

	public Avenger getData(Avenger hero) {
		return hero;
	}
}
