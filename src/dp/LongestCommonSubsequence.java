package dp;

public class LongestCommonSubsequence {
	
	public static void solve(String a, String b) {
		int[][] lcs = new int[a.length()+1][b.length()+1];
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				if(i == 0 || j == 0) {
					lcs[i][j] = 0;
				} else {
					if(a.charAt(i-1) == b.charAt(j-1)) {
						lcs[i][j] = 1 + lcs[i-1][j-1];
					} else {
						lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		int i = a.length(); int j = b.length();
		while(i > 0 && j > 0) {
			if(a.charAt(i-1) == b.charAt(j-1)) {
				sb.insert(0, a.charAt(i-1));
				i--; j--;
			} else {
				if(lcs[i][j-1] > lcs[i-1][j]) {
					j--;
				} else {
					i--;
				}
			}
		}
		
		System.out.println("LCS : " + sb.toString() + " Length : " + lcs[a.length()][b.length()]);		
	}

	public static void main(String[] args) {
		String a = "ABCDGH";
		String b = "AEDFHR";
		
		LongestCommonSubsequence.solve(a, b);
		
		String c = "AGGTAB";
		String d = "GXTXAYB";
		
		LongestCommonSubsequence.solve(c, d);
	}
}