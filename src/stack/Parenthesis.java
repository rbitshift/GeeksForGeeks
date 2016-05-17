package stack;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Parenthesis {
	private static final char OPEN_BRACKET = '(';
	private static final char CLOSE_BRACKET = ')';
	
	public static boolean isValidExpression(String expr) {
		StringCharacterIterator itr = new StringCharacterIterator(expr);
		Stack<Character> stack = new Stack<Character>();
		
		for(char ch = itr.first(); ch != CharacterIterator.DONE; ch = itr.next()) {
			if(ch == OPEN_BRACKET) {
				stack.push(ch);
			} else if(ch == CLOSE_BRACKET) {
				if(stack.peek() != null && stack.peek() == OPEN_BRACKET) {
					stack.pop();
				} else {
					stack.push(ch);
				}
			} else {
				throw new IllegalArgumentException("Input other than '(' and ')' are not supported");
			}
		}
		return stack.isEmpty();
	}
	
	public static int diffValidExpression(String expr) {
		if(expr.length() % 2 != 0) {
			throw new IllegalArgumentException("Not possible to convert odd length expression into valid expression");
		}
		
		int counter = 0;
		StringCharacterIterator itr = new StringCharacterIterator(expr);
		Stack<Character> stack = new Stack<Character>();
		
		for(char ch = itr.first(); ch != CharacterIterator.DONE; ch = itr.next()) {
			if(ch == OPEN_BRACKET) {
				stack.push(ch);
			} else if(ch == CLOSE_BRACKET) {
				if(stack.peek() != null && stack.peek() == OPEN_BRACKET) {
					stack.pop();
				} else {
					counter++;
					stack.push(OPEN_BRACKET);
				}
			} else {
				throw new IllegalArgumentException("Input other than '(' and ')' are not supported");
			}
		}
		return counter + stack.size() / 2;
	}
	
	public static int maxValidSubstrExpressionLength(String expr) {
		int globalMaxLen = 0;
		int localMaxLen = 0;
		
		Stack<Character> stack = new Stack<Character>();
		for(int i = 0; i < expr.length(); i++) {
			char ch = expr.charAt(i);
			if(ch == OPEN_BRACKET) {
				stack.push(ch);
			} else if(ch == CLOSE_BRACKET) {
				if(stack.peek() != null && stack.peek() == OPEN_BRACKET) {
					stack.pop();
				}
			} else {
				throw new IllegalArgumentException("Input other than '(' and ')' are not supported");
			}
			
			localMaxLen = i - stack.size() + 1;
			if(localMaxLen > globalMaxLen) {
				globalMaxLen = localMaxLen;
			}
		}
		return globalMaxLen / 2;
	}
	
	public static void main(String[] args) {
		assert Parenthesis.isValidExpression(")(((") == false;
		assert Parenthesis.isValidExpression("()(())") == true;
		
		assert Parenthesis.diffValidExpression(")(((") == 3;
		assert Parenthesis.diffValidExpression(")(") == 2;
		
		assert Parenthesis.maxValidSubstrExpressionLength(")(()())(") == 3;
		assert Parenthesis.maxValidSubstrExpressionLength(")(") == 0;
	}
}