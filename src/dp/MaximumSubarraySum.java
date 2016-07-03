package dp;

public class MaximumSubarraySum {
	private static int maxSum;
	
	public static int solve_recursive(int[] a) {
		if(a.length < 0) {
			System.out.println("EMPTY ARRAY");
			return 0;
		}
		maxSum = a[0];
		solve_recursive(a, a.length-1);
		return maxSum;
	}
	
	private static int solve_recursive(int[] a, int pos) {
		if(pos == 0) {
			return a[pos];
		}
		
		int max = Math.max(a[pos],  a[pos] + solve_recursive(a, pos-1));
		maxSum = Math.max(maxSum,  max);
		
		return max;
	}

	public static void main(String[] args) {
		int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
		System.out.println(MaximumSubarraySum.solve_recursive(a));
	}
}