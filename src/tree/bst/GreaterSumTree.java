package tree.bst;

public class GreaterSumTree {
	private Node root;
	private int sum;
	
	class Node {
		int item;
		Node left;
		Node right;
		
		public Node(int item) {
			this.item = item;
			this.left = null;
			this.right = null;
		}
	}
	
	public void add(int item) {
		root = add(root, item);
	}
	
	private Node add(Node node, int item) {
		if(node == null) {
			return new Node(item);
		} else {
			if(item < node.item) {
				node.left = add(node.left, item);
			} else if(item > node.item) {
				node.right = add(node.right, item);
			} else {
				node.item = item;
			}
			return node;
		}
	}
	
	public void inorder() {
		inorder(root);
		System.out.println();
	}
	
	private void inorder(Node node) {
		if(node != null) {
			inorder(node.left);
			System.out.print(node.item + " ");
			inorder(node.right);
		}
	}
	
	public void convert() {
		convert(root);
		//reset sum;
		sum = 0;
	}
	
	private void convert(Node node) {
		if(node != null) {
			convert(node.right);
			int oldval = node.item;
			node.item = sum;
			sum += oldval;
			convert(node.left);
		}
	}

	public static void main(String[] args) {
		int[] input = {11, 2, 29, 1, 7, 15, 40, 35};
		GreaterSumTree tree = new GreaterSumTree();
		for(int num: input) {
			tree.add(num);
		}
		
		tree.inorder();
		tree.convert();
		tree.inorder();
	}
}