package list;

public class LinkedList<Item extends Comparable<Item>> {
	private Node head;
	private int size;
	
	class Node {
		Item item;
		Node next;
		
		public Node(Item item) {
			this.item = item;
			this.next = null;
		}
	}
	
	public void add(Item item) {
		head = add(head, item);
		size++;
	}
	
	private Node add(Node node, Item item) {
		if(node == null) {
			return new Node(item);
		} else {
			node.next = add(node.next, item);
			return node;
		}
	}
	
	public int size() {
		return size;
	}
	
	public void print() {
		StringBuilder sb = new StringBuilder();
		Node node = head;
		while(node != null) {
			sb.append(node.item + " ");
			node = node.next;
		}
		System.out.println(sb.toString());
	}
}