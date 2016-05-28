package queue;

public class TreeComplete<Item extends Comparable<Item>> {
	private Node root;
	
	class Node {
		Item item;
		Node left;
		Node right;
		
		public Node(Item item) {
			this.item = item;
			this.left = null;
			this.right = null; 
		}
	}
	
	public void insert(Item item) {
		root = insert(root, item);
	}
	
	private Node insert(Node node, Item item) {
		if(node == null) {
			return new Node(item);
		} else {
			int cmp = item.compareTo(node.item);
			if(cmp < 0) {
				node.left = insert(node.left, item);
			} else if(cmp > 0) {
				node.right = insert(node.right, item);
			} else {
				node.item = item;
			}
			return node;
		}
	}
	
	public void buildCompleteTree(Item[] input) {
		if(input.length == 0) {
			System.out.println("Invalid input length");
			return;
		}
		
		if(root == null) {
			root = new Node(input[0]);
		}
		
		Queue<Node> queue = new Queue<Node>();
		queue.enque(root);
		
		for(int i = 1; i < input.length; i++) {
			Node node = queue.first();
			if(node != null) {
				if(node.left == null) {
					node.left = new Node(input[i]);
					queue.enque(node.left);
				} else {
					node.right = new Node(input[i]);
					queue.enque(node.right);
					queue.deque();
				}
			}
		}
	}
	
	public void printLevelOrder() {
		if(root == null) {
			System.out.println("Tree is empty");
			return;
		}
		
		Queue<Node> queue = new Queue<Node>();
		queue.enque(root);
		
		int size = 1;
		
		while(!queue.isEmpty()) {
			Node node = queue.deque();
			if(node != null) {
				System.out.print(node.item + " ");
				queue.enque(node.left);
				queue.enque(node.right);
			}
			size--;
			
			if(size == 0) {
				size = queue.size();
				System.out.println();
			}
		}
		System.out.println();
	}
	
	public boolean isComplete() {
		Queue<Node> queue = new Queue<Node>();
		queue.enque(root);
		
		boolean lastNodeFlag = false;
		while(!queue.isEmpty()) {
			Node node = queue.deque();
			if(node != null) {
				//print
				if(lastNodeFlag) {
					if(node.left != null || node.right != null) {
						return false;
					}
				}
				
				if(node.left == null) {
					if(node.right == null) {
						lastNodeFlag = true;
					} else {
						return false;
					}
				} else {
					if(node.right == null) {
						lastNodeFlag = true;
					}
				}
				queue.enque(node.left);
				queue.enque(node.right);
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Integer[] input1 = {1, 2, 3, 4, 5, 6, 7};
		TreeComplete<Integer> tree1 = new TreeComplete<Integer>();
		tree1.buildCompleteTree(input1);
		System.out.println(tree1.isComplete());
		
		Integer[] input2 = {4, 2, 6, 3};
		TreeComplete<Integer> tree2 = new TreeComplete<Integer>();
		for(Integer num: input2) {
			tree2.insert(num);
		}
		System.out.println(tree2.isComplete());
	}
}