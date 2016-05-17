package stack;

public class MinStack<Item extends Comparable<Item>> {
	private Stack<Item> stack;
	private Stack<Item> minStack;
	
	public MinStack() {
		stack = new Stack<Item>();
		minStack = new Stack<Item>();
	}
	
	public boolean isEmpty() {
		return stack.isEmpty() && minStack.isEmpty();
	}
	
	public void push(Item item) {
		Item min = minStack.peek();
		if(min == null || min.compareTo(item) > 0) {
			min = item;
		}
		stack.push(item);
		minStack.push(min);
	}
	
	public Item pop() {
		Item topItem = stack.pop();
		minStack.pop();
		return topItem;
	}
	
	public Item min() {
		Item min = minStack.peek();
		if(min == null) {
			throw new RuntimeException("Stack is Empty");
		}
		return min;
	}
	
	public static void main(String[] args) {
		MinStack<Integer> minStack = new MinStack<Integer>();
		minStack.push(13);
		minStack.push(5);
		minStack.push(3);
		minStack.push(7);
		minStack.push(19);
		
		assert minStack.min() == 3;
		minStack.pop();
		assert minStack.min() == 3;
		minStack.pop();
		assert minStack.min() == 3;
		minStack.pop();
		assert minStack.min() == 5;
		minStack.pop();
		assert minStack.min() == 13;
		minStack.pop();
	}
}