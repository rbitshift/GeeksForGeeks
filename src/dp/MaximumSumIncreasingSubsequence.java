package dp;

public class MaximumSumIncreasingSubsequence {
	private static int maxSumR;
	private static int maxSumM;
	private static int[] msis;
	
	public static int solve_recursive(int[] a) {
		if(a.length < 0) {
			System.out.println("Input array is empty");
			return 0;
		}
		
		maxSumR = a[0];
		solve_recursive_helper(a, a.length-1);
		return maxSumR;
	}
	
	private static int solve_recursive_helper(int[] a, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		int localSum = a[pos];
		for(int i = 0; i < pos; i++) { 
			int q = solve_recursive_helper(a, i);
			if(a[i] < a[pos]) {
				localSum = Math.max(localSum, a[pos] + q);
			}
		}
		maxSumR = Math.max(maxSumR, localSum);
		return localSum;
	}
	
	public static int solve_memoized(int[] a) {
		if(a.length < 0) {
			System.out.println("Input array is empty");
			return 0;
		}
		
		msis = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			msis[i] = -1;
		}
		
		maxSumM = a[0];
		solve_memoized_helper(a, a.length-1);
		return maxSumM;
	}
	
	private static int solve_memoized_helper(int[] a, int pos) {
		if(pos < 0) {
			return 0;
		}
		
		if(msis[pos] == -1) {
			int localSum = a[pos];
			for(int i = 0; i < pos; i++) {
				int q = solve_memoized_helper(a, i);
				if(a[i] < a[pos]) {
					localSum = Math.max(localSum, a[pos] + q);
				}
				
			}
			msis[pos] = localSum;
			maxSumM = Math.max(maxSumM,  localSum);
		}		
		return msis[pos];
	}
	
	public static void main(String[] args) {
		assert MaximumSumIncreasingSubsequence.solve_recursive(new int[]{1, 101, 2, 3, 100, 4, 5}) == 106;
		assert MaximumSumIncreasingSubsequence.solve_memoized(new int[]{1, 101, 2, 3, 100, 4, 5}) == 106;
		
		assert MaximumSumIncreasingSubsequence.solve_recursive(new int[]{3, 4, 5, 10}) == 22;
		assert MaximumSumIncreasingSubsequence.solve_memoized(new int[]{3, 4, 5, 10}) == 22;
		
		assert MaximumSumIncreasingSubsequence.solve_recursive(new int[]{10, 5, 4, 3}) == 10;
		assert MaximumSumIncreasingSubsequence.solve_memoized(new int[]{10, 5, 4, 3}) == 10;
		
	}
}