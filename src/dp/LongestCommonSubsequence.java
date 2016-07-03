package dp;

public class LongestCommonSubsequence {
	private static int[][] lcs;
	
	public static int solve_recursive(String a, String b) {
		return solve_recursive_helper(a, b,  a.length()-1, b.length()-1);
	}
	
	private static int solve_recursive_helper(String strA, String strB, int posA, int posB) {
		if(posA < 0 || posB < 0) {
			return 0;
		}
		
		if(strA.charAt(posA) == strB.charAt(posB)) {
			return 1 + solve_recursive_helper(strA, strB, posA-1, posB-1);
		} else {
			return Math.max(
				solve_recursive_helper(strA, strB, posA, posB-1),
				solve_recursive_helper(strA, strB, posA-1, posB)
			);
		}
	}
	
	public static int solve_memoization(String a, String b) {
		lcs = new int[a.length()+1][b.length()+1];
		for(int i = 0; i <= a.length(); i++) {
			for(int j = 0; j <= b.length(); j++) {
				if(i == 0 || j == 0) { lcs[i][j] = 0; 
				} else { lcs[i][j] = -1; }
			}
		}
		
		return solve_memoization_helper(a, b, a.length(), b.length());
	}
	
	private static int solve_memoization_helper(String strA, String strB, int posA, int posB) {
		if(posA <= 0 || posB <= 0) {
			return 0;
		}
		
		if(lcs[posA][posB] == -1) {
			if(strA.charAt(posA-1) == strB.charAt(posB-1)) {
				lcs[posA][posB] = 1 + solve_memoization_helper(strA, strB, posA-1, posB-1);
			} else {
				lcs[posA][posB] = Math.max(
					solve_memoization_helper(strA, strB, posA, posB-1),
					solve_memoization_helper(strA, strB, posA-1, posB)
				);	
			}
		}
		
		return lcs[posA][posB];
	}
	
	public static void printLCS(String strA, String strB) {
		System.out.println("LCS LENGTH: " + solve_memoization(strA,  strB));
		System.out.println("LCS STRING: " + printLCS(strA, strB, strA.length(), strB.length()));
	}
	
	private static String printLCS(String strA, String strB, int posA, int posB) {
		if(posA <= 0 || posB <= 0) {
			return "";
		} else if(strA.charAt(posA-1) == strB.charAt(posB-1)) {
			return printLCS(strA, strB, posA-1, posB-1) + strA.charAt(posA-1);
		} else {
			if(lcs[posA][posB-1] > lcs[posA-1][posB]) {
				return printLCS(strA, strB, posA, posB-1);
			} else {
				return printLCS(strA, strB, posA-1, posB);
			}
		}
	}

	public static void main(String[] args) {
		String a = "ABCDGH";
		String b = "AEDFHR";
		
		assert LongestCommonSubsequence.solve_recursive(a, b) == 3;
		LongestCommonSubsequence.printLCS(a, b);
		
		String c = "AGGTAB";
		String d = "GXTXAYB";
		
		assert LongestCommonSubsequence.solve_recursive(c, d) == 4;
		LongestCommonSubsequence.printLCS(c, d);
	}
}