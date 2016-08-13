package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	private static Set<String> set;

	public static boolean solve_recursive(String[] dictionary, String word) {
		set = new HashSet<String>(Arrays.asList(dictionary));
		return solve_recursive(word);
	}
	
	private static boolean solve_recursive(String word) {
		int size = word.length();
		if(size == 0) {
			return true;
		}
		
		
		for(int i = 0; i <= size; i++) {
			if(set.contains(word.substring(0, i)) && 
					solve_recursive(word.substring(i, size))) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		String[] dictionary = {"mobile","app","sam", "man","mango", "icecream", "go","i","like","ice","cream"};
		System.out.println(WordBreak.solve_recursive(dictionary, "ilikeicecream"));
		System.out.println(WordBreak.solve_recursive(dictionary, "gameofthrones"));
	}
}