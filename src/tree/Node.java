package tree;

public class Node<Item extends Comparable<Item>> {
	Item item;
	Node<Item> left;
	Node<Item> right;
	
	public Node(Item item) {
		this.item = item;
		this.left = this.right = null;
	}
}