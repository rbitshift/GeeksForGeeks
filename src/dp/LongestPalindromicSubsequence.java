package dp;

public class LongestPalindromicSubsequence {
	private static int[][] lps;

	public static int solve_recursive(String str) {
		return solve_recursive(str, 0, str.length()-1);
	}
	
	private static int solve_recursive(String str, int start, int end) {
		if(start < 0 || end < 0 || end < start) {
			return 0;
		}
		
		if(start == end) {
			return 1;
		}
		
		if(str.charAt(start) == str.charAt(end)) {
			return 2 + solve_recursive(str, start+1, end-1);
		} else {
			return Math.max(solve_recursive(str, start, end-1),
					solve_recursive(str, start+1, end));
		}
	}
	
	public static int solve_memoized(String str) {
		
		lps = new int[str.length()][str.length()];
		for(int i = 0; i < lps.length; i++) {
			for(int j = 0; j < lps[0].length; j++) {
				lps[i][j] = -1;
			}
		}
		return solve_memoized_helper(str, 0, str.length()-1); 
	}
	
	private static int solve_memoized_helper(String str, int start, int end) {
		if(start < 0 || end < 0 || end < start) {
			return 0;
		}
		
		if(start == end) {
			return 1;
		}
		
		if(lps[start][end] == -1) {
			if(str.charAt(start) == str.charAt(end)) {
				lps[start][end] = 2 + solve_memoized_helper(str, start+1, end-1);
			} else {
				lps[start][end] = Math.max(solve_memoized_helper(str, start, end-1),
						solve_memoized_helper(str, start+1, end));
			}
		}
		
		return lps[start][end];
	}
	
	public static void main(String[] args) {
		System.out.println(LongestPalindromicSubsequence.solve_recursive("BBABCBCAB"));
		System.out.println(LongestPalindromicSubsequence.solve_memoized("BBABCBCAB"));
	}
}