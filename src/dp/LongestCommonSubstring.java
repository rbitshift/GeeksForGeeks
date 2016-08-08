package dp;

public class LongestCommonSubstring {
	
	public static int solve_tabulated(String a, String b) {
		int[][] lcs = new int[a.length()][b.length()];
		
		int maxlen = 0;
		for(int i = 0; i < a.length(); i++) {
			for(int j = 0; j < b.length(); j++) {
				if(a.charAt(i) == b.charAt(j)) {
					if(i == 0 || j == 0) {
						lcs[i][j] = 1;
					} else {
						lcs[i][j] = 1 + lcs[i-1][j-1];
					}
					maxlen = Math.max(maxlen,  lcs[i][j]);
				} else {
					lcs[i][j] = 0;
				}
			}
		}
		return maxlen;
	}

	public static void main(String[] args) {
		System.out.println(LongestCommonSubstring.solve_tabulated("aaaaaaa", "aaa"));

	}

}
