package tree.bst;

/*
 * Solution: 
 * http://www.geeksforgeeks.org/find-k-th-smallest-element-in-bst-order-statistics-in-bst/
 * Given root of binary search tree and K as input, find K-th smallest element in BST.
 */

public class BSTOrderStatistics<Item extends Comparable<Item>> {
	private Node root;
	
	class Node {
		Item item;
		Node left;
		Node right;
		int size;
		
		public Node(Item item) {
			this.item = item;
			this.size = 1;
			this.left = this.right = null;
		}
	}
	
	public BSTOrderStatistics(Item[] items) {
		for(Item item: items) {
			add(item);
		}
	}
	
	private int size(Node node) {
		if(node == null) { return 0; }
		else { return node.size; }
	}
	
	public void add(Item item) {
		root = add(root, item);
	}
	
	private Node add(Node node, Item item) {
		if(node == null) {
			return new Node(item);
		} else {
			if(item.compareTo(node.item) < 0) {
				node.left = add(node.left, item);
			} else {
				node.right = add(node.right, item);
			}
			node.size = 1 + size(node.left) + size(node.right); 
			return node;
		}
	}
	
	public void inorder() {
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node node) {
		if(node != null) {
			inorder(node.left);
			System.out.print(node.item + " ");
			inorder(node.right);
		}
	}
	
	public Item select(int k) {
		Node node = select(root, k);
		return node.item;
	}
	
	private Node select(Node node, int k) {
		if(node == null) { return null; }
		else {
			int t = size(node.left);
			if(k < t) {
				return select(node.left, k);
			} else if(k > t) {
				return select(node.right, k-t-1);
			} else {
				return node;
			}
		}
	}
	
	public int rank(Item item) {
		return rank(root, item);
	}
	
	private int rank(Node node, Item item) {
		if(node == null) {
			return 0;
		} else {
			int cmp = item.compareTo(node.item);
			if(cmp < 0) {
				return rank(node.left, item);
			} else if(cmp > 0) {
				return 1 + size(node.left) + rank(node.right, item); 
			} else {
				return size(node.left);
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] input = {20, 8, 22, 4, 12, 10, 14 };
		BSTOrderStatistics<Integer> bst = new BSTOrderStatistics<Integer>(input);
		assert bst.select(6) == 22;
		assert bst.rank(22) == 6;
		System.out.println("ALL TESTS COMPLETED");
	}
}