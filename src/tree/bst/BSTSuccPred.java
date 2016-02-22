package tree.bst;

public class BSTSuccPred<Item extends Comparable<Item>> {
	
	public static void main(String[] args) {
		Integer[] input1 = {1, 2, 3, 4, 5, 6};
		BST<Integer> bst1 = new BST<Integer>(input1);
		assert bst1.successor(3).compareTo(4) == 0;
		assert bst1.successor(1).compareTo(2) == 0;
		assert bst1.successor(6) == null;

		assert bst1.predecessor(3).compareTo(2) == 0;
		
		Integer[] input2 = {20, 8, 22, 4, 12, 10, 14};
		BST<Integer> bst2 = new BST<Integer>(input2);
		assert bst2.successor(12).compareTo(14) == 0;
		
		assert bst2.predecessor(14).compareTo(12) == 0;
		
		System.out.println("ALL TESTS COMPLETED");
	}
}

class BST<Item extends Comparable<Item>> {
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
	
	public BST(Item[] array) {
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
		if(cmp < 0) { 
			return predecessor(node.left, item); 
		} else if (cmp > 0) {
			Node temp = predecessor(node.right, item);
			if(temp == null) { return node; } 
			else {return temp; }
		}
		return null;
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
}