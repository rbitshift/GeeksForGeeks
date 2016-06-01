package tree.bst;

public class LargestBSTSubtree<Item extends Comparable<Item>> {
	private Node root;
	int globalMax = Integer.MIN_VALUE;
	Queue<Node> queue = new Queue<Node>();
	
	class Node{
		Item item;
		Node left;
		Node right;
		
		public Node(Item item) {
			this.item = item;
			this.left = null;
			this.right = null;
		}
	}
	
	public void buildCompleteTree(Item[] array) {
		for(Item i: array) { add(i); }
	}
	
	public void add(Item item) {
		Node nNode = new Node(item);
		if(root == null) {
			root = nNode;
		} else {
			Node qNode = queue.peek();
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
	
	public int getLargestBSTSubtreeSize() {
		if(root == null) {
			System.out.println("Tree is empty");
			return -1;
		}
		getLargetBSTSubtreeSize(root, null, null);
		return globalMax;
	}
	
	private int getLargetBSTSubtreeSize(Node node, Item min, Item max) {
		if(node == null) {
			return 0;
		}
		
		if(min != null && node.item.compareTo(min) <= 0) {
			return -1;
		}
		
		if(max != null && node.item.compareTo(max) >= 0) {
			return -1;
		}
		
		int leftSize = getLargetBSTSubtreeSize(node.left, min, node.item);
		int rightSize = getLargetBSTSubtreeSize(node.right, node.item, max);
		
		if(leftSize != -1 && rightSize != -1) {
			int localMax = leftSize + rightSize + 1;
			if(localMax > globalMax) {
				globalMax = localMax;
			}
			return localMax;
		} else {
			return -1;
		}
	}
	
	public static void main(String[] args) {
		Integer[] input = {5, 2, 4, 1, 3};
		LargestBSTSubtree<Integer> tree = new LargestBSTSubtree<Integer>();
		tree.buildCompleteTree(input);
		
		assert tree.getLargestBSTSubtreeSize() == 3;
	}
}