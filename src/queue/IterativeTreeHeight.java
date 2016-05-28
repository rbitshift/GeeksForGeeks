package queue;

public class IterativeTreeHeight<Item extends Comparable<Item>> {
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
	
	public void add(Item item) {
		root = add(root, item);
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
				node.item = item;
			}
			return node;
		}
	}
	
	public int getHeight() {
		if(root == null) {
			return 0;
		} else {
			Queue<Node> queue = new Queue<Node>();
			queue.enque(root);
			
			int level = -1;
			int curlvlNodeCount = queue.size();
			
			while(!queue.isEmpty()) {
				Node node = queue.deque();
				if(node != null) {
					queue.enque(node.left);
					queue.enque(node.right);
				}
				curlvlNodeCount -= 1;
				
				if(curlvlNodeCount == 0) {
					curlvlNodeCount = queue.size();
					level += 1;
				}
			}
			return level;
		}
	}

	public static void main(String[] args) {
		Integer[] input = {1, 2, 3, 4, 5};
		IterativeTreeHeight<Integer> tree = new IterativeTreeHeight<Integer>();
		for(Integer num: input) {
			tree.add(num);
		}
		
		System.out.println(tree.getHeight());
	}
}