package tree;

import java.util.NoSuchElementException;

public class BSTMinimum<Item extends Comparable<Item>> {
	private Node root;
	
	private class Node {
		Item item; Node left; Node right;
		
		Node(Item item) {
			this.item = item; 
			this.left = this.right = null;
		}
	};
	
	public BSTMinimum(Item[] items) {
		for(Item i: items) {
			add(i);
		}
	}
	
	public void add(Item item) {
		Node node = add(root, item);
		if(root == null) {
			root = node;
		}
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
	
	public Item getMin() {
		Node current = root;
		if(current == null) {
			throw new NoSuchElementException("BST is empty");
		} else {
			while(current.left != null) {
				current = current.left;
			}
		}
		return current.item;
	}
	
	public static void main(String[] args) {
		Integer[] input = {20, 8, 22, 4, 12, 10, 14};
		BSTMinimum<Integer> bst = new BSTMinimum<Integer>(input);
		assert bst.getMin().compareTo(4) == 0;
		
		System.out.println("ALL TESTS COMPLETED");
	}
}