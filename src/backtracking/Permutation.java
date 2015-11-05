package backtracking;

import java.util.Scanner;

public class Permutation {

	public static void permute(String s) {
		permuteHelper("", s);
	}
	
	private static void permuteHelper(String s, String t) {
		if(t.length() == 0) {
			System.out.println(s);
		}
		
		for(int i = 0; i < t.length(); i++) {
			permuteHelper(s + t.charAt(i), t.substring(0, i) + t.substring(i+1, t.length()));
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		permute(input);
		
		scanner.close();
	}
}