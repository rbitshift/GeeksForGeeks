package dp;

public class MaximumProductCutting {
	
	public static int solve_recursive(int n) {
		if(n <= 1) {
			System.out.println("Incorrect input: " + n);
			return -1;
		}
		
		return solve_recursive_helper(n);
	}
	
	private static int solve_recursive_helper(int n) {
		if(n == 1) {
			return 1;
		}
		
		int maxP = n;
		for(int i = 1; i <= n; i++) {
			int prod = i * solve_recursive_helper(n-i);
			maxP = Math.max(maxP,  prod);
		}
		
		return maxP;
	}

	public static void main(String[] args) {
		for(int i = 2; i <= 10; i++) {
			System.out.println(i + " : " + MaximumProductCutting.solve_recursive(i));
		}
	}
}