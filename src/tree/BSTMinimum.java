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
	
	public void addUsingIteration(Item item) {
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
	
	public void addUsingRecursion(Item item) {
		Node node = recursiveAddHelper(root, item);
		if(root == null) {
			root = node;
		}
	}
	
	private Node recursiveAddHelper(Node node, Item item) {
		if(node == null) {
			node = new Node(item);
		} else {
			if(item.compareTo(node.item) < 0) {
				node.left = recursiveAddHelper(node.left, item);
			} else {
				node.right = recursiveAddHelper(node.right, item);
			}
		}
		return node;
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
			bst.addUsingIteration(a);
//			bst.addUsingRecursion(a);
		}
		System.out.println(bst.getMinimumElement());
	}
}