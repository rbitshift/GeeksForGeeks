package tree;

public class BST<Item extends Comparable<Item>> {
	private Node<Item> root;
	
	public void put(Item[] items) {
		for(int i = 0; i < items.length; i++) {
			put(items[i]);
		}
	}
	
	public void put(Item item) {
		root = put(root, item);
	}
	
	private Node<Item> put(Node<Item> node, Item item) {
		if(node == null) {
			return new Node<Item>(item);
		} else {
			int cmp = item.compareTo(node.item);
			if(cmp < 0) {
				node.left = put(node.left, item);
			} else if(cmp > 0) {
				node.right = put(node.right, item);
			} else {
				node.item = item;
			}
			return node;
		}
	}
	
	public Node<Item> getRoot() {
		return root;
	}
	
	public void printInorder() {
		printInorder(root);
		System.out.println();
	}
	
	private void printInorder(Node<Item> node) {
		if(node != null) {
			printInorder(node.left);
			System.out.print(node.item + " ");
			printInorder(node.right);
		}
	}
}