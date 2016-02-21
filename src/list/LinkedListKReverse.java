package list;

import java.util.Iterator;

/*
 * Solution:
 * http://www.geeksforgeeks.org/reverse-a-list-in-groups-of-given-size/
 * Given a linked list, write a function to reverse every k nodes (where k is an input to the function).
 */

public class LinkedListKReverse<Item extends Comparable<Item>> implements Iterable<Item>{
	private Node head;
	private int size;
	
	public static void main(String[] args) {
		int K = 3;
		LinkedListKReverse<Integer> list = new LinkedListKReverse<Integer>();
		for(int i = 1; i < 10; i++) {
			list.add(i);
		}
		
		assert list.toString().equals("1 2 3 4 5 6 7 8 9");
		list.kreverse(K);
		assert list.toString().equals("3 2 1 6 5 4 9 8 7");
	}
	
	class Node {
		Item item;
		Node next;
		
		public Node(Item item) {
			this.item = item;
			this.next = null;
		}
	}
	
	public int size() {
		return size;
	}
	
	public void add(Item item) {
		if(head == null) {
			head = new Node(item);
		} else {
			Node current = head;
			while(current.next != null) {
				current = current.next;
			}
			
			current.next = new Node(item);
		}
		size++;
	}
	
	public void reverse() {
		head = reverse(null, head, size);
	}
	
	private Node reverse(Node prev, Node curr, int length) {
		int count = 0;
		while(count < length && curr != null) {
			Node succ = curr.next;
			curr.next = prev;
			prev = curr;
			curr = succ;
			count++;
		}
		return prev;
	}
	
	public void kreverse(int k) {
		Node prev = null;
		Node curr = head;
		while(curr != null) {
			int count = 0;
			Node succ = curr;
			while(succ != null && count < k) {
				succ = succ.next;
				count++;
			}
			Node last = reverse(null, curr, k); 			
			if(prev == null) {
				head = last;
			}
			append(last);
			prev = curr;
			curr = succ;
		}
	}
	
	private void append(Node node) {
		if(node != head) {
			Node curr = head;
			if(curr != null) {
				while(curr.next != null) {
					curr = curr.next;
				}
				curr.next = node;
			}
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			Node current = head;
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				Node oldNode = current;
				current = current.next;
				return oldNode.item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Not implemented");
			}
		};
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		for(Iterator<Item> itr = iterator(); itr.hasNext(); ) {
			sb.append(itr.next() + " ");
		}
		sb.append("\n");
		return sb.toString().trim();
	}
}