package tree.bst;

public class SortedListToBST<Item extends Comparable<Item>> {
	private LNode head;
	private int lsize;
	
	class LNode {
		Item item;
		LNode next;
		
		public LNode(Item item) {
			this.item = item;
			this.next = null;
		}
	}
	public void addToList(Item item) {
		head = addToList(head, item);
		lsize++;
	}
	
	private LNode addToList(LNode node, Item item) {
		if(node == null) {
			return new LNode(item);
		} else {
			node.next = addToList(node.next, item);
			return node;
		}
	}
	
	public void printlist() {
		printlist(head);
		System.out.println();
	}
	
	private void printlist(LNode node) {
		if(node != null) {
			System.out.print(node.item + " ");
			printlist(node.next);
		}
	}
	
	private TNode root;
	class TNode {
		Item item;
		TNode left;
		TNode right;
		
		public TNode(Item item) {
			this.item = item;
			this.left = null;
			this.right = null;
		}
	}
	
	public void convert() {
		root = convert(lsize);
	}
	
	private TNode convert(int lsize) {
		if(lsize <= 0) {
			return null;
		} else {
			TNode tleft = convert(lsize/2);
			TNode tnode = new TNode(head.item);
			tnode.left = tleft;
			
			head = head.next;
			TNode tright = convert(lsize - (lsize/2) - 1);
			tnode.right = tright;
			
			return tnode;
		}
	}
	
	public void printpreorder() {
		printpreorder(root);
	}
	
	private void printpreorder(TNode node) {
		if(node != null) {
			System.out.print(node.item + " " );
			printpreorder(node.left);
			printpreorder(node.right);
		}
	}
	
	public static void main(String[] args) {
		Integer[] input = {1, 2, 3, 4, 5, 6};
		SortedListToBST<Integer> listToBST = new SortedListToBST<Integer>();
		for(Integer num: input) {
			listToBST.addToList(num);
		}
	
		listToBST.printlist();
		listToBST.convert();
		listToBST.printpreorder();
	}

}