package tree.bst;
/*
 * Solution:
 * http://www.geeksforgeeks.org/floor-and-ceil-from-a-bst/
 * 
 */

public class BSTFloorCeiling<Item extends Comparable<Item>> {
	private Node root;
	
	public static void main(String[] args) {
		Integer[] input = {20, 8, 22, 4, 13, 10, 14};
		BSTFloorCeiling<Integer> bst = new BSTFloorCeiling<Integer>(input);
		assert bst.floor(5).compareTo(4) == 0;
		assert bst.ceiling(12).compareTo(13) == 0;
		System.out.println("ALL TESTS COMPLETED");
	}
	
	class Node {
		Item item;
		Node left;
		Node right;
		
		public Node(Item item) {
			this.item = item;
			this.left = this.right = null;
		}
	}
	
	public BSTFloorCeiling(Item[] items) {
		for(Item i: items) {
			add(i);
		}
	}
	
	public void add(Item item) {
		Node node = add(root, item);
		if(root == null) { root = node; }
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
			return node;
		}
	}
	
	public void inorder() {
		inorder(root);
	}
	
	private void inorder(Node node) {
		if(node == null) {
			return;
		} else {
			inorder(node.left);
			System.out.print(node.item + " ");
			inorder(node.right);
		}
	}
	
	public Item floor(Item item) {
		Node node = floor(root, item);
		if(node == null) { return null; }
		else { return node.item; }
	}
	
	private Node floor(Node node, Item item) {
		if(node == null) {
			return null;
		} else {
			int cmp = item.compareTo(node.item);
			if(cmp == 0) { return node; }
			if(cmp < 0) { return floor(node.left, item); }
			Node temp = floor(node.right, item);
			if(temp == null) {
				return node;
			} else {
				return temp;
			}
		}
	}
	
	public Item ceiling(Item item) {
		Node node = ceiling(root, item);
		if(node == null) { return null; }
		else { return node.item; }
	}
	
	private Node ceiling(Node node, Item item) {
		if(node == null) {
			return null; 
		} else {
			int cmp = item.compareTo(node.item);
			if(cmp == 0) { return node; }
			if(cmp < 0) {
				Node temp = ceiling(node.left, item);
				if(temp == null) {
					return node;
				} else {
					return temp;
				}
			}
			return ceiling(node.right, item);
		}
	}
}