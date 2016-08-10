package dp;

/*
 * Recursive Equation:
 * LPS(i, i+d) = max(d) such that P(i, i+d) is true for 0 <= d <= n-1
 * P(i, i+d) = {
 * 				true								if d = 0
 * 				s(i) == s(i+d)						if d = 1
 * 				p(i+1, i+d-1) && s(i) == s(i+d)		if d > 1
 * }
 * 
 */

public class LongestPalindromicSubstring {
	
	public static int solve_recursive(String str) {
		return solve_recursive_helper(str, str.length()-1);
	}
	
	public static int solve_recursive_helper(String str, int pos) {
		
		for(int d = 0; d < pos; d++) {
			int q = solve_recursive_helper(str, d);
			
		}
		return -1;
	}
	
	public static int solve_tabulated(String str) {
		if(str == null || str.length() == 0) {
			System.out.println("Invalid input");
			return 0;
		}
		
		boolean[][] lpst = new boolean[str.length()][str.length()];
		int maxlen = 1;
		
		for(int d = 0; d < str.length(); d++) {
			for(int i = 0; i + d < str.length(); i++) {
				if(d == 0) {
					lpst[i][i+d] = true;
					maxlen = 1;
					continue;
				} else if(d == 1) {
					lpst[i][i+d] = str.charAt(i) == str.charAt(i+d);
					maxlen = 2;
				} else {
					lpst[i][i+d] = ((str.charAt(i) == str.charAt(i+d) && lpst[i+1][i+d-1]));
					if(lpst[i][i+d]) {
						maxlen = Math.max(maxlen,  1 + d);
					}
				}	
			}
		}
		
		return maxlen;
	}

	public static void main(String[] args) {
		System.out.println(LongestPalindromicSubstring.solve_tabulated("dcdb"));
	}
}