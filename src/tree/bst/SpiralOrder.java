package tree.bst;

import queue.Deque;
import stack.Stack;

public class SpiralOrder<Item extends Comparable<Item>> {
	private Node root;
	
	class Node {
		Item item;
		Node left;
		Node right;
		
		public Node(Item item) {
			this.item = item;
			this.left = this.right = null;
		}
	}
	
	public void add(Item item) {
		root = add(root, item);
	}
	
	private Node add(Node node, Item item) {
		if(node == null) {
			return new Node(item);
		} else {
			int cmp = item.compareTo(node.item);
			if(cmp < 0) {
				node.left = add(node.left, item);
			} else if(cmp > 0) {
				node.right = add(node.right, item);
			} else {
				node.item = item;
			}
			return node;
		}
	}
	
	public void inorder() {
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
	
	public void spiralUsingTwoStacks() {
		Stack<Node> stack1 = new Stack<Node>();
		Stack<Node> stack2 = new Stack<Node>();
		
		stack1.push(root);
		int remEl = stack1.size();
		boolean leftToRight = true;
		
		while(!stack1.isEmpty()) {
			Node node = stack1.pop();
			if(node != null) {
				System.out.print(node.item + " ");
				if(leftToRight) {
					stack2.push(node.left);
					stack2.push(node.right);
				} else {
					stack2.push(node.right);
					stack2.push(node.left);
				}
			}
			remEl -= 1;
			
			if(remEl == 0) {
				Stack<Node> temp = stack1;
				stack1 = stack2;
				stack2 = temp;
				
				leftToRight = !leftToRight;
				remEl = stack1.size();
				System.out.println();
			}
		}
	}
	
	public void spiralUsingDeque() {
		Deque<Node> dek = new Deque<Node>();
		dek.addFirst(root);
		
		boolean leftToRight = true;
		int remEl = dek.size();
		
		while(!dek.isEmpty()) {
			if(leftToRight) {
				Node node = dek.removeFirst();
				if(node != null) {
					System.out.print(node.item + " ");
					dek.addLast(node.left);
					dek.addLast(node.right);
				}
			} else {
				Node node = dek.removeLast();
				if(node != null) {
					System.out.print(node.item + " ");
					dek.addFirst(node.right);
					dek.addFirst(node.left);
				}
			}
			
			remEl -= 1;
			if(remEl == 0) {
				remEl = dek.size();
				leftToRight = !leftToRight;
				System.out.println();
			}
		}
	}
	
	
	public static void main(String[] args) {
		Integer[] input = {5, 3, 7, 1, 9};
		SpiralOrder<Integer> tree = new SpiralOrder<Integer>();
		for(Integer num: input) {
			tree.add(num);
		}
		
		tree.inorder();
		tree.spiralUsingTwoStacks();
		tree.spiralUsingDeque();
	}
}