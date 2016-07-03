package dp;

//lis(n) = max(lisEndingAt(i)) where 0<=i<n; lisEndingAt(i) = (1 + lisEndingAt(j)) where aj < ai and 0<=j<i and lisEndingAt(0) = 1;
public class LongestIncreasingSubsequence {
	private static int[] lisend;
	private static int lislen;
	
	public static int solve_recursive(int[] a) {
		int maxLIS = 0;
		for(int i = 0; i < a.length; i++) {
			maxLIS = Math.max(maxLIS, solve_recursive_helper(a, i));
		}
		return maxLIS;
	}
	
	private static int solve_recursive_helper(int[] a, int pos) {
		if(pos < 0) { return 0; }
		
		int endLIS = 1;
		for(int i = 0; i < pos; i++) {
			if(a[i] < a[pos]) {
				endLIS = Math.max(endLIS,  1 + solve_recursive_helper(a, i));
			}
		}		
		return endLIS;
	}
	
	//O(n2)
	public static int solve_memoization(int[] a) {
		lisend = new int[a.length];
		
		for(int i = 0; i < a.length; i++) {
			lisend[i] = -1;
		}
		
		solve_memoization_helper(a, a.length-1);
		return lislen;
	}
	
	private static int solve_memoization_helper(int[] a, int pos) {
		if(pos < 0) { return 0; }
		
		if(lisend[pos] == -1) {
			int endLIS = 1;
			for(int i = 0; i < pos; i++) {
				int q = solve_memoization_helper(a,  i);
				if(a[i] < a[pos]) {
					endLIS = Math.max(endLIS, 1 + q);
				}
			}
			lisend[pos] = endLIS;
			lislen = Math.max(lislen,  lisend[pos]);
		}
		
		return lisend[pos];
	}
	
	public static void main(String[] args) {
		int[] a = {10, 22, 9, 33, 21, 50, 41, 60, 1};
		assert LongestIncreasingSubsequence.solve_recursive(a) == 5;
		assert LongestIncreasingSubsequence.solve_memoization(a) == 5;
	}
}