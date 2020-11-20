public class BST<T extends Comparable<T>> {
	class BSTNode implements Comparable<BSTNode> {
		private T data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(T d) {
			setLeft(null);
			setRight(null);
			setData(d);
		}

		public T getData() {
			return data;
		}

		public void setData(T d) {
			data = d;
		}

		public void setLeft(BSTNode l) {
			left = l;
		}

		public void setRight(BSTNode r) {
			right = r;
		}

		public BSTNode getLeft() {
			return left;
		}

		public BSTNode getRight() {
			return right;
		}

		public boolean isLeaf() {
			return (getLeft() == null) && (getRight() == null);
		}

		public int compareTo(BSTNode o) {
			return this.getData().compareTo(o.getData());
		}
	}
  }
