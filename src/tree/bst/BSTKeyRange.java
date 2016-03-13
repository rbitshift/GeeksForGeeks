package tree.bst;

public class BSTKeyRange<Item extends Comparable<Item>> {
	
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
	
	public BSTKeyRange(Item[] items) {
		for(Item item: items) {
			add(item);
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
				//ignore duplicates;
			}
			return node;
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
	
	public void keyrange(Item k1, Item k2) {
		keyrange(root, k1, k2);
	}
	
	public void keyrange(Node node, Item k1, Item k2) {
		if(node != null) {
			if(k1.compareTo(node.item) <= 0) {
				keyrange(node.left, k1, k2);
			}
			if(k1.compareTo(node.item) <= 0 && node.item.compareTo(k2) <= 0) {
				System.out.print(node.item + " ");
			}
			if(node.item.compareTo(k2) <= 0) {
				keyrange(node.right, k1, k2);
			}
		}
	}

	public static void main(String[] args) {
		Integer[] input = {20, 8, 22, 4, 12};
		BSTKeyRange<Integer> bst = new BSTKeyRange<Integer>(input);
		bst.inorder();
		bst.keyrange(10,  22);
	}
}