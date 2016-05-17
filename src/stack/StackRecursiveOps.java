package stack;

public class StackRecursiveOps<Item extends Comparable<Item>> {
	private Stack<Item> stack;
	
	public StackRecursiveOps() {
		stack = new Stack<Item>();
	}
	
	public void push(Item item) {
		stack.push(item);
	}
	
	public Item pop() {
		return stack.pop();
	}
	
	public Item peek() {
		return stack.peek();
	}
	
	public void reverse() {
		reverseHelper(stack.pop());
	}
	
	private void reverseHelper(Item current) {
		if(current == null) {
			return;
		}
		reverseHelper(stack.pop());
		append(current);
	}
	
	private void append(Item current) {
		if(stack.isEmpty()) {
			stack.push(current);
		} else {
			appendHelper(stack.pop(), current);
		}
	}
	
	private void appendHelper(Item popItem, Item currentItem) {
		if(stack.isEmpty()) {
			stack.push(currentItem);
		} else {
			appendHelper(stack.pop(), currentItem);
		}
		stack.push(popItem);
	}
	
	public void sort() {
		sortHelper(stack.pop());
	}
	
	private void sortHelper(Item current) {
		if(current == null) {
			return;
		}
		sortHelper(stack.pop());
		insert(current);
	}
	
	private void insert(Item current) {
		if(stack.peek() == null || stack.peek().compareTo(current) > 0) {
			stack.push(current);
		} else {
			insertHelper(stack.pop(), current);
		}
	}
	
	private void insertHelper(Item popItem, Item currentItem) {
		if(stack.peek() == null || stack.peek().compareTo(currentItem) > 0) {
			stack.push(currentItem);
		} else {
			insertHelper(stack.pop(), currentItem);
		}
		stack.push(popItem);
	}
	
	
	public static void main(String[] args) {
		StackRecursiveOps<Integer> stack = new StackRecursiveOps<Integer>();
		Integer[] input = {5, 13, 19, 3, 1};
		
		for(int i = 0; i < input.length; i++) {
			stack.push(input[i]);
		}
		stack.reverse();
		for(int i = 0; i < input.length; i++) {
			System.out.println(stack.pop());
		}
		
		
		for(int i = 0; i < input.length; i++) {
			stack.push(input[i]);
		}
		stack.sort();
		for(int i = 0; i < input.length; i++) {
			System.out.println(stack.pop());
		}
	}
}