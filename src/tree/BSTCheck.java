package tree;

import java.util.Iterator;

public class BSTCheck<Item extends Comparable<Item>> {
	private TNode root;
	Queue<TNode> queue = new Queue<TNode>();
	
	class TNode {
		private Item item; 
		private TNode left; 
		private TNode right;
		
		TNode(Item item) {
			this.item = item;
			this.left = this.right = null;
		}
	};
	
	public BSTCheck(Item[] array) {
		for(Item i: array) { add(i); }
	}
	
	public void add(Item item) {
		TNode nNode = new TNode(item);
		if(root == null) {
			root = nNode;
		} else {
			TNode qNode = queue.peek();
			if(qNode.left != null && qNode.right != null) {
				queue.remove();
			}
			if(qNode.left == null) {
				qNode.left = nNode; 
			} else if(qNode.right == null) {
				qNode.right = nNode;
				queue.remove();
			}
		}
		queue.add(nNode);
	}
	
	public boolean isBST() {
		return isBST(root, null, null);
	}
	
	private boolean isBST(TNode node, Item min, Item max) {
		if(node == null) { 
			return true; 
		}
		if(min != null && node.item.compareTo(min) <= 0) { 
			return false; 
		}
		if(max != null && node.item.compareTo(max) >= 0) { 
			return false; 
		}
		
		return isBST(node.left, min, node.item) && isBST(node.right, node.item, max);
	}
	
	public static void main(String[] args) {
		//TestCase-1;
		Integer[] input1 = {1, 2, 3, 4, 5, 6};
		BSTCheck<Integer> cbtree1 = new BSTCheck<Integer>(input1);
		assert cbtree1.isBST() == false;
		//TestCase-2;
		Integer[] input2 = {6, 5, 4, 3, 2, 1};
		BSTCheck<Integer> cbtree2 = new BSTCheck<Integer>(input2);
		assert cbtree2.isBST() == false;
		//TestCase-3;
		Integer[] input3 = {3, 2, 5, 1, 4};
		BSTCheck<Integer> cbtree3 = new BSTCheck<Integer>(input3);
		assert cbtree3.isBST() == false;

		Integer[] input4 = {10, 7, 20, 4, 9};
		BSTCheck<Integer> cbtree4 = new BSTCheck<Integer>(input4);
		assert cbtree4.isBST() == true;
		System.out.println("ALL TESTS COMPLETED");
	}
}

class Queue<Item> implements Iterable<Item> {
	private QNode first;
	private QNode last;
	private int size;
	
	class QNode {
		Item item;
		QNode next;
		
		public QNode(Item item) {
			this.item = item;
		}
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public int size() {
		return size;
	}
	
	public void add(Item item) {
		QNode oldNode = last;
		last = new QNode(item);
		if(isEmpty()) {
			first = last;
		} else {
			oldNode.next = last;
		}
		size++;
	}
	
	public Item remove() {
		Item item = first.item;
		if(isEmpty()) {
			last = null;
		} else {
			first = first.next;
		}
		size--;
		return item;
	}
	
	public Item peek() {
		return first.item;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			private QNode current = first;
			
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