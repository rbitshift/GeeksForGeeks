package dp;

public class MinimumInsertionPalindrome {
	public static int solve_recursive(String str) {
		return solve_recursive_helper(str, 0, str.length()-1);
	}
	
	private static int solve_recursive_helper(String str, int i, int j) {
		if(i >= j) {
			return 0;
		}
		
		if(str.charAt(i) == str.charAt(j)) {
			return solve_recursive_helper(str, i+1, j-1);
		} else {
			return 1 + Math.min(solve_recursive_helper(str, i, j-1),
					solve_recursive_helper(str, i+1, j));
		}
	}
	

	public static void main(String[] args) {
		System.out.println(MinimumInsertionPalindrome.solve_recursive("ab"));
		System.out.println(MinimumInsertionPalindrome.solve_recursive("abcd"));
		System.out.println(MinimumInsertionPalindrome.solve_recursive("abcda"));
	}
}