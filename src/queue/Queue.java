package queue;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int N;
	
	private class Node {
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return N;
	}
	
	public Item first() {
		if(isEmpty()) {
			return null;
		} else {
			return first.item;
		}
	}
	
	public void enque(Item item) {
		Node oldNode = last;
		last = new Node();
		last.item = item;
		
		if(isEmpty()) {
			first = last;
		} else {
			oldNode.next = last;
		}
		N++;
	}
	
	public Item deque() {
		Item item = first.item;
		if(isEmpty()) {
			last = null;
		} else {
			first = first.next;
		}
		N--;
		return item;
	}
	
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			private Node current = first;
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				Item item = current.item;
				current = current.next;
				return item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("Not implemented");
			}
		};
	}
}