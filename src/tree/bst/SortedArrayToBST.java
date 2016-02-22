package tree.bst;

import java.util.Vector;

/*
 * Solution:
 * http://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
 * Given a sorted array. Write a function that creates a Balanced Binary Search Tree using array elements.
 * 
 */

public class SortedArrayToBST<Item extends Comparable<Item>> {
	private Node root;
	private int size;
	
	class Node {
		Item item;
		Node left;
		Node right;
		
		public Node(Item item) {
			this.item = item;
			this.left = this.right = null;
		}
	}
	
	public void inorder() {
		inorder(root);
	}
	
	private void inorder(Node node) {
		if(node != null) {
			inorder(node.left);
			System.out.print(node.item + " ");
			inorder(node.right);
		}
	}
	
	public void preorder() {
		preorder(root);
	}
	
	private void preorder(Node node) {
		if(node != null) {
			System.out.print(node.item + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	
	
	public void levelorder() {
		Vector<Node> vec = new Vector<Node>();
		vec.add(root);
		while(!vec.isEmpty()) {
			Node curr = vec.remove(0);
			if(curr != null) {
				vec.add(curr.left);
				vec.add(curr.right);
				System.out.print(curr.item + " ");
			}
		}
	}
	
	public void convert(Item[] item) {
		root = convert(item, 0, item.length-1);
	}
	
	private Node convert(Item[] item, int start, int end) {
		
		if(start > end) {
			return null;
		}
		
		int mid = (start + end)/2;
		Node node = new Node(item[mid]);
		node.left = convert(item, start, mid-1);
		node.right = convert(item, mid+1, end);
		return node;
	}
	
	public static void main(String[] args) {
		Integer[] input = {1, 2, 3, 4};
		SortedArrayToBST<Integer> obj = new SortedArrayToBST<Integer>();
		obj.convert(input);
		obj.levelorder();
	}	
}