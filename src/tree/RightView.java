package tree;

import java.util.Vector;

public class RightView<Item extends Comparable<Item>> {
	private BST<Item> tree;
	
	public RightView(BST<Item> bst) {
		this.tree = bst;
	}
	
	//uses level order traversal
	public void print() {
		Node<Item> nullNode = new Node<Item>(null);
		Vector<Node> queue = new Vector<Node>();
		queue.add(nullNode);
		queue.add(tree.getRoot());
		
		boolean flag = false;
		while(!queue.isEmpty()) {
			Node node = queue.remove(0);
			if(node != null) {
				if(node.item == null && !queue.isEmpty()) {
					queue.add(nullNode);
					flag = true;
					continue;
				}
				queue.add(node.right);
				queue.add(node.left);
				if(flag && node.item != null) {
					System.out.print(node.item + " ");
					flag = false;
				}
			}
		}
		System.out.println();
	}
	
	class GlobalInteger {
		public int level = -1;
	}
	
	//uses (tweaked) pre-order traversal
	public void printRecursive() {
		GlobalInteger globalLevel = new GlobalInteger();
		printRecursive(tree.getRoot(), globalLevel, 0);
	}
	
	private void printRecursive(Node node, GlobalInteger globalLevel, int curLevel) {
		if(node == null) {
			return;
		}
		
		if(curLevel > globalLevel.level) {
			System.out.print(node.item + " ");
			globalLevel.level = curLevel;
		}
		
		printRecursive(node.right, globalLevel, curLevel + 1);
		printRecursive(node.left, globalLevel, curLevel + 1);
	}
	
	public static void main(String[] args) {
		Integer[] input = {8, 3, 10, 1, 6, 14, 4, 7, 13};
		BST<Integer> tree = new BST<Integer>();
		tree.put(input);
		
		RightView rightview = new RightView(tree);
		rightview.print();
		rightview.printRecursive();
	}
}
