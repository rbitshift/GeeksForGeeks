package tree;

import java.util.Iterator;
import java.util.Stack;

import tree.CompleteBinaryTree.TNode;

public class BSTChecker {
	private CompleteBinaryTree tree;
	private TNode prev;
	
	public BSTChecker(CompleteBinaryTree tree) {
		this.tree = tree;
	}
	
	public boolean isBST() {
		return bstChecker(tree.getRoot());
	}
	
	private boolean bstChecker(TNode node) {
		if(node != null) {
			if(!bstChecker(node.left())) {
				return false;
			}
			
			if(prev != null && node.item().compareTo(prev.item()) < 0) {
				return false;
			}
			
			prev = node;
			bstChecker(node.right());
		}
		return true;
	}
	
	public static void main(String[] args) {
		//TestCase-1;
		Integer[] input1 = {1, 2, 3, 4, 5, 6};
		CompleteBinaryTree<Integer> cbtree1 = new CompleteBinaryTree<Integer>(input1);
		assert new BSTChecker(cbtree1).isBST() == false;
		//TestCase-2;
		Integer[] input2 = {6, 5, 4, 3, 2, 1};
		CompleteBinaryTree<Integer> cbtree2 = new CompleteBinaryTree<Integer>(input2);
		assert new BSTChecker(cbtree2).isBST() == true;
		//TestCase-3;
		Integer[] input3 = {3, 2, 5, 1, 4};
		CompleteBinaryTree<Integer> cbtree3 = new CompleteBinaryTree<Integer>(input3);
		assert new BSTChecker(cbtree3).isBST() == false;
		
		System.out.println("ALL TESTS COMPLETED");
	}
}

class CompleteBinaryTree<Item extends Comparable<Item>> {
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
		
		@SuppressWarnings("unused")
		TNode(Item item, TNode left, TNode right) {
			this.item = item; 
			this.left = left; this.right = right;
		}
		
		public Item item() {
			return item;
		}
		
		public TNode left() {
			return left;
		}
		
		public TNode right() {
			return right;
		}
	};
	
	public TNode getRoot() {
		return root;
	}
	
	public CompleteBinaryTree(Item[] array) {
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
	
	public void InorderTraversalUsingRecursion() {
		recursiveInorderTraversalHelper(root);
		System.out.println();
	}
	
	private void recursiveInorderTraversalHelper(TNode node) {
		if(node == null) {
			return;
		} else {
			recursiveInorderTraversalHelper(node.left);
			System.out.print(node.item + " ");
			recursiveInorderTraversalHelper(node.right);
		}
	}
	
	public void InorderTraversalUsingIteration() {
		Stack<TNode> stack = new Stack<TNode>();
		TNode node = root;
		while(!stack.isEmpty() || node != null) {
			if(node != null) {
				stack.push(node);
				node = node.left;
			} else {
				TNode curr = stack.pop();
				System.out.print(curr.item + " ");
				node = curr.right;
			}
		}
		System.out.println();
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