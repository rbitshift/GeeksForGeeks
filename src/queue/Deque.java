package queue;

public class Deque<Item> {
	private Node first;
	private Node last;
	int N;
	
	class Node {
		Item item;
		Node next;
		Node prev;
		
		public Node(Item item) {
			this.item = item;
			this.next = null;
			this.prev = null;
		}
	}
	
	public Deque() {
		first = null;
		last = first;
		N = 0;
	}
	
	public boolean isEmpty() {
		return N == 0;
	}
	
	public int size() {
		return N;
	}
	
	public Item pollFirst() {
		if(first == null) {
			return null;
		}
		
		return first.item;
	}
	
	public Item pollLast() {
		if(last == null) {
			return null;
		}
		
		return last.item;
	}
	
	public void addFirst(Item item) {
		Node prevFirstNode = first;
		first = new Node(item);
		first.next = prevFirstNode;
		
		if(prevFirstNode != null) {
			prevFirstNode.prev = first;
		}
		N++;
		if(size() == 1) {
			last = first;
		}
	}
	
	public void addLast(Item item) {
		Node prevLastNode = last;
		last = new Node(item);
		last.prev = prevLastNode;
		
		if(prevLastNode != null) {
			prevLastNode.next = last;
		}
		N++;
		if(size() == 1) {
			first = last;
		}
	}
	
	public Item removeFirst() {
		if(isEmpty()) {
			return null;
		}
		
		Node oldFirstNode = first;
		first = first.next;
		
		if(isEmpty()) {
			last = null;
		}
		N--;
		return oldFirstNode.item;
	}
	
	public Item removeLast() {
		if(isEmpty()) {
			return null;
		}
		
		Node oldLastNode = last;
		last = last.prev;
		
		if(isEmpty()) {
			first = null;
		}
		N--;
		return oldLastNode.item;
	}
}