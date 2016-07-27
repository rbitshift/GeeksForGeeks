package dp;

public class PalindromePartitioning {
	private static int[][] p;
	
	public static int solve_recursive(String str) {
		return solve_recursive_helper(str, 0, str.length()-1);
	}
	
	private static int solve_recursive_helper(String str, int i, int j) {
		if(i == j || isPalindrome(str, i, j)) {
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		for(int k = i; k < j; k++) {
			min = Math.min(1 + solve_recursive_helper(str, i, k) + solve_recursive_helper(str, k+1, j), min);
		}
		return min;
	}
	
	private static boolean isPalindrome(String str, int i, int j) {
		if(j < i) {
			return false;
		} else {
			boolean palin = true;
			while(i < j) {
				if(str.charAt(i) != str.charAt(j)) {
					palin = false;
				}
				i++;
				j--;
			}
			return palin;
		}
	}
	
	public static int solve_memoized(String str) {
		p = new int[str.length()][str.length()];
		for(int i = 0; i < p.length; i++) {
			for(int j = 0; j < p[0].length; j++) {
				if(i == j) {
					p[i][j] = 0;
				} else {
					p[i][j] = -1;
				}
			}
		}
		return solve_memoized_helper(str, 0, str.length()-1);
	}
	
	private static int solve_memoized_helper(String str, int i, int j) {
		if(p[i][j] == -1) {
			if(isPalindrome(str, i, j)) {
				p[i][j] = 0;
			} else {
				int min = Integer.MAX_VALUE;
				for(int k = i; k < j; k++) {
					min = Math.min(solve_memoized_helper(str, i, k) +
							solve_memoized_helper(str, k+1, j) + 
							1, min);
					p[i][j] = min;
				}
			}
		}
		return p[i][j];
	}

	public static void main(String[] args) {
		assert PalindromePartitioning.solve_recursive("ababbbabbababa") == 3;
		assert PalindromePartitioning.solve_memoized("ababbbabbababa") == 3;
	}
}