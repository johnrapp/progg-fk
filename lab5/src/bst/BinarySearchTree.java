package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<>(x);
            size++;
            return true;
		} else {
			return add(root, x);
		}
	}

	private boolean add(BinaryNode<E> node, E x) {
		int cmp = x.compareTo(node.element);
		if (cmp > 0) {
			// x ">" element
			if (node.right != null) {
				add(node.right, x);
			} else {
			    size++;
				node.right = new BinaryNode<>(x);
			}
		} else if (cmp < 0) {
			// x "<" element
			if (node.left != null) {
				add(node.left, x);
			} else {
                size++;
                node.left = new BinaryNode<>(x);
			}
		} else {
			// x "=" element, i.e duplicate
			return false;
		}
		return true;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> node) {
	    if (node == null) {
	        return 0;
        }
	    return 1 + Math.max(height(node.left), height(node.right));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
        print(root);
	}

	private void print(BinaryNode<E> node) {
	    if (node.left != null) {
            print(node.left);
        }
		System.out.println(node.element);
        if (node.right != null) {
            print(node.right);
        }
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		return 0;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		return null;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
