package dp;

//lis(n) = max(lisEndingAt(i)) where 0<=i<n; lisEndingAt(i) = (1 + lisEndingAt(j)) where aj < ai and 0<=j<i and lisEndingAt(0) = 1;
public class LongestIncreasingSubsequence {
	private static int[] lisend;
	private static int[] lis;
	
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
		lis = new int[a.length];
		lisend = new int[a.length];
		
		for(int i = 0; i < a.length; i++) {
			lis[i] = 1; lisend[i] = -1;
		}
		
		for(int i = 1; i < a.length; i++) {
			lis[i] = Math.max(lis[i-1], solve_memoization_helper(a, i));
		}
		return lis[a.length-1];
	}
	
	private static int solve_memoization_helper(int[] a, int pos) {
		if(pos < 0) { return 0; }
		
		if(lisend[pos] == -1) {
			int endLIS = 1;
			for(int i = 0; i < pos; i++) {
				if(a[i] < a[pos]) {
					endLIS = Math.max(endLIS, 1 + solve_memoization_helper(a, i));
				}
			}
			lisend[pos] = endLIS;
		}
		
		return lisend[pos];
	}
	
	public static void main(String[] args) {
		int[] a = {10, 22, 9, 33, 21, 50, 41, 60, 1};
		assert LongestIncreasingSubsequence.solve_recursive(a) == 5;
		assert LongestIncreasingSubsequence.solve_memoization(a) == 5;
	}
}