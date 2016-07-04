package dp;

public class MaximumSubarraySum {
	private static int maxSumR;
	
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
		if(pos == 0) {
			return a[0];
		}
		
		int localMax = Math.max(a[pos], a[pos] + solve_recursive_helper(a, pos-1));
		maxSumR = Math.max(localMax, maxSumR);
		
		return localMax;
	}
	
	public static int solve_bottom_up(int[] a) {
		int maxSum = Integer.MIN_VALUE;
		int localMax = 0;
		
		for(int i = 0; i < a.length; i++) {
			localMax = Math.max(a[i], a[i] + localMax);
			maxSum = Math.max(localMax,  maxSum);
		}
		
		return maxSum;
	}
	
	
	public static void main(String[] args) {
		int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		assert MaximumSubarraySum.solve_recursive(a) == 7;
		assert MaximumSubarraySum.solve_bottom_up(a) == 7;
	}
}