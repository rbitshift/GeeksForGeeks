package tree;

import java.util.NoSuchElementException;

public class BSTMinimum<Item extends Comparable<Item>> {
	private Node root;
	
	private class Node {
		Item item; Node left; Node right;
		
		Node(Item item) {
			this.item = item; this.left = null; this.right = null;
		}
	};
	
	public void add(Item item) {
		if(root == null) {
			root = new Node(item);
		} else {
			Node current = root; Node previous = null;
			while(current != null) {
				previous = current;
				if(item.compareTo(current.item) < 0) {
					current = current.left;
				} else {
					current = current.right;
				}
			}
			if(previous == null) {
				previous = root;
			}
			if(item.compareTo(previous.item) < 0) {
				previous.left = new Node(item);
			} else {
				previous.right = new Node(item);
			}
		}
	}
	
	public Item getMinimumElement() {
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
		BSTMinimum<Integer> bst = new BSTMinimum<Integer>();
		for(Integer a: input) {
			bst.add(a);
		}
		System.out.println(bst.getMinimumElement());
	}
}