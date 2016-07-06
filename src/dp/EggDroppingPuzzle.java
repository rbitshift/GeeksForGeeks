package dp;

public class EggDroppingPuzzle {
	private static int[][] egpt;
	
	public static int solve_recursive(int n, int k) {
		if(k == 0) {
			return 0;
		}
		
		if(n == 1) {
			return k;
		}
		
		int minTrials = Integer.MAX_VALUE;
		for(int i = 1; i <= k; i++) {
			int localTrials = Math.max(solve_recursive(n-1, i-1), 
					solve_recursive(n, k-i));
			minTrials = Math.min(minTrials, localTrials);
		}
		
		return 1 + minTrials;
	}
	
	public static int solve_memoized(int n, int k) {
		egpt = new int[n+1][k+1];
		for(int i = 0; i < egpt.length; i++) {
			for(int j = 0; j < egpt[0].length; j++) {
				egpt[i][j] = -1;
			}
		}
		
		return solve_memoized_helper(n, k);
	}
	
	private static int solve_memoized_helper(int n, int k) {
		if(k == 0) {
			return 0;
		}
		
		if(n == 1) {
			return k;
		}
		
		int minTrials = Integer.MAX_VALUE;
		if(egpt[n][k] == -1) {
			for(int i = 1; i <= k; i++) {
				int localTrials = Math.max(solve_memoized_helper(n-1, i-1),
						solve_memoized_helper(n, k-i));
				minTrials = Math.min(localTrials, minTrials);
				egpt[n][k] = 1 + minTrials;
			}
		}
		
		return egpt[n][k];
	}

	public static void main(String[] args) {
		assert EggDroppingPuzzle.solve_recursive(2, 10) == 4;
		assert EggDroppingPuzzle.solve_memoized(2, 10) == 4;
		assert EggDroppingPuzzle.solve_memoized(2, 100) == 14;
	}
}