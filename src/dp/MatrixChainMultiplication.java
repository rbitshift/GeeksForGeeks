package dp;

public class MatrixChainMultiplication {
	private static int[][] m;
	
	public static int solve_recursive(int[] p) {
		return solve_recursive_helper(p, 1, p.length-1);
	}
	
	private static int solve_recursive_helper(int[] p, int i, int j) {
		if(i == j) {
			return 0;
		}
		
		int min = Integer.MAX_VALUE;
		for(int k = i; k < j; k++) {
			int q = solve_recursive_helper(p,  i,  k) + 
					solve_recursive_helper(p, k+1, j) + 
					p[i-1] * p[k] * p[j];
			
			min = Math.min(min, q);
		}
		return min;
	}
	
	public static int solve_memoized(int[] p) {
		if(p.length < 2) {
			System.out.println("Input input array");
			return 0;
		}
		
		m = new int[p.length][p.length];
		for(int i = 0; i < m.length; i++) {
			for(int j = 0; j < m[0].length; j++) {
				if(i == j) {
					m[i][j] = 0;
				} else {
					m[i][j] = -1;
				}
			}
		}
		
		return solve_memoized_helper(p, 1, p.length-1);
	}
	
	private static int solve_memoized_helper(int[] p, int i, int j) {
		if(m[i][j] == -1) {
			if(i == j) {
				m[i][j] = 0;
			} else {
				int min = Integer.MAX_VALUE;
				for(int k = i; k < j; k++) {
					int q = solve_recursive_helper(p, i, k) + 
							solve_recursive_helper(p, k+1, j) + 
							p[i-1] * p[k] * p[j];
					min = Math.min(min,  q);
					m[i][j] = min;
				}
			}
		}
		
		return m[i][j];
	}

	public static void main(String[] args) {
		assert MatrixChainMultiplication.solve_recursive(new int[]{40, 20, 30, 10, 30}) == 26000;
		assert MatrixChainMultiplication.solve_memoized(new int[]{40, 20, 30, 10, 30}) == 26000;
		
		assert MatrixChainMultiplication.solve_recursive(new int[]{10, 20, 30, 40, 30}) == 30000;
		assert MatrixChainMultiplication.solve_memoized(new int[]{10, 20, 30, 40, 30}) == 30000;
		
		assert MatrixChainMultiplication.solve_recursive(new int[]{10, 20, 30}) == 6000;
		assert MatrixChainMultiplication.solve_memoized(new int[]{10, 20, 30}) == 6000;
		
		assert MatrixChainMultiplication.solve_recursive(new int[]{1, 2, 3, 4}) == 18;
		assert MatrixChainMultiplication.solve_memoized(new int[]{1, 2, 3, 4}) == 18;
	}
}