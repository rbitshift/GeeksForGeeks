package tree.bst;

public class BSTSuccPred<Item extends Comparable<Item>> {
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
	
	public BSTSuccPred(Item[] array) {
		for(Item i: array) { add(i); }
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
	
	public Item predecessor(Item item) {
		Node node = predecessor(root, item);
		if(node == null) { return null; }
		else { return node.item; }
	}

	private Node predecessor(Node node, Item item) {
		if(node == null) {
			return null;
		}
		
		int cmp = item.compareTo(node.item);
		if(cmp <= 0) { 
			return predecessor(node.left, item); 
		} else {
			Node temp = predecessor(node.right, item);
			if(temp == null) { return node; } 
			else {return temp; }
		}
	}
	
	public Item successor(Item item) {
		Node node = successor(root, item);
		if(node == null) { return null; } 
		else { return node.item; }
	}
	
	private Node successor(Node node, Item item) {
		
		if(node == null) {
			return null;
		}
		int cmp = item.compareTo(node.item);
		if(cmp < 0) {
			Node temp = successor(node.left, item);
			if(temp == null) { return node; }
			else { return temp; }
		}
		return successor(node.right, item);
	}
	
	public void inorderTraversal() {
		inorderTraversal(root);
		System.out.println();
	}
	
	private void inorderTraversal(Node node) {
		if(node == null) {
			return;
		} else {
			inorderTraversal(node.left);
			System.out.print(node.item + " ");
			inorderTraversal(node.right);
		}
	}
	
	public static void main(String[] args) {
		Integer[] input1 = {1, 2, 3, 4, 5, 6};
		BSTSuccPred<Integer> bst1 = new BSTSuccPred<Integer>(input1);
		assert bst1.successor(3).compareTo(4) == 0;
		assert bst1.successor(1).compareTo(2) == 0;
		assert bst1.successor(6) == null;
		
		assert bst1.predecessor(3).compareTo(2) == 0;
		
		Integer[] input2 = {20, 8, 22, 4, 12, 10, 14};
		BSTSuccPred<Integer> bst2 = new BSTSuccPred<Integer>(input2);
		assert bst2.successor(14).compareTo(20) == 0;
		
		assert bst2.predecessor(14).compareTo(12) == 0;
		
		Integer[] input3 = {6, 5, 4, 3, 2, 1};
		BSTSuccPred<Integer> bst3 = new BSTSuccPred<Integer>(input3);
		assert bst3.predecessor(2).compareTo(1) == 0;
		assert bst3.successor(1).compareTo(2) == 0;
		System.out.println("ALL TESTS COMPLETED");
	}
}