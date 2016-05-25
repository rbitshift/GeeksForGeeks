package queue;

import stack.Stack;

public class TreeTraversal<Item extends Comparable<Item>> {
	Node root;
	
	class Node {
		Item item;
		Node left;
		Node right;
		
		public Node(Item item) {
			this.item = item;
			this.left = null;
			this.right = null;
		}
	}
	
	public void buildCompleteBinaryTree(Item[] input) {
		if(input.length <= 0) {
			System.out.println("Invalid length of the input");
			return;
		}
		Queue<Node> queue = new Queue<Node>();
		if(root == null) {
			root = new Node(input[0]);
			queue.enque(root);
		}
		
		for(int i = 1; i < input.length; i++) {
			Node newNode = new Node(input[i]);
			Node lastNode = queue.first();
			
			if(lastNode.left == null) {
				lastNode.left = newNode;
			} else {
				lastNode.right = newNode;
				queue.deque();
			}
			queue.enque(newNode);
		}
	}
	
	public void printLevelOrder() {
		if(root == null) {
			System.out.println("Tree is empty");
			return;
		}
		
		Queue<Node> queue = new Queue<Node>();
		queue.enque(root);
		
		int size = 1;
		
		while(!queue.isEmpty()) {
			Node node = queue.deque();
			if(node != null) {
				System.out.print(node.item + " ");
				queue.enque(node.left);
				queue.enque(node.right);
			}
			size--;
			
			if(size == 0) {
				size = queue.size();
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public void printSpiralLevelOrder() {
		if(root == null) {
			System.out.println("Tree is empty");
			return;
		}
		
		Stack<Node> currentStack = new Stack<Node>();
		Stack<Node> nextLevelStack = new Stack<Node>();

		currentStack.push(root);
		boolean rightToLeft = false;
		
		while(!currentStack.isEmpty()) {
			Node node = currentStack.pop();
			if(node != null) {
				System.out.print(node.item + " ");
				if(rightToLeft) {
					nextLevelStack.push(node.right);
					nextLevelStack.push(node.left);
				} else {
					nextLevelStack.push(node.left);
					nextLevelStack.push(node.right);
				}
			}
			if(currentStack.isEmpty()) {
				rightToLeft = !rightToLeft;
				
				Stack<Node> temp = currentStack;
				currentStack = nextLevelStack;
				nextLevelStack = temp;
				
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Integer[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		TreeTraversal<Integer> tree = new TreeTraversal<Integer>();
		tree.buildCompleteBinaryTree(input);
		tree.printLevelOrder();
		tree.printSpiralLevelOrder();
	}
}