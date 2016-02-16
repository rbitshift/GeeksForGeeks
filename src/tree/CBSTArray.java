package tree;

public class CBSTArray<Item extends Comparable<Item>> {
	private Item[] items;
	private int size;
	
	public CBSTArray(Item[] items) {
		this.items = items;
		this.size = items.length;
	}
	
	public String inorderTraversal() {
		if(items.length == 0) {
			return "Complete Binary Tree is empty";
		} else {
			StringBuilder sb = new StringBuilder();
			inorderTraversalHelper(1, sb);
			return sb.toString().trim();
		}
	}
	
	private void inorderTraversalHelper(int index, StringBuilder sb) {
		if(index > size) {
			return;
		} else {
			inorderTraversalHelper(left(index), sb);
			sb.append(items[index-1] + " ");
			inorderTraversalHelper(right(index), sb);
		}
	}
	
	private int left(int i) {
		return 2 * i;
	}
	
	private int right(int i) {
		return (2 * i) + 1;
	}
	
	private int parent(int i) {
		return i/2;
	}

	public static void main(String[] args) {
		Integer[] input1 = {4, 2, 5, 1, 3};
		CBSTArray<Integer> tree1 = new CBSTArray<Integer>(input1);
		assert tree1.inorderTraversal().equals("1 2 3 4 5");
		
		Integer[] input2 = {4, 2, 6, 1, 3, 5, 7};
		CBSTArray<Integer> tree2 = new CBSTArray<Integer>(input2);
		assert tree2.inorderTraversal().equals("1 2 3 4 5 6 7");
		
		System.out.println("ALL TESTS COMPLETED");
	}
}
