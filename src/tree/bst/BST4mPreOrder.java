package tree.bst;

/*
 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversal-set-2/
 * http://www.geeksforgeeks.org/construct-bst-from-given-preorder-traversa/
 */

import java.util.Stack;
import java.util.Vector;

public class BST4mPreOrder<Item extends Comparable<Item>> {
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
	
	public BST4mPreOrder() {
		
	}
	
	public BST4mPreOrder(Item[] array) {
		if(array.length <= 0) {
			System.out.println("Input array is empty");
			return;
		}
		
		for(int i = 0; i < array.length; i++) {
			if(root == null) {
				root = add(root, array[i]);
			} else {
				add(root, array[i]);
			}

		}
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
				//Ignore duplicates;
			}
			return node;
		}
	}
	
	public void createUsingIteration(Item[] preordItems) {
		if(preordItems.length <= 0) {
			System.out.println("Input array is empty");
			return;
		}
		
		int index = 1; Node last = null;
		
		root = new Node(preordItems[0]);
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(stack.isEmpty() || index < preordItems.length) {
			if(stack.size() > 0) {
				Node top = stack.peek();
				int cmp  = preordItems[index].compareTo(top.item);
				if(cmp < 0) {
					if(last == null) {
						top.left = new Node(preordItems[index++]);
						stack.push(top.left);
					} else {
						last.right = new Node(preordItems[index++]);
						stack.push(last.right);
						last = null;
					}
				} else if(cmp > 0) {
					last = stack.pop();
				} else {
					//duplicates; do nothing;
				}
			} else {
				if(last != null) {
					last.right = new Node(preordItems[index++]);
					stack.push(last.right);
				}
			}
		}
	}
	
	class GlobalInteger {
		int index = 0;
	}
	
	public void createUsingRecursion(Item[] preordItems) {
		GlobalInteger elm = new GlobalInteger();
		root = createUsingRecursion(elm, null, null, preordItems);
	}
	
	private Node createUsingRecursion(GlobalInteger elm, Item min, Item max, Item[] items) {
		if(elm.index >= items.length) { return null; }
		if(min != null && min.compareTo(items[elm.index]) > 0) { return null; }
		if(max != null && max.compareTo(items[elm.index]) < 0) { return null; }
		
		Node node = new Node(items[elm.index]);
		
		elm.index += 1;
		node.left = createUsingRecursion(elm, min, node.item, items);
		node.right = createUsingRecursion(elm, node.item, max, items);
		return node;
	}
	
	public void inorder() {
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node node) {
		if(node == null) {
			return;
		} else {
			inorder(node.left);
			System.out.print(node.item + " ");
			inorder(node.right);
		}
	}
	
	public void levelorder() {
		Vector<Node> queue = new Vector<Node>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Node frontNode = queue.remove(0);
			if(frontNode != null) {
				System.out.print(frontNode.item + " ");
				queue.add(frontNode.left);
				queue.add(frontNode.right);
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Integer[] items =  {10, 5, 1, 7, 40, 50};
		
		BST4mPreOrder<Integer> bst1 = new BST4mPreOrder<Integer>();
		bst1.createUsingIteration(items);
		bst1.inorder();
		bst1.levelorder();
		
		System.out.println();
		
		BST4mPreOrder<Integer> bst2 = new BST4mPreOrder<Integer>();
		bst2.createUsingRecursion(items);
		bst2.inorder();
		bst2.levelorder();
	}
}