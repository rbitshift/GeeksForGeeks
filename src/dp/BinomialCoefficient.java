package dp;

public class BinomialCoefficient {
	private static int[][] bc;
	
	public static int solve_recursive(int n, int k) {
		if(k == 0 || n == k) {
			return 1;
		} else {
			return solve_recursive(n-1, k-1) +
					solve_recursive(n-1, k);
		}
	}
	
	public static int solve_memoized(int n, int k) {
		bc = new int[n+1][k+1];
		for(int i = 0; i < bc.length; i++) {
			for(int j = 0; j < bc[0].length; j++) {
				bc[i][j] = -1;
			}
		}
		
		return solve_memoized_helper(n, k);
	}
	
	private static int solve_memoized_helper(int n, int k) {
		if(k == 0 || n == k) {
			return 1;
		} else {
			if(bc[n][k] == -1) {
				bc[n][k] = solve_memoized_helper(n-1, k-1) + 
						solve_memoized_helper(n-1, k);
			}
			return bc[n][k];
		}
	}

	public static void main(String[] args) {
		System.out.println(BinomialCoefficient.solve_recursive(5, 2));
		System.out.println(BinomialCoefficient.solve_memoized(5, 2));
	}
}