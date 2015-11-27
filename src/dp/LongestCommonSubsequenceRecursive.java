package dp;

public class LongestCommonSubsequenceRecursive {
	private final String a;
	private final String b;
	
	public LongestCommonSubsequenceRecursive(String a, String b) {
		this.a = a;
		this.b = b;
	}
	
	public void solve() {
		System.out.println(lcsHelper(a.length()-1, b.length()-1));
	}
	
	private int lcsHelper(int i, int j) {
		if(i < 0 || j < 0) {
			return 0;
		}
		
		if(a.charAt(i) == b.charAt(j)) {
			return 1 + lcsHelper(i-1, j-1);
		} else {
			return Math.max(lcsHelper(i, j-1), lcsHelper(i-1, j));
		}
	}
	
	public static void main(String[] args) {
		String a = "ABCDGH";
		String b = "AEDFHR";
		
		LongestCommonSubsequenceRecursive puzzle1 = new LongestCommonSubsequenceRecursive(a, b);
		puzzle1.solve();
		
		String c = "AGGTAB";
		String d = "GXTXAYB";
		
		LongestCommonSubsequenceRecursive puzzle2 = new LongestCommonSubsequenceRecursive(c, d);
		puzzle2.solve();
	}
}