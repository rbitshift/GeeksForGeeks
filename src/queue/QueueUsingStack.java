package queue;

import stack.Stack;

public class QueueUsingStack<Item extends Comparable<Item>> {
	private Stack<Item> stack1;
	private Stack<Item> stack2;
	
	public QueueUsingStack() {
		stack1 = new Stack<Item>();
		stack2 = new Stack<Item>();
	}
	
	public void enQueue(Item item) {
		if(stack2.isEmpty()) {
			stack1.push(item);
		} else {
			while(!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
			stack1.push(item);
		}
	}
	
	public Item deQueue() {
		while(!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		
		return stack2.pop();
	}
	
	public static void main(String[] args) {
		Integer[] input = {5, 7, 3, 1, 17, 19};
		QueueUsingStack<Integer> queue = new QueueUsingStack<Integer>();
		queue.enQueue(input[0]);
		queue.enQueue(input[1]);
		System.out.println(queue.deQueue());
		
		queue.enQueue(input[2]);
		System.out.println(queue.deQueue());
		
		queue.enQueue(input[0]);
		queue.enQueue(input[4]);
		queue.enQueue(input[5]);
		
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
	}
}