package stack;

public class Stack<Item extends Comparable<Item>> {
	private Node top;
	private int size;
	
	class Node {
		Item item;
		Node next;
		
		public Node(Item item) {
			this.item = item;
			this.next = null;
		}
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int size() {
		return size;
	}
	
	public Item peek() {
		if(isEmpty()) {
			return null;
		}
		return top.item;
	}
	
	public void push(Item item) {
		Node oldTop = top;
		top = new Node(item);
		top.next = oldTop;
		size++;
	}
	
	public Item pop() {
		Node oldTop = top;
		top = top.next;
		oldTop.next = null;
		size--;
		return oldTop.item;
	}
}