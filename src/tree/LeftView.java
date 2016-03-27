package tree;

import java.util.Vector;

public class LeftView<Item extends Comparable<Item>> {
	private final BST tree;
	
	public LeftView(BST<Item> bst) {
		this.tree = bst;
	}
	
	//uses level order traversal
	public void print() {
		Node<Integer> nullNode = new Node(null);
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
				queue.add(node.left);
				queue.add(node.right);
				
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
	
	//uses pre-order traversal
	public void printRec() {
		GlobalInteger globalLevel = new GlobalInteger();
		printRec(tree.getRoot(), globalLevel, 0);
	}
	
	private void printRec(Node node, GlobalInteger globalLevel, int curLevel) {
		if(node == null) {
			return;
		}
		
		if(curLevel > globalLevel.level) {
			System.out.print(node.item + " ");
			globalLevel.level = curLevel;
		}
		
		printRec(node.left, globalLevel, curLevel + 1);
		printRec(node.right, globalLevel, curLevel + 1);
	}

	public static void main(String[] args) {
		Integer[] input = {8, 3, 10, 1, 6, 14, 4, 7, 13};
		BST<Integer> tree = new BST<Integer>();
		tree.put(input);
		
		LeftView leftview = new LeftView(tree);
		leftview.print();
		
		leftview.printRec();
	}
}