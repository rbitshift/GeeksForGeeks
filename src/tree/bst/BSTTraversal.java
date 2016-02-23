package tree.bst;

import java.util.Stack;
import java.util.Vector;

public class BSTTraversal<Item extends Comparable<Item>> {
	private Node root;
	
	public static void main(String[] args) {
		Integer[] input = {10, 5, 1, 7, 40, 50};
		BSTTraversal<Integer> bst = new BSTTraversal<Integer>();
		for(Integer num: input) {
			bst.put(num);
		}
		
		bst.preorder();
		bst.preorderItr();
		
		bst.inorder();
		bst.inorderItr();
		bst.inorderMorris();
		
		bst.postorder();
		bst.postorderItr();
		
		bst.levelorder();
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
	
	public void put(Item item) {
		root = put(root, item);
	}
	
	private Node put(Node node, Item item) {
		if(node == null) {
			return new Node(item);
		} else {
			int cmp = item.compareTo(node.item);
			if(cmp < 0) {
				node.left = put(node.left, item);
			} else if(cmp > 0) {
				node.right = put(node.right, item);
			} else {
				//over-write existing value;
				node.item = item;
			}
			return node;
		}
	}
	
	public void preorder() {
		preorder(root);
		System.out.println();
	}
	
	private void preorder(Node node) {
		if(node != null) {
			System.out.print(node.item + " ");
			preorder(node.left);
			preorder(node.right);
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
	
	public void postorder() {
		postorder(root);
		System.out.println();
	}
	
	private void postorder(Node node) {
		if(node != null) {
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.item + " ");
		}
	}
	
	public void levelorder() {
		Vector<Node> queue = new Vector<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node node = queue.remove(0);
			if(node != null) {
				System.out.print(node.item + " ");
				queue.add(node.left);
				queue.add(node.right);
			}
		}
		System.out.println();
	}

	public void preorderItr() {
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		while(!stack.isEmpty() || node != null) {
			if(node != null) {
				System.out.print(node.item + " ");
				stack.push(node.right);
				node = node.left;
			} else {
				node = stack.pop();
			}
		}
		System.out.println();
	}
	
	public void inorderItr() {
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		while(!stack.isEmpty() || node != null) {
			if(node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(node.item + " ");
				node = node.right;
			}
		}
		System.out.println();
	}
	
	public void postorderItr() {
		Stack<Node> stack = new Stack<Node>();
		Node node = root;
		Node last = null;
		while(!stack.isEmpty() || node != null) {
			if(node != null) {
				stack.push(node);
				node = node.left;
			} else {
				Node curr = stack.peek();
				if(curr.right != null && last != curr.right) {
					node = curr.right;
				} else {
					System.out.print(curr.item + " ");
					last = stack.pop();
				}
			}
		}
		System.out.println();
	}
	
	public void inorderMorris() {
		if(root == null) {
			return;
		} else {
			Node node = root;
			while(node != null) {
				if(node.left == null) {
					System.out.print(node.item + " ");
					node = node.right;
				} else {
					Node prev = node.left;
					while(prev.right != null && prev.right != node) {
						prev = prev.right;
					}
					
					if(prev.right == null) {
						prev.right = node;
						node = node.left;
					} else {
						prev.right = null;
						System.out.print(node.item + " ");
						node = node.right;
					}
				}
			}
		}
		System.out.println();
	}
}